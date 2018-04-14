package com.ganster.cms.admin.service;

import com.ganster.cms.admin.dto.ArticleDTO;
import com.ganster.cms.admin.web.CmsCommonBean;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.*;
import com.ganster.cms.core.util.PermissionUtil;
import com.ganster.cms.core.util.StringUtil;
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
    private CmsCommonBean cmsCommonBean;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ModuleService moduleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentWebService.class);

    /**
     * 列出站点所有的文章
     *
     * @param siteId
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Article> listArticle(Integer siteId, Integer page, Integer limit) {
        User user = cmsCommonBean.getUser();
        List<Integer> categoryList = PermissionUtil.getAllPermittedCategory(user.getUserId(), siteId, CmsConst.PERMISSION_READ);
        if (null == categoryList || categoryList.isEmpty()) {
            return null;
        }
        ArticleExample articleExample = new ArticleExample();

        // 查询文章条件： siteId,categoryId
        articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleCategoryIdIn(categoryList);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }

    /**
     * 列出该站点所有待审核的文章
     *
     * @param siteId
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Article> listCheckArticle(Integer siteId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleStatusEqualTo(CmsConst.REVIEW);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }

    /**
     * 添加一篇文章
     *
     * @param articleDTO
     */
    public boolean addArticle(ArticleDTO articleDTO) {
        Category category = categoryService.selectByPrimaryKey(articleDTO.getArticleCategoryId());
        Integer siteId = category.getCategorySiteId();
        // 判断是否具有添加权限
        if (!cmsCommonBean.getUser().getUserIsAdmin() || !permissionService.hasCategoryPermission(cmsCommonBean.getUser().getUserId(), siteId, category.getCategoryId(), CmsConst.PERMISSION_WRITE)) {
            return false;
        }
        Article article = articleDTO.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteId);
        article.setArticleStatus(CmsConst.REVIEW);

        String tags = articleDTO.getTags();
        List<String> tagList = Arrays.asList(tags.split(","));
        try {
            articleService.insertSelectiveWithTag(article, tagList);
        } catch (Exception e) {
            LOGGER.error("添加文章失败,错误原因{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 列出某个栏目下的所有文章
     *
     * @param categoryId 栏目id
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Article> listCategoryOfArticle(Integer categoryId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(categoryId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }

    /**
     * 上传文章图片内容
     *
     * @param file
     */
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

    /**
     * 删除单篇文章
     *
     * @param articleId
     * @return
     */
    public boolean deleteSingleArticle(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article == null) {
            LOGGER.info("没有找到id为{}的文章", articleId);
            return false;
        }
        try {
            articleService.deleteArticleWithTags(articleId);
        } catch (Exception e) {
            LOGGER.error("删除id为{}的文章发生{}错误", articleId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 查看一篇文章的详细信息
     *
     * @param articleId
     * @return
     */
    public ArticleDTO detailArticle(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);

        if (article == null) {
            LOGGER.info("没有找到id为{}的文章信息", articleId);
            return null;
        }

        Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
        List<Tag> list = tagService.selectByArticleId(articleId);
        List<String> tagNameList = null;
        if (!(list == null || list.isEmpty())) {
            tagNameList = list.stream().map(Tag::getTagName).collect(Collectors.toList());
        }
        assert tagNameList != null;
        String tags = String.join(",", tagNameList);
        ArticleDTO articleDTO = new ArticleDTO(article);
        articleDTO.setCategoryName(category.getCategoryTitle());
        articleDTO.setTags(tags);
        return articleDTO;
    }


    /**
     * 更新单篇文章
     *
     * @param articleId  要更新的文章id
     * @param articleDTO 新的文章内容
     */
    public boolean updateArticle(Integer articleId, ArticleDTO articleDTO) {
        Article article = articleDTO.toArticle();

        Article oldArticle = articleService.selectByPrimaryKey(articleId);
        if (null == oldArticle) {
            LOGGER.info("没有找到id为{}的文章", articleId);
            return false;
        }

        article.setArticleId(articleId);
        article.setArticleStatus(0);
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


    /**
     * 批量删除文章
     *
     * @param articleIdData 文章id的集合
     * @return
     */
    public boolean deleteArticles(String articleIdData) {
        if (StringUtil.isNullOrEmpty(articleIdData)) {
            String[] articleIds = articleIdData.split(",");
            for (String articleId : articleIds) {
                try {
                    articleService.deleteArticleWithTags(Integer.parseInt(articleId));
                } catch (NumberFormatException e) {
                    LOGGER.error("删除多篇文章发生错误");
                    e.printStackTrace();
                    return false;
                }
            }
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

    /**
     * 列出某个栏目下的文章
     *
     * @param siteId
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Category> listCategory(Integer siteId, Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        List<Integer> categoryIdList = PermissionUtil.getAllPermittedCategory(cmsCommonBean.getUser().getUserId(), siteId, CmsConst.PERMISSION_READ);
        if (categoryIdList == null || categoryIdList.isEmpty()) {
            return null;
        }
        categoryExample.or().andCategoryLevelNotEqualTo(-1).andCategoryIdIn(categoryIdList);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
    }

    /**
     * 列出单个站点需要审核的文章
     *
     * @param siteId
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Category> listCheck(Integer siteId, Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(siteId).andCategoryStatusEqualTo(0);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
    }

    /**
     * 选择分类的时候用到
     *
     * @return
     */
    public List<CategoryTree> select() {
        List<CategoryTree> treeList = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryLevelEqualTo(-1);
        List<Category> list = categoryService.selectByExample(categoryExample);
        if (list == null || list.isEmpty()) {
            Category category = new Category();
            category.setCategoryLevel(-1);
            category.setCategoryTitle("root");
            categoryService.insert(category);
            CategoryTree tree = new CategoryTree();
            tree.setId(1);
            tree.setName("root");
            tree.setChildren(null);
            treeList.add(tree);
            return treeList;
        } else {
            CategoryExample categoryExample1 = new CategoryExample();
            categoryExample1.or().andCategoryLevelEqualTo(-1).andCategoryStatusEqualTo(1);
            List<Category> list1 = categoryService.selectByExample(categoryExample1);
            Category category = list1.get(0);
            CategoryTree tree = categoryService.toTree(category);
            categoryExample.or().andCategoryParentIdEqualTo(1).andCategoryStatusEqualTo(1);
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

    /**
     * 删除某个栏目信息
     *
     * @param categoryId
     * @return
     */
    public boolean delete(Integer categoryId) {
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
            List<Article> articleList = articleService.selectArticleByCategoryId(category.getCategoryId());
            for (Article article : articleList) {
                articleService.deleteArticleWithTags(article.getArticleId());
            }
        } catch (Exception e) {
            LOGGER.error("删除栏目为{}发生{}错误", categoryId, e.getMessage());
            e.printStackTrace();
            return false;
        }

        PermissionUtil.flush(cmsCommonBean.getUser().getUserId());
        return true;
    }


    /**
     * 更新栏目信息
     *
     * @param categoryId
     * @param category
     * @return
     */
    public boolean update(Integer categoryId, Category category) {
        Category c = categoryService.selectByPrimaryKey(categoryId);
        if (null == c) {
            return false;
        }

        category.setCategoryId(categoryId);
        category.setCategoryStatus(0);
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
    public CategoryWithParent details(Integer categoryId) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (null == category) {
            return null;
        }
        return new CategoryWithParent(category.getCategoryTitle(), category);
    }


    public boolean add(Category category) {
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.or().andModuleNameEqualTo("栏目管理");
        Module module = moduleService.selectByExample(moduleExample).get(0);
        if (!permissionService.hasModulePermission(cmsCommonBean.getUser().getUserId(), category.getCategorySiteId(), module.getModuleId(), CmsConst.PERMISSION_WRITE)) {
            return false;
        }

        category.setCategoryCreateTime(new Date());
        category.setCategorySkin("default");
        category.setCategoryStatus(0);
        Category parentCategory = categoryService.selectByPrimaryKey(category.getCategoryParentId());
        Integer level = parentCategory.getCategoryLevel();
        if (level == -1) {
            category.setCategoryLevel(0);
        } else {
            category.setCategoryLevel(level + 1);
        }
        try {
            categoryService.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (!cmsCommonBean.getUser().getUserIsAdmin()) {
                permissionService.addCategoryPermissionToUser(cmsCommonBean.getUser().getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_READ);
                permissionService.addCategoryPermissionToUser(cmsCommonBean.getUser().getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_WRITE);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

    }
}
