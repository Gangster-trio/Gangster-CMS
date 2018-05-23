package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.SettingService;
import com.gangster.cms.admin.service.WebFileService;
import com.gangster.cms.admin.util.ZipUtils;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.Skin;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.dao.mapper.SkinMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author Yoke
 * Created on 2018/4/16
 */
@Service
public class FileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    @Autowired
    private WebFileService webFileService;
    @Autowired
    private SettingService settingService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SkinMapper skinMapper;

    public String uploadFile(Integer id, MultipartFile uploadFile) {
        String originFileName = uploadFile.getOriginalFilename();
        LOGGER.info("文件名为{}的文件开始上传", originFileName);
        File dir = new File(settingService.get(CmsConst.FILE_PATH));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        String uploadPath = dir + File.separator + uuid + originFileName;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = uploadFile.getInputStream();
            outputStream = new FileOutputStream(uploadPath);
            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            LOGGER.error("上传文件失败,错误信息：{}", e.getMessage());
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long fileSize = uploadFile.getSize();
        String suffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String virtualPath = "/webfile/" + uuid + originFileName;
        WebFile webFile = new WebFile(virtualPath, new Date(), 0, suffix, String.valueOf(fileSize));
        if (id != null) {
            Article article = articleService.selectByPrimaryKey(id);
            webFile.setFileArticleId(id);
            webFile.setFileSiteId(article.getArticleSiteId());
            webFile.setFileCategoryId(article.getArticleCategoryId());
        }
        try {
            webFileService.insert(webFile);
        } catch (Exception e) {
            LOGGER.error("插入数据库时失败");
            e.printStackTrace();
        }
        LOGGER.info("文件名为{}的文件上传成功，对应新文件名：{}", originFileName, uploadPath);
        if (suffix.equals("zip")) {
            decompressionZIP(uploadPath);
        }
        return virtualPath;
    }

    /**
     * 解压zip包
     */
    private void decompressionZIP(String zipPath) {
        String resourcePath = settingService.get(CmsConst.RESOURCE_PATH);
        try {
            String skinName = ZipUtils.unZip(zipPath, resourcePath);
            skinMapper.insert(new Skin(skinName, new Date(), null));
            LOGGER.info("添加皮肤:{}", skinName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
