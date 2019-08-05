package com.biocome.platform.auth.feign;

import com.biocome.platform.auth.configuration.FeignConfiguration;
import com.biocome.platform.auth.util.user.JwtAuthenticationRequest;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.common.vo.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * ${DESCRIPTION}
 *
 * @author zengqiang
 * @create 2019-08-02 11:10
 */
@FeignClient(value = "wechat-applet",configuration = FeignConfiguration.class)
public interface IAppService {
  @RequestMapping(value = "app/user/validate", method = RequestMethod.POST)
  public AppUserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}
