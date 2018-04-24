package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.admin.service.*;
import com.gangster.cms.admin.util.PermissionUtil;
import com.gangster.cms.admin.util.StringUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.gangster.cms.admin.util.PermissionUtil.getAllPermittedCategory;

/**
 * @author Yoke
 * Created on 2018/4/10
 */
@Service
public class ContentWebService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private TagService tagService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebFileService webFileService;


    private static final Logger LOGGER = LoggerFactory.getLogger(ContentWebService.class);
//    private static final String ADMIN = "admin";

    public PageInfo<Article> listArticle(User user, Integer siteId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        if (user.getUserIsAdmin()) {
            criteria.andArticleSiteIdEqualTo(siteId);
//            articleExample.or().andArticleSiteIdEqualTo(siteId);
        } else {
            List<Integer> categoryList = getAllPermittedCategory(user.getUserId(), siteId, CmsConst.PERMISSION_READ);
            criteria.andArticleCategoryIdIn(categoryList);
//            articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleCategoryIdIn(categoryList);
        }
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }


    public PageInfo<Article> listCheckArticle(User user, Integer siteId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        if (user.getUserIsAdmin()) {
            articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleStatusEqualTo(CmsConst.REVIEW);
//            articleExample.or().andArticleSiteIdEqualTo(siteId);
        } else {
            List<Integer> categoryList = PermissionUtil.getAllPermittedCategory(user.getUserId(), siteId, CmsConst.PERMISSION_READ);
            articleExample.or().andArticleIdIn(categoryList).andArticleStatusEqualTo(CmsConst.REVIEW);
        }
        List<Article> list = articleService.selectByExample(articleExample);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }


    public boolean addArticle(ArticleDTO articleDTO) {
        System.out.println(articleDTO.toString());
        Category category = categoryService.selectByPrimaryKey(articleDTO.toArticle().getArticleCategoryId());
        Integer siteId = category.getCategorySiteId();
        Article article = articleDTO.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteId);
        article.setArticleStatus(CmsConst.REVIEW);
        try {
            List<String> fileNames = null;
            if (articleDTO.getFileNames() != null) {
                fileNames = Arrays.asList(articleDTO.getFileNames().split(","));
            }

            WebFileExample webFileExample = new WebFileExample();
            webFileExample.or().andFileNameIn(fileNames);
            List<WebFile> files = webFileService.selectByExample(webFileExample);
            articleService.insertSelectiveWithTagAndFile(article, Arrays.asList(articleDTO.getTags().split(",")), files);

            for (WebFile webFile : files) {
                webFile.setFileArticleId(article.getArticleId());
            }

        } catch (Exception e) {
            LOGGER.error("添加文章{}失败,错误原因{}", articleDTO, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public PageInfo<Article> listCategoryOfArticle(Integer categoryId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(categoryId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }


    public Map<String, Object> uploadImg(@Param("file") MultipartFile file) {
        // 得到文件最初的名字
        String originalFileName = file.getOriginalFilename();
        LOGGER.info(originalFileName);
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + originalFileName.substring(originalFileName.lastIndexOf("."));
        File dir = new File(settingService.get(CmsConst.PIC_PATH_SETTING));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, newName);
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("文件上传" + newFile.toString());
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileUrl = "/pic/" + newName;
        Map<String, Object> map = new HashMap<>();
        map.put("src", fileUrl);
        return map;
    }


    public boolean deleteSingleArticle(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article == null) {
            LOGGER.info("没有找到id为{}的文章", articleId);
            return false;
        }
        try {
            articleService.deleteArticleWithTagsAndFiles(articleId);
        } catch (Exception e) {
            LOGGER.error("删除id为{}的文章发生{}错误", articleId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public ArticleDTO detailArticle(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);

        if (article == null) {
            LOGGER.info("没有找到id为{}的文章信息", articleId);
            return null;
        }

        Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
        List<Tag> list = tagService.selectByArticleId(articleId);
        List<String> tagNameList
                = list.stream().map(Tag::getTagName).collect(Collectors.toList());
        String tags = String.join(",", tagNameList);
        ArticleDTO articleDTO = new ArticleDTO(article);
        articleDTO.setCategoryName(category.getCategoryTitle());
        articleDTO.setTags(tags);
        return articleDTO;
    }


    public boolean updateArticle(Integer articleId, ArticleDTO articleDTO) {
        Article article = articleDTO.toArticle();

        Article oldArticle = articleService.selectByPrimaryKey(articleId);
        if (null == oldArticle) {
            LOGGER.info("没有找到id为{}的文章", articleId);
            return false;
        }

        article.setArticleId(articleId);
        article.setArticleStatus(CmsConst.REVIEW);
        article.setArticleUpdateTime(new Date());
        try {
            articleService.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            LOGGER.error("更新id为{}的文章发生错误{}", articleId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean deleteArticles(String articleIdData) {
        if (!StringUtil.isNullOrEmpty(articleIdData)) {
            String[] articleIds = articleIdData.split(",");
            Arrays.stream(articleIds).map(e -> articleService.deleteArticleWithTagsAndFiles(Integer.parseInt(e))).collect(Collectors.toList());
            return true;
        } else {
            return false;
        }
    }


    public boolean checkArticle(Integer articleId, Integer judge) {

        Article article = articleService.selectByPrimaryKey(articleId);
        if (article == null) {
            LOGGER.info("没有找到id为{}的文章", articleId);
            return false;
        }
        article.setArticleStatus(judge);
        try {
            articleService.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            LOGGER.error("审核文章出现错误:{}", e.getMessage());
            return false;
        }
        return true;
    }

//    --------------------------------------------栏目部分--------------------------------------------------------------------

    public PageInfo<Category> listCategory(User user, Integer siteId, Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        if (user.getUserIsAdmin()) {
            categoryExample.or().andCategorySiteIdEqualTo(siteId).andCategoryLevelNotEqualTo(CmsConst.CATEGORY_ROOT_LEVEL);
        } else {
            List<Integer> categoryIdList = getAllPermittedCategory(user.getUserId(), siteId, CmsConst.PERMISSION_READ);
            if (categoryIdList.isEmpty()) {
                return null;
            }
            categoryExample.or().andCategoryIdIn(categoryIdList).andCategoryLevelNotEqualTo(CmsConst.CATEGORY_ROOT_LEVEL);
        }
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
    }

    public PageInfo<Category> listCheckCategory(User user, Integer siteId, Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        if (!user.getUserIsAdmin()) {
            List<Integer> categoryIds = PermissionUtil.getAllPermittedCategory(user.getUserId(), siteId, CmsConst.PERMISSION_WRITE);
            criteria.andCategoryIdIn(categoryIds);
        }
        criteria.andCategorySiteIdEqualTo(siteId).andCategoryStatusEqualTo(CmsConst.REVIEW);

//        categoryExample.or().andCategorySiteIdEqualTo(siteId).andCategoryStatusEqualTo(CmsConst.REVIEW);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
    }


    // TODO: 2018/4/15 待显示有权限的栏目
    public List<CategoryTree> select() {
        List<CategoryTree> treeList = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryLevelEqualTo(CmsConst.CATEGORY_ROOT_LEVEL);
        List<Category> list = categoryService.selectByExample(categoryExample);
        // 如果list为空，就创建以CATEGORY_ROOT_LEVEL为根节点的栏目树
        if (list == null) {
            Category category = new Category();
            category.setCategoryLevel(CmsConst.CATEGORY_ROOT_LEVEL);
            category.setCategoryTitle(CmsConst.CATEGORY_ROOT_NAME);
            categoryService.insert(category);
            CategoryTree tree = new CategoryTree();
            tree.setId(1);
            tree.setName(CmsConst.CATEGORY_ROOT_NAME);
            tree.setChildren(null);
            treeList.add(tree);
            return treeList;
        } else {
            CategoryExample categoryExample1 = new CategoryExample();
            categoryExample1.or().andCategoryLevelEqualTo(CmsConst.CATEGORY_ROOT_LEVEL).andCategoryStatusEqualTo(CmsConst.ACCESS);
            List<Category> list1 = categoryService.selectByExample(categoryExample1);
            Category category = list1.get(0);
            CategoryTree tree = categoryService.toTree(category);
            categoryExample.or().andCategoryParentIdEqualTo(1).andCategoryStatusEqualTo(CmsConst.ACCESS);
            List<Category> list2 = categoryService.selectByExample(categoryExample);
            if (list2.size() > 0) {
                for (Category c : list) {
                    treeList.add(categoryService.toTree(c));
                }
                tree.setChildren(treeList);
            }
            return treeList;
        }
    }


    public boolean deleteCategory(User user, Integer categoryId) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (null == category) {
            LOGGER.info("没有找到id为{}的栏目", categoryId);
            return false;
        }

        Integer siteId = category.getCategorySiteId();

        try {
            // 删除权限表信息
            categoryService.deleteCategoryInfo(siteId, categoryId, CmsConst.PERMISSION_READ);
            categoryService.deleteCategoryInfo(siteId, categoryId, CmsConst.PERMISSION_WRITE);
            // 删除栏目下面的文章
            ArticleExample articleExample = new ArticleExample();
            articleExample.or().andArticleCategoryIdEqualTo(category.getCategoryId());
            articleService.selectArticleByCategoryId(categoryId).stream().map(e -> articleService.deleteArticleWithTags(e.getArticleId())).collect(Collectors.toList());
//            List<Article> articleList = articleService.selectArticleByCategoryId(category.getCategoryId());
           /* for (Article article : articleList) {
                articleService.deleteArticleWithTags(article.getArticleId());
            }*/
        } catch (Exception e) {
            LOGGER.error("删除栏目为{}发生{}错误", categoryId, e.getMessage());
            e.printStackTrace();
            return false;
        }

        PermissionUtil.flush(user.getUserId());
        return true;
    }


    public boolean updateCategory(Integer categoryId, Category category) {
        Category c = categoryService.selectByPrimaryKey(categoryId);
        if (null == c) {
            return false;
        }

        category.setCategoryId(categoryId);
        category.setCategoryStatus(CmsConst.REVIEW);
        category.setCategoryUpdateTime(new Date());
        try {
            categoryService.updateByPrimaryKeySelective(category);
        } catch (Exception e) {
            LOGGER.error("更新id为{}的栏目发生错误{}", categoryId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 查看栏目的详细信息
     *
     * @param categoryId
     * @return
     */
    public CategoryWithParent detailsCategory(Integer categoryId) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (null == category) {
            return null;
        }
        return new CategoryWithParent(category.getCategoryTitle(), category);
    }


    public boolean addCategory(User user, Category category) {
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.or().andModuleNameEqualTo("栏目管理");
        Module module = moduleService.selectByExample(moduleExample).get(0);
        if (!permissionService.hasModulePermission(user.getUserId(), category.getCategorySiteId(), module.getModuleId(), CmsConst.PERMISSION_WRITE)) {
            return false;
        }

        category.setCategoryCreateTime(new Date());
        category.setCategorySkin("default");
        category.setCategoryStatus(CmsConst.REVIEW);
        Category parentCategory = categoryService.selectByPrimaryKey(category.getCategoryParentId());
        Integer level = parentCategory.getCategoryLevel();
        if (level.equals(CmsConst.CATEGORY_ROOT_LEVEL)) {
            category.setCategoryLevel(0);
        } else {
            category.setCategoryLevel(level + 1);
        }
        try {
            categoryService.insert(category);
        } catch (Exception e) {
            LOGGER.error("插入栏目出错:{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        try {
            if (!user.getUserIsAdmin()) {
                UserExample userExample = new UserExample();
                userExample.or().andUserNameEqualTo(CmsConst.ADMIN);
                User root = userService.selectByExample(userExample).get(0);
                permissionService.addCategoryPermissionToUser(root.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_READ);
                permissionService.addCategoryPermissionToUser(root.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_WRITE);
            }
            permissionService.addCategoryPermissionToUser(user.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_READ);
            permissionService.addCategoryPermissionToUser(user.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_WRITE);
        } catch (UserNotFoundException e) {
            LOGGER.error("插入权限出错:{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCategories(String categoryIdData) {
        if (StringUtil.isNullOrEmptyAfterTrim(categoryIdData)) {
            return false;
        }
        try {
            categoryService.deleteBatchCategoryInfo(categoryIdData, CmsConst.PERMISSION_READ);
            categoryService.deleteBatchCategoryInfo(categoryIdData, CmsConst.PERMISSION_WRITE);
        } catch (Exception e) {
            LOGGER.error("删除多个栏目时候出现错误{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean checkCategory(Integer categoryId, Integer judge) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (category == null) {
            LOGGER.info("没有找到id为{}的栏目", categoryId);
            return false;
        }
        category.setCategoryStatus(judge);
        try {
            categoryService.updateByPrimaryKeySelective(category);
        } catch (Exception e) {
            LOGGER.error("审核id为{}的栏目出现错误{}", categoryId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //    ------------------------------------------------网站部分------------------------------------------------------------------
    public PageInfo<Site> listSite(User user, Integer page, Integer limit) {
        SiteExample siteExample = new SiteExample();
        List<Integer> siteIdList = PermissionUtil.getAllPermissionSite(user.getUserId());
        if (siteIdList.isEmpty()) {
            return null;
        }
        siteExample.or().andSiteIdIn(siteIdList);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> siteService.selectByExample(siteExample));
    }


    public boolean addSite(User user, Site site) {
        site.setSiteCreateTime(new Date());
        site.setSiteStatus(CmsConst.REVIEW);
        try {
            siteService.insert(site);
            permissionService.addUserToSite(user.getUserId(), site.getSiteId());
        } catch (Exception e) {
            LOGGER.error("插入站点发生错误{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        PermissionUtil.flush(user.getUserId());
        return true;
    }

    public boolean deleteSite(User user, Integer siteId) {
        try {
            siteService.deleteSite(siteId);
        } catch (Exception e) {
            LOGGER.error("删除站点发生错误{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        PermissionUtil.flush(user.getUserId());
        return true;
    }

    public Site detailsSite(Integer siteId) {
        return siteService.selectByPrimaryKey(siteId);
    }

    public boolean updateSite(Integer siteId, Site site) {
        site.setSiteId(siteId);
        try {
            siteService.updateByPrimaryKeySelective(site);
        } catch (Exception e) {
            LOGGER.error("更新站点失败");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
