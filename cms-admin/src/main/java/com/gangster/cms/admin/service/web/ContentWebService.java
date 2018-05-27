package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.service.*;
import com.gangster.cms.admin.util.StringUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.dto.CategoryTree;
import com.gangster.cms.common.dto.CategoryWithParent;
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
import java.util.stream.Stream;


/**
 * @author Yoke
 * Created on 2018/4/10
 */
@Service
public class ContentWebService {
    private final CategoryService categoryService;
    private final ArticleService articleService;

    private final SettingService settingService;

    private final TagService tagService;

    private final SiteService siteService;

    private final WebFileService webFileService;


    private static final Logger LOGGER = LoggerFactory.getLogger(ContentWebService.class);

    @Autowired
    public ContentWebService(CategoryService categoryService
            , ArticleService articleService
            , SettingService settingService
            , TagService tagService
            , SiteService siteService
            , WebFileService webFileService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.settingService = settingService;
        this.tagService = tagService;
        this.siteService = siteService;
        this.webFileService = webFileService;
    }

    public PageInfo<Article> listArticle(Integer siteId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }


    public PageInfo<Article> listCheckArticle(Integer siteId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleStatusEqualTo(CmsConst.REVIEW);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }


    public boolean addArticle(ArticleDTO articleDTO) {
        Article article = articleDTO.getArticle();
        Integer siteId = article.getArticleSiteId();
        Site site = siteService.selectByPrimaryKey(siteId);

        // 如果文章没有设置皮肤,默认为站点的皮肤.
        if (article.getArticleSkin() == null) {
            article.setArticleSkin(site.getSiteSkin());
        }

        try {
            // 添加文章附件
            List<String> fileNames;
            List<WebFile> files = null;
            if (!articleDTO.getFiles().equals("")) {
                fileNames = Arrays.asList(articleDTO.getFiles().split(","));
                WebFileExample webFileExample = new WebFileExample();
                webFileExample.or().andFileNameIn(fileNames);
                files = webFileService.selectByExample(webFileExample);
            }
            articleService.insertSelectiveWithTagAndFile(article, Arrays.asList(articleDTO.getTags().split(",")), files);
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
        String newName = uuid + originalFileName.substring(Objects.requireNonNull(originalFileName).lastIndexOf("."));
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
            articleService.deleteArticleWithTagAndFile(articleId);
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
        return new ArticleDTO(article, category.getCategoryTitle(), tags);
    }


    public boolean updateArticle(Integer articleId, ArticleDTO articleDTO) {
        Article article = articleDTO.getArticle();

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


    public boolean deleteArticles(String articleIdList) {
        if (!StringUtil.isNullOrEmpty(articleIdList)) {
            String[] articleIds = articleIdList.split(",");
            Stream.of(articleIds).forEach(e -> articleService.deleteArticleWithTagAndFile(Integer.parseInt(e)));
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
        categoryExample.or().andCategorySiteIdEqualTo(siteId).andCategoryLevelNotEqualTo(CmsConst.CATEGORY_ROOT_LEVEL);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
    }

    public PageInfo<Category> listCheckCategory(User user, Integer siteId, Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or()
                .andCategorySiteIdEqualTo(siteId)
                .andCategoryStatusEqualTo(CmsConst.REVIEW);

        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
    }


    public List<CategoryTree> select(Integer siteId) {
        List<CategoryTree> treeList = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(siteId).andCategoryLevelEqualTo(CmsConst.CATEGORY_ROOT_LEVEL);
        List<Category> list = categoryService.selectByExample(categoryExample);
        // 如果list为空，就创建以CATEGORY_ROOT_LEVEL为根节点的栏目树
        if (list.size() == 0) {
            Category category = new Category();
            category.setCategoryTitle(CmsConst.CATEGORY_ROOT_NAME);
            category.setCategoryCreateTime(new Date());
            category.setCategoryLevel(CmsConst.CATEGORY_ROOT_LEVEL);
            category.setCategorySiteId(siteId);
            category.setCategoryStatus(CmsConst.ACCESS);
            category.setCategoryDesc("root Category");

            categoryService.insert(category);
            LOGGER.info("在网站id为:{}下添加栏目:{}", siteId, category);
            CategoryTree tree = new CategoryTree();
            tree.setId(1);
            tree.setName(CmsConst.CATEGORY_ROOT_NAME);
            tree.setChildren(null);
            treeList.add(tree);
            return treeList;
        } else {
            Category category = list.get(0);
            CategoryTree tree = categoryService.toTree(category);
            categoryExample.or().andCategoryParentIdEqualTo(category.getCategoryId()).andCategoryStatusEqualTo(CmsConst.ACCESS);
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


    //TODO:该方法耗时过长(3986ms),需修改    @Yoke
    public boolean deleteCategory(User user, Integer categoryId) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (null == category) {
            LOGGER.info("没有找到id为{}的栏目", categoryId);
            return false;
        }

        Integer siteId = category.getCategorySiteId();

        try {
            // 删除权限表信息
            categoryService.deleteCategoryInfo(siteId, categoryId);
        } catch (Exception e) {
            LOGGER.error("删除栏目为{}发生{}错误", categoryId, e.getMessage());
            e.printStackTrace();
            return false;
        }

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
     */
    public CategoryWithParent detailsCategory(Integer categoryId) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (null == category) {
            return null;
        }
        Category categoryParent = categoryService.selectByPrimaryKey(category.getCategoryParentId());
        //TODO: rewrite
        return new CategoryWithParent(categoryParent.getCategoryTitle(), category);
    }


    //TODO: 添加文章权限在controller中判断， 添加目录又在service中判断权限，需修改 @Yoke
    public boolean addCategory(User user, Category category) {
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.or().andModuleNameEqualTo("栏目管理");

        //默认为站点的皮肤
        Site site = siteService.selectByPrimaryKey(category.getCategorySiteId());
        if (category.getCategorySkin().isEmpty()) {
            category.setCategorySkin(site.getSiteSkin());
        }
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

        return true;
    }

    public boolean deleteCategories(String categoryIdList) {
        if (StringUtil.isNullOrEmptyAfterTrim(categoryIdList)) {
            return false;
        }
        try {
            categoryService.deleteBatchCategoryInfo(categoryIdList);
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

        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> siteService.selectByExample(new SiteExample()));
    }


    public boolean addSite(Site site) {
        site.setSiteCreateTime(new Date());
        site.setSiteStatus(CmsConst.REVIEW);
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
