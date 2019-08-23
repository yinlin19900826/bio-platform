package com.biocome.platform.auth.service;


import com.biocome.platform.auth.util.user.JwtAuthenticationRequest;
import com.biocome.platform.common.msg.ObjectRestResponse;

import java.util.Map;

public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
    ObjectRestResponse<String> appLogin(JwtAuthenticationRequest authenticationRequest) throws Exception;
    ObjectRestResponse appLogout(String username);
}
