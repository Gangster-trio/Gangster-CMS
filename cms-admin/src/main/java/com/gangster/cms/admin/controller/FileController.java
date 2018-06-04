package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.SettingService;
import com.gangster.cms.admin.service.WebFileService;
import com.gangster.cms.admin.service.web.FileUploadService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.common.pojo.WebFileExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
public class FileController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private WebFileService webFileService;
    @Autowired
    private SettingService settingService;

    @SystemControllerLog(description = "添加文件")
    @CmsPermission(moduleName = "文件管理")
    @PostMapping({"/upload/{siteId}/{articleId}", "/upload/{siteId}"})
    public MessageDto uploadFile(@SiteId  @PathVariable Integer siteId, @PathVariable(required = false) Integer articleId, @Param("file") MultipartFile file) {
        if (null == articleId) {
            return MessageDto.success(fileUploadService.saveFile(file));
        } else {
            return MessageDto.success(fileUploadService.saveArticleFile(articleId, file));
        }
    }

    @SystemControllerLog(description = "下载文件")
    @CmsPermission(moduleName = "文件管理")
    @PostMapping("/download/{siteId}/{fId}")
    public void download(@SiteId @PathVariable Integer siteId, @PathVariable Integer fId, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/ocet-stream");
        FileInputStream inputStream = null;
        try {
            WebFile webFile = webFileService.selectByPrimaryKey(fId);
            String webPath = webFile.getFileName();
            String downPath = settingService.get(CmsConst.FILE_PATH) + webPath.split("/")[2];
            File file = new File(downPath);
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

    @SystemControllerLog(description = "列出某个网站的文件")
    @CmsPermission(moduleName = "文件管理")
    @GetMapping("/list")
    public AjaxData list(@SiteId @RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        WebFileExample webFileExample = new WebFileExample();
        webFileExample.or().andFileSiteIdEqualTo(siteId);
        PageInfo<WebFile> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> webFileService.selectByExample(webFileExample));
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/delete/{siteId}/{fId}")
    @CmsPermission(moduleName = "文件管理")
    public MessageDto delete(@SiteId @PathVariable Integer siteId, @PathVariable Integer fId) {
        WebFile file = webFileService.selectByPrimaryKey(fId);
        if (file == null) {
            return MessageDto.fail(1, "要删除的文件不存在");
        }
        webFileService.deleteByPrimaryKey(fId);
        return MessageDto.success(null);
    }
}
