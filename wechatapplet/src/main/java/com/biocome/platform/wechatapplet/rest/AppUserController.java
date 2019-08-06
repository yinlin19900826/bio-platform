package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.inter.gateguard.entity.AppUser;
import com.biocome.platform.inter.gateguard.vo.user.AppUserVo;
import com.biocome.platform.wechatapplet.biz.AppUserBiz;
import com.biocome.platform.wechatapplet.rpc.service.AppUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Api(value = "app用户操作", tags = {"app用户操作"})
@Controller
@RequestMapping("app/user")
public class AppUserController extends BaseController<AppUserBiz, AppUser> {
    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public @ResponseBody AppUserInfo validate(@RequestBody Map<String,String> body){
        return appUserService.validate(body.get("username"),body.get("password"));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public @ResponseBody
    AppUserVo detail(){
        String username = BaseContextHandler.getUsername();
        String usercode = BaseContextHandler.getUsercode();
        return appUserService.detail(usercode);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse resetPassword(){
        return null;
    }
}