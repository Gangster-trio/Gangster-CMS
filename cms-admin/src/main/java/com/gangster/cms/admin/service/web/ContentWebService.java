package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.service.*;
import com.gangster.cms.admin.util.StringUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.dto.CategoryTree;
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
        if (article.getArticleSkin().isEmpty()) {
            article.setArticleSkin(site.getSiteSkin());
        }

        try {
            // 添加文章附件
            List<String> fileNames;
            List<WebFile> files = null;
            files = transformArticleDto(articleDTO, files);
            articleService.insertWithTagAndFile(article, Arrays.asList(articleDTO.getTags().split(",")), files);
        } catch (Exception e) {
            LOGGER.error("添加文章{}失败,错误原因{}", articleDTO, e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("添加文章{}成功", article);
        return true;
    }


    public PageInfo<Article> listCategoryOfArticle(Integer categoryId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(categoryId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }


    public Map<String, Object> uploadImg(@Param("file") MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        LOGGER.info("文件初始名字{}" + originalFileName);
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
        LOGGER.info("文件上传{}" + newFile.toString());
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
        try {
            articleService.deleteArticleWithTagAndFile(articleId);
        } catch (Exception e) {
            LOGGER.error("删除id为{}的文章发生{}错误", articleId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("删除id为{}的文章成功", articleId);
        return true;
    }


    public ArticleDTO detailsArticle(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
        List<String> tagNames = tagService.selectByArticleId(articleId).stream().map(Tag::getTagName).collect(Collectors.toList());
        String tags = String.join(",", tagNames);
        return new ArticleDTO(article, category.getCategoryTitle(), tags);
    }


    public boolean updateArticle(Integer articleId, ArticleDTO articleDTO) {
        Article article = articleDTO.getArticle();

        article.setArticleId(articleId);
        article.setArticleStatus(CmsConst.REVIEW);
        article.setArticleUpdateTime(new Date());

        try {
            List<String> fileNames;
            List<WebFile> files = null;
            files = transformArticleDto(articleDTO, files);
            articleService.updateSelectWithTagAndFile(articleId, article, Arrays.asList(articleDTO.getTags().split(",")), files);

        } catch (Exception e) {
            LOGGER.error("更新id为{}的文章发生错误{}", articleId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("更新id为{}的文章{}成功", articleId, article);
        return true;
    }

    private List<WebFile> transformArticleDto(ArticleDTO articleDTO, List<WebFile> files) {
        List<String> fileNames;
        if (!articleDTO.getFiles().isEmpty()) {
            fileNames = Arrays.asList(articleDTO.getFiles().split(","));
            WebFileExample webFileExample = new WebFileExample();
            webFileExample.or().andFileNameIn(fileNames);
            files = webFileService.selectByExample(webFileExample);
        }
        return files;
    }


    public boolean deleteArticles(String articleIdList) {
        return split(articleIdList, articleService);
    }

    public static boolean split(String articleIdList, ArticleService articleService) {
        if (!StringUtil.isNullOrEmpty(articleIdList)) {
            String[] articleIds = articleIdList.split(",");
            try {
                Stream.of(articleIds).forEach(e -> articleService.deleteArticleWithTagAndFile(Integer.parseInt(e)));
            } catch (Exception e) {
                LOGGER.error("批量删除文章ids{}的文章发生错误", Arrays.toString(articleIds));
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    public boolean checkArticle(Integer articleId, Integer judge) {

        Article article = articleService.selectByPrimaryKey(articleId);
        article.setArticleStatus(judge);
        try {
            articleService.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            LOGGER.error("审核文章出现错误:{}", e.getMessage());
            return false;
        }
        LOGGER.info("审核文章{}成功", article);
        return true;
    }

//    --------------------------------------------栏目部分--------------------------------------------------------------------

    /**
     * 累出某个站下所有的栏目
     */
    public PageInfo<Category> listCategory(Integer siteId, Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
//        查询条件： 属于当前网站，且不是ROOT
        categoryExample.or().andCategorySiteIdEqualTo(siteId).andCategoryLevelNotEqualTo(CmsConst.CATEGORY_ROOT_LEVEL);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
    }

    /**
     * 列出某个站下待审核的栏目
     */
    public PageInfo<Category> listCheckCategory(Integer siteId, Integer page, Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
//        查询条件: 属于当前站,状态是待审核状态
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
            try {
                categoryService.insert(category);
            } catch (Exception e) {
                LOGGER.error("添加栏目{}失败", category);
                e.printStackTrace();
                return Collections.emptyList();
            }
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


    public boolean deleteCategory(Integer categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除栏目为{}发生{}错误", categoryId, e.getMessage());
            return false;
        }
        LOGGER.info("删除栏目id为{}成功", categoryId);
        return true;
    }


    public boolean updateCategory(Integer categoryId, Category category) {
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
        LOGGER.info("更新id为{}的栏目{}成功", categoryId, category);
        return true;
    }


    /**
     * 查看栏目的详细信息
     */
    public Map<String, Object> detailsCategory(Integer categoryId) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (null == category) {
            return Collections.emptyMap();
        }
        Category categoryParent = categoryService.selectByPrimaryKey(category.getCategoryParentId());
        Map<String, Object> map = new HashMap<>();
        map.put("category", category);
        map.put("parentCategoryName", categoryParent.getCategoryTitle());

        return map;
    }


    public boolean addCategory(Category category) {

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
            LOGGER.error("添加栏目出错:{}", e.getMessage());
            e.printStackTrace();
            return false;
        }

        LOGGER.info("添加栏目{}成功", category);
        return true;
    }

    public boolean deleteCategories(String categoryIds) {
        if (StringUtil.isNullOrEmptyAfterTrim(categoryIds)) {
            return false;
        }
        try {
            categoryService.deleteBatchCategory(categoryIds);
        } catch (Exception e) {
            LOGGER.error("删除多个栏目时候出现错误{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("删除多个栏目ids{}出错", categoryIds);
        return true;
    }

    public boolean checkCategory(Integer categoryId, Integer judge) {
        Category category = categoryService.selectByPrimaryKey(categoryId);
        category.setCategoryStatus(judge);
        try {
            categoryService.updateByPrimaryKeySelective(category);
        } catch (Exception e) {
            LOGGER.error("审核id为{}的栏目出现错误{}", categoryId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("审核栏目{}成功", category);
        return true;
    }

    //    ------------------------------------------------网站部分------------------------------------------------------------------
    public PageInfo<Site> listSite(Integer page, Integer limit) {

        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> siteService.selectByExample(new SiteExample()));
    }


    public boolean addSite(Site site) {
        site.setSiteCreateTime(new Date());
        site.setSiteStatus(CmsConst.REVIEW);
        try {
            siteService.insert(site);
        } catch (Exception e) {
            LOGGER.error("添加站点发生错误{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("添加站点{}成功", site);
        return true;
    }

    public boolean deleteSite(Integer siteId) {
        try {
            siteService.deleteSite(siteId);
        } catch (Exception e) {
            LOGGER.error("删除站点发生错误{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("删除站点id{}成功", siteId);
        return true;
    }

    public boolean deleteSites(String siteIds) {
        if (StringUtil.isNullOrEmptyAfterTrim(siteIds)) {
            return false;
        }
        try {
            siteService.deleteBatchSite(siteIds);
        } catch (Exception e) {
            LOGGER.error("批量删除站点{}发生错误{}", siteIds, e.getMessage());
            e.printStackTrace();
            return false;
        }
        LOGGER.info("批量删除网站成功");
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
        LOGGER.info("更新站点{}成功", site);
        return true;
    }
}
