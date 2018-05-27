package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.util.DeleteFileUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Skin;
import com.gangster.cms.common.pojo.SkinExample;
import com.gangster.cms.dao.mapper.SkinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/skin")
public class SkinController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SkinController.class);
    @Autowired
    private SkinMapper skinMapper;

    @GetMapping("/list")
    public MessageDto list() {
        return MessageDto.success(skinMapper.selectByExample(new SkinExample()));
    }

    @PutMapping("/add")
    public MessageDto add(Skin skin) {
        return null;
    }

    @DeleteMapping("/delete/{skinName}")
    public MessageDto delete(@PathVariable("skinName") String skinName) {
        try {
            skinMapper.deleteByPrimaryKey(skinName);
            DeleteFileUtil.deleteDir(new File(CmsConst.RESOURCE_PATH + skinName));
            LOGGER.info("删除皮肤:{}", skinName);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除皮肤:{}失败,错误信息:{}", skinName, e.getMessage());
            return MessageDto.fail(1, "删除皮肤失败");
        }
        return MessageDto.success(null);
    }
}
