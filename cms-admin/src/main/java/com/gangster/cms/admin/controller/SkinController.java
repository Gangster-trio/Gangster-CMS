package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.FileUploadService;
import com.gangster.cms.admin.util.FileTool;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Skin;
import com.gangster.cms.common.pojo.SkinExample;
import com.gangster.cms.dao.mapper.SettingEntryMapper;
import com.gangster.cms.dao.mapper.SkinMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/skin")
public class SkinController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SkinController.class);
    private final SkinMapper skinMapper;
    private final FileUploadService fileUploadService;
    private final SettingEntryMapper settingEntryMapper;

    @Autowired
    public SkinController(SkinMapper skinMapper,
                          FileUploadService fileUploadService,
                          SettingEntryMapper settingEntryMapper) {
        this.skinMapper = skinMapper;
        this.fileUploadService = fileUploadService;
        this.settingEntryMapper = settingEntryMapper;
    }

    @GetMapping("")
    public AjaxData list(
            @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Skin> pageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> skinMapper.selectByExample(new SkinExample()));
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("")
    public MessageDto add(@Param("file") MultipartFile file) {
        if (!fileUploadService.saveSkinFile(file)) {
            return MessageDto.fail(1, "上传失败");
        }
        return MessageDto.success(null);
    }

    @DeleteMapping("/{skinName}")
    public MessageDto delete(@PathVariable("skinName") String skinName) {
        try {
            skinMapper.deleteByPrimaryKey(skinName);
            FileTool.deleteDir(
                    new File(settingEntryMapper.
                            selectByPrimaryKey(CmsConst.RESOURCE_PATH).getSysValue() + skinName));
            LOGGER.info("删除皮肤:{}", skinName);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除皮肤:{}失败,错误信息:{}", skinName, e.getMessage());
            return MessageDto.fail(1, "删除皮肤失败");
        }
        return MessageDto.success(null);
    }
}
