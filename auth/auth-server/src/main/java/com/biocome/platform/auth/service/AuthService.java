package com.biocome.platform.auth.service;


import com.biocome.platform.auth.util.user.JwtAuthenticationRequest;

import java.util.Map;

public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
    Map<String, String> appLogin(JwtAuthenticationRequest authenticationRequest) throws Exception;
}
