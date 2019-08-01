package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.wechatapplet.biz.AppUserBiz;
import com.biocome.platform.wechatapplet.entity.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("appUser")
public class AppUserController extends BaseController<AppUserBiz,AppUser> {

}