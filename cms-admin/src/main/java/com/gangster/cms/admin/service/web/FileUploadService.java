package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.service.SettingService;
import com.gangster.cms.common.constant.CmsConst;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author Yoke
 * Created on 2018/4/16
 */
@Component
public class FileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);
    @Autowired
    private SettingService settingService;

    public boolean uploadFile(MultipartFile uploadFile) {
        String originFileName = uploadFile.getOriginalFilename();
        LOGGER.info("文件名为{}的文件开始上传", originFileName);
        File dir = new File(settingService.get(CmsConst.PIC_PATH_SETTING));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString() + File.separator + originFileName;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = uploadFile.getInputStream();
            outputStream = new FileOutputStream(newFileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            LOGGER.error("上传文件失败,错误信息：{}", e.getMessage());
            e.printStackTrace();
            return false;
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
        LOGGER.info("文件名为{}的文件上传成功，对应新文件名：{}", originFileName, newFileName);
        return true;
    }
}
