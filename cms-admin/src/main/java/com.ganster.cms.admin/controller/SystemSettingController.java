package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.SettingEntry;
import com.ganster.cms.core.pojo.SettingEntryExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.SettingService;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
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
public class SystemSettingController extends BaseController {

    @Autowired
    private SettingService settingService;

    @Autowired
    private UserService userService;

    // id代表站点id
    @GetMapping("/list/{id}")
    public List<SettingEntry> list(@PathVariable Integer id) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        User user = userService.selectByPrimaryKey(userId);
        if (userId == null) {
            return null;
        } else {
            SettingEntryExample settingEntryExample = new SettingEntryExample();
            if (!user.getUserName().equals("admin")) {
                if (PermissionUtil.permittedModule(userId, id, 9, CmsConst.PERMISSION_READ)) {
                    return settingService.selectByExample(settingEntryExample);
                } else {
                    return null;
                }
            }
            return settingService.selectByExample(settingEntryExample);
        }
    }

    @PostMapping("/update")
    public Message update(@RequestBody Map<String, String> map) {
        if (map == null) {
            return super.buildMessage(1, "false", null);
        }
        int count = 0;
        String newPicPath = map.get("picSetting");
        SettingEntry picSetting = new SettingEntry();
        picSetting.setSysKey(CmsConst.PIC_PATH_SETTING);
        picSetting.setSysValue(newPicPath);
        picSetting.setSysUpdateTime(new Date());
        count += settingService.updateByPrimaryKey(picSetting);
        String newSkinPath = map.get("skinSetting");
        SettingEntry skinPath = new SettingEntry();
        skinPath.setSysKey(CmsConst.SKIN_PATH_SETTING);
        skinPath.setSysValue(newSkinPath);
        skinPath.setSysUpdateTime(new Date());
        count += settingService.updateByPrimaryKey(skinPath);
        return super.buildMessage(0, "success", count);
    }
}
