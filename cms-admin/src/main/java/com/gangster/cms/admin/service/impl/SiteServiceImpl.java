package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl extends BaseServiceImpl<SiteMapper, Site, SiteExample> implements SiteService {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    PermissionService permissionService;

    @Override
    public int deleteSite(Integer sid) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(sid);
        List<Category> categoryList = categoryService.selectByExample(categoryExample);
        if (categoryList != null) {
            for (Category category : categoryList) {
                ArticleExample articleExample = new ArticleExample();
                articleExample.or().andArticleCategoryIdEqualTo(category.getCategoryId());
                articleService.deleteByExample(articleExample);
            }
        }
        categoryService.deleteByExample(categoryExample);
        return super.deleteByPrimaryKey(sid);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return deleteSite(id);
    }
}
