package com.gangster.cms.admin.service.auth;

import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.service.TagService;
import com.gangster.cms.admin.service.WebFileService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.common.pojo.WebFileExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TimedTaskService {
    private static final Logger logger = LoggerFactory.getLogger(TimedTaskService.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private WebFileService webFileService;

    public boolean addArticleTimedTask(ArticleDTO articleDTO) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        Category category = categoryService.selectByPrimaryKey(articleDTO.toArticle().getArticleCategoryId());
        Integer siteId = category.getCategorySiteId();
        Article article = articleDTO.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteId);
        article.setArticleStatus(CmsConst.REVIEW);
        logger.info(article.getArticleTitle());
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
            logger.error("添加文章{}失败,错误原因{}", articleDTO, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
