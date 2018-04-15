package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.MessageDto;
import com.ganster.cms.core.constant.CmsConst;
import com.gangster.cms.common.pojo.SettingEntry;
import com.gangster.cms.common.pojo.SettingEntryExample;
import com.gangster.cms.common.pojo.User;
import com.ganster.cms.core.service.SettingService;
import com.ganster.cms.core.util.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by Yoke on 2018/2/10
 */
@RestController
@RequestMapping("/setting")
public class SystemSettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/list/{id}")
    public List<SettingEntry> list(@SessionAttribute(CmsConst.CURRENT_USER) User user, @PathVariable Integer id) {
        SettingEntryExample settingEntryExample = new SettingEntryExample();
        if (user.getUserIsAdmin()) {
            if (PermissionUtil.permittedModule(user.getUserId(), id, 9, CmsConst.PERMISSION_READ)) {
                return settingService.selectByExample(settingEntryExample);
            } else {
                return null;
            }
        }
        return settingService.selectByExample(settingEntryExample);
    }

    @PostMapping("/update")
    public MessageDto<Object> update(@RequestBody Map<String, String> map) {
        if (map == null) {
            return MessageDto.fail(1, "更新配置失败");
        }
        int count = 0;
        String newPicPath = map.get("picSetting");
        SettingEntry picSetting = new SettingEntry();
        picSetting.setSysKey(CmsConst.PIC_PATH_SETTING);
        picSetting.setSysValue(newPicPath);
        picSetting.setSysUpdateTime(new Date());
        settingService.updateByPrimaryKey(picSetting);
        String newSkinPath = map.get("skinSetting");
        SettingEntry skinPath = new SettingEntry();
        skinPath.setSysKey(CmsConst.SKIN_PATH_SETTING);
        skinPath.setSysValue(newSkinPath);
        skinPath.setSysUpdateTime(new Date());
        settingService.updateByPrimaryKey(skinPath);
        return MessageDto.success(null);
    }
}
