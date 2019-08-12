package com.biocome.platform.wechatapplet.rpc;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.common.vo.user.UserInfo;
import com.biocome.platform.wechatapplet.rpc.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName: AppUserRest
 * @Author: zengqiang
 * @Date: 2019/8/2
 * @Description:
 */
@RestController
@RequestMapping("api/app")
public class AppUserRest {
    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/user/validate", method = RequestMethod.POST)
    public @ResponseBody
    ObjectRestResponse<AppUserInfo> validate(@RequestBody Map<String,String> body){
        return appUserService.validate(body.get("username"),body.get("password"),body.get("type"));
    }

}
