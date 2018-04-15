package com.ganster.cms.core.constant;

import org.omg.CORBA.PUBLIC_MEMBER;

public class CmsConst {

    //放置在主页上的目录
    public static final String INDEX_CATEGORY_TYPE = "主页目录";

    // 普通栏目
    public static final String NORMAL_CATEGORY_TYPE = "普通目录";

    //轮播图
    public static final String CAROUSEL_ARTICLE_TYPE = "轮播图";

    //主页文章
    public static final String INDEX_ARTICLE_TYPE = "主页文章";

    //普通文章,不放置在主页上
    public static final String NORMAL_ARTICLE_TYPE = "普通文章";

    public static final String ARTICLE_SKIN_SUFFIX = "-article";

    public static final String CATEGORY_SKIN_SUFFIX = "-category";

    public static final String SITE_SKIN_SUFFIX = "-site";

    public static final String DEFAULT_SKIN = "default";

    public static final String SKIN_PATH_SETTING = "skin_path";

    public static final String PIC_PATH_SETTING = "pic_path";

    public static final String PERMISSION_READ = "READ";

    public static final String PERMISSION_WRITE = "WRITE";

    public static final String LOG_ERR = "error";

    public static final String LOG_INFO = "info";

    public static final String LOG_DEBUG = "debug";

    public static final String LOG_ACCESS = "access_log";

    public static final String LOG_MODIFY = "modify_log";
    // 通过
    public static final Integer ACCESS = 1;
    // 审核中
    public static final Integer REVIEW = 0;
    // 未通过
    public static final Integer UNACCESS = 2;
    public static final String CURRENT_USER = "currentUser";
}
