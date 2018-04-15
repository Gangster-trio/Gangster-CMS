package com.gangster.cms.admin.api;

import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @author Yoke
 * Created on 2018/4/15
 */
@RestController
@RequestMapping("/user/api")
public class UserApi {


    @GetMapping("/judge")
    public MessageDto judge(@SessionAttribute(CmsConst.CURRENT_USER) User user) {
        if (user.getUserIsAdmin()) {
            return MessageDto.success(true);
        }
        return MessageDto.fail(1, "审核失败");
    }

}
