package com.biocome.platform.auth.controller;

import com.biocome.platform.auth.configuration.UserConfiguration;
import com.biocome.platform.auth.service.AuthService;
import com.biocome.platform.auth.util.user.JwtAuthenticationRequest;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jwt/app")
@Slf4j
public class AppController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserConfiguration userConfiguration;

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public ObjectRestResponse<String> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        log.info(authenticationRequest.getUsername()+"app require logging...");
        ObjectRestResponse<String> res = authService.appLogin(authenticationRequest);
        return res;
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ObjectRestResponse logout() throws Exception {
        String username = BaseContextHandler.getUsername();
        log.info("app require logout...username : "+ username);
        ObjectRestResponse res = authService.appLogout(username);
        return res;
    }

    /*@RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ObjectRestResponse<String> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws Exception {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        return new ObjectRestResponse<>().data(refreshedToken);
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ObjectRestResponse<?> verify(String token) throws Exception {
        authService.validate(token);
        return new ObjectRestResponse<>();
    }*/
}
