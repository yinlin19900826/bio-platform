package com.biocome.platform.auth.feign;

import com.biocome.platform.auth.configuration.FeignConfiguration;
import com.biocome.platform.auth.util.user.JwtAuthenticationRequest;
import com.biocome.platform.base.api.vo.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:11
 */
@FeignClient(value = "ace-admin",configuration = FeignConfiguration.class)
public interface IUserService {
  @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
  public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}