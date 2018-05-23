package com.gangster.cms.common.constant;

public class CmsConst {

    //放置在主页上的目录
    public static final String INDEX_CATEGORY_TYPE = "主页目录";

    // 普通栏目
    public static final String NORMAL_CATEGORY_TYPE = "普通目录";

    //轮播图
    public static final String CAROUSEL_ARTICLE_TYPE = "轮播图";

    public static final String VIDEO = "视频";

    //主页文章
    public static final String INDEX_ARTICLE_TYPE = "主页文章";

    //普通文章,不放置在主页上
    public static final String NORMAL_ARTICLE_TYPE = "普通文章";
    // 虚拟映射
    public static final String VIRTUAL_PATH = "/pic";
    // 根栏目级别
    public static final Integer CATEGORY_ROOT_LEVEL = -1;

    public static final String SETTING_MODULE = "存储设置";

    public static final String CATEGORY_ROOT_NAME = "root";

    public static final String topic_type_1 = "单选";
    public static final String topic_type_2 = "多选";
    public static final String topic_type_3 = "问答";
    public static final String RESOURCE_PATH = "resource_path";

    // 超级管理员的名字
    public static final String ADMIN = "admin";

    public static final String ARTICLE_SKIN_SUFFIX = "-article";

    public static final String CATEGORY_SKIN_SUFFIX = "-category";

    public static final String SITE_SKIN_SUFFIX = "-site";

    public static final String DEFAULT_SKIN = "default";

    public static final String SKIN_PATH_SETTING = "skin_path";

    public static final String PIC_PATH_SETTING = "pic_path";
    public static final String FILE_PATH = "file_path";

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

    //  未读邮件状态
    public static final Integer MAIIL_READ_TOREAD = 0;

    // 已读邮件状态
    public static final Integer MAIIL_READ_READED = 1;

    // 邮件已经删除状态
    public static final Integer MAIL_READ_DELETED = 2;

    //已经发送状态
    public static final Integer MAIL_FLAG_SENDED = 0;
    // 发送失败
    public static final Integer MAIL_FLAG_FAILED = 1;
    //存为 草稿
    public static final Integer MAIL_FLAG_DRAFT = 2;


}
