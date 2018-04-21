/*package com.gangster.cms.admin.interceptor;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

*//**
 * @author Yoke
 * Created on 2018/4/17
 *//*
@Component("fileUploadInterceptor")
@ConfigurationProperties(prefix = "fileupload")
public class FileUploadInterceptor extends HandlerInterceptorAdapter {

    private List<String> allowFileTypeList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (allowFileTypeList == null) {
            return super.preHandle(request, response, handler);
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        while (it.hasNext()) {
            String fileParamFile = it.next();
            List<MultipartFile> listFile = multipartRequest.getFiles(fileParamFile);
            if (!CollectionUtils.isEmpty(listFile)) {
                MultipartFile multipartFile = null;
                String fileName = "";
                for (MultipartFile aListFile : listFile) {
                    multipartFile = aListFile;
                    fileName = multipartFile.getOriginalFilename();
                    int flag = 0;
                    if ((flag = fileName.lastIndexOf(".")) > 0) {
                        fileName = fileName.substring(flag + 1);
                    }
                    if (!allowFileTypeList.contains(fileName)) {
                        this.outputStream(request, response);
                        return false;
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    private void outputStream(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        System.out.println("上传的文件不被允许");
    }

    public void setAllowFileTypeList(String allowFileType) {
        if (StringUtils.isEmpty(allowFileType)) {
            allowFileTypeList = null;
            return;
        }
        allowFileTypeList = Arrays.asList(allowFileType.split(","));
    }
}*/
