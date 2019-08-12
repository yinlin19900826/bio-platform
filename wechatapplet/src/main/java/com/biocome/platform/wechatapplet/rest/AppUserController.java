package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.biocome.platform.inter.gateguard.vo.user.AppAccountVo;
import com.biocome.platform.wechatapplet.entity.AppUser;
import com.biocome.platform.wechatapplet.vo.user.AppUserVo;
import com.biocome.platform.wechatapplet.vo.user.ResetPasswordParam;
import com.biocome.platform.wechatapplet.biz.AppUserBiz;
import com.biocome.platform.wechatapplet.rpc.service.AppUserService;
import com.biocome.platform.wechatapplet.vo.user.SimpleUserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    AppUserBiz appUserBiz;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public @ResponseBody ObjectRestResponse<AppUserInfo> validate(@RequestBody Map<String,String> body){
        return appUserService.validate(body.get("username"),body.get("password"), body.get("type"));
    }

    @ApiOperation("用户详情 用户自查 无参数")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public @ResponseBody
    ObjectRestResponse<AppUserVo> detail(){
        String usercode = BaseContextHandler.getUsercode();
        return appUserBiz.detail(usercode);
    }

    @ApiOperation("任何人可查看用户信息详情（暂无权限控制）")
    @ApiImplicitParam(name = "usercode", value = "usercode")
    @RequestMapping(value = "/detailByUsercode", method = RequestMethod.GET)
    public @ResponseBody
    ObjectRestResponse<AppUserVo> detailByUsercode(String usercode){
        return appUserBiz.detail(usercode);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/createAppAccount", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse createAppAccount(@RequestBody AppAccountVo vo){
        return appUserBiz.createAppAccount(vo);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse resetPassword(@RequestBody ResetPasswordParam param){
        return appUserBiz.resetPassword(param);
    }

    @ApiOperation("用户简单信息")
    @ApiImplicitParam(name = "usercode", value = "usercode")
    @RequestMapping(value = "/simpleUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<SimpleUserInfoVo> simpleUserInfo(String usercode){
        return appUserBiz.simpleUserInfo(usercode);
    }
}