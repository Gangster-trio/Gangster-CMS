package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.FileUploadService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Yoke
 * Created on 2018/4/16
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);


    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public MessageDto uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile) {
        if (fileUploadService.uploadFile(uploadFile)) {
            return MessageDto.success(null);
        } else {
            return MessageDto.fail(1, "上传失败");
        }
    }

    // TODO: 2018/4/16 待改进
    @PostMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/ocet-stream");
        FileInputStream inputStream = null;
        File file = new File("D:\\upload\\1.jpg");
        try {
            inputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
