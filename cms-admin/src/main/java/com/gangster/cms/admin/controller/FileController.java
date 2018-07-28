package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.config.QiniuConfig;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.WebFileService;
import com.gangster.cms.admin.service.web.FileUploadService;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.common.pojo.WebFileExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.BatchStatus;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Yoke
 * Created on 2018/4/16
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private QiniuConfig qiniuConfig;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private WebFileService webFileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

//    //FIXME 要不要删掉?
//    @SystemControllerLog(description = "添加文件")
//    @CmsPermission(moduleName = "文件管理")
//    @PostMapping({"/{siteId}/{articleId}", "/{siteId}"})
//    public MessageDto uploadFile(
//            @SiteId @PathVariable Integer siteId,
//            @PathVariable(required = false) Integer articleId,
//            @Param("file") MultipartFile file) {
//        if (null == articleId) {
//            return MessageDto.success(fileUploadService.saveFile(file));
//        } else {
//            return MessageDto.success(fileUploadService.saveArticleFile(articleId, file));
//        }
//    }

    // 直接跳转到cdn网址
//    @SystemControllerLog(description = "下载文件")
//    @CmsPermission(moduleName = "文件管理")
//    @GetMapping("/{siteId}/{fkey:file/\\d+-[\\s\\S]+}")
//    public void download(
//            @SiteId @PathVariable Integer siteId,
//            @PathVariable String fkey, HttpServletResponse response) throws IOException {
//        response.sendRedirect(qiniuConfig.getCdnDomain() + "://" + fkey);
//    }

    @SystemControllerLog(description = "列出某个网站的文件")
    @CmsPermission(moduleName = "文件管理")
    @GetMapping("/{siteId}")
    public AjaxData list(
            @SiteId @PathVariable Integer siteId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {


        WebFileExample webFileExample = new WebFileExample();
        webFileExample.or().andFileSiteIdEqualTo(siteId);
        PageInfo<WebFile> pageInfo = PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> webFileService.selectByExample(webFileExample));

//        List<String> keyList = pageInfo.getList().stream().map(WebFile::getFileKey).collect(Collectors.toList());
//
//        BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
//        batchOperations.addStatOps(qiniuConfig.getBucket(),keyList.toArray(new String[]{}));
//        Response response = null;
//        try {
//            response = qiniuConfig.getBucketManager().batch(batchOperations);
//            BatchStatus[] batchStatusList = Objects.requireNonNull(response).jsonToObject(BatchStatus[].class);
//
//
//        } catch (QiniuException e) {
//            LOGGER.error(e.getMessage());
//            return new AjaxData(1,e.getMessage(),0,null);
//        }

        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @DeleteMapping("/{siteId}/{fName}")
    @CmsPermission(moduleName = "文件管理")
    public MessageDto delete(@SiteId @PathVariable("siteId") Integer siteId, @PathVariable("fName") String fName) {
        String fKey = "file/" + siteId + "/" + fName;
        WebFileExample example = new WebFileExample();
        example.or().andFileKeyEqualTo(fKey);
        if (webFileService.deleteByExample(example) == 0) {
            return MessageDto.fail(1, "failed");
        }
        try {
            qiniuConfig.getBucketManager().delete(qiniuConfig.getBucket(), fKey);
        } catch (QiniuException e) {
            LOGGER.error(e.getMessage());
            return MessageDto.fail(1, e.response.error);
        }
        return MessageDto.success(null);
    }
}
