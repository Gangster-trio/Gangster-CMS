package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.common.pojo.WebFileExample;

import java.util.List;

/**
 * @author Yoke
 * Created on 2018/4/18
 */
public interface WebFileService extends BaseService<WebFile, WebFileExample> {

    /**
     * 删除电脑上的，和数据库中的数据
     * @param files 要删除的文件
     */

}
