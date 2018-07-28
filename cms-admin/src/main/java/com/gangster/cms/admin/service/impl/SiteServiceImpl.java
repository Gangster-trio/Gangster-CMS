package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.admin.util.FileTool;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.SiteMapper;
import com.gangster.cms.dao.mapper.WebFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SiteServiceImpl extends BaseServiceImpl<SiteMapper, Site, SiteExample> implements SiteService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WebFileMapper webFileMapper;

    @Autowired
    private FileTool fileTool;

    @Override
    public int deleteSite(Integer sid) {

        // 删除网站的文件
//        WebFileExample webFileExample = new WebFileExample();
//        webFileExample.or().andFileSiteIdEqualTo(sid);
//        List<WebFile> files = webFileMapper.selectByExample(webFileExample);
//        if (files.size() > 0) {
//            fileTool.deleteFiles(files);
//        }
        // 文章没有删除标签
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(sid);
        categoryService.deleteByExample(categoryExample);
        return super.deleteByPrimaryKey(sid);
    }

    @Override
    public void deleteBatchSite(String siteIdStr) {
        String[] siteIds = siteIdStr.split(",");
        Stream.of(siteIds).forEach(e -> deleteSite(Integer.parseInt(e)));
    }


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return deleteSite(id);
    }
}
