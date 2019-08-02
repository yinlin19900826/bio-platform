package com.biocome.platform.auth.service.impl;

import com.biocome.platform.auth.common.util.constatns.CommonConstants;
import com.biocome.platform.auth.common.util.util.jwt.AppJWTInfo;
import com.biocome.platform.auth.common.util.util.jwt.JWTInfo;
import com.biocome.platform.auth.feign.IAppService;
import com.biocome.platform.common.exception.auth.UserInvalidException;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.common.vo.user.UserInfo;
import com.biocome.platform.auth.service.AuthService;
import com.biocome.platform.auth.util.user.JwtAuthenticationRequest;
import com.biocome.platform.auth.feign.IUserService;
import com.biocome.platform.auth.util.user.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;
    private IAppService appService;
    private JedisCluster jedisCluster;
    @Value("${jwt.refresh.expire}")
    private int refreshExpire;


    @Autowired
    public AuthServiceImpl(JwtTokenUtil jwtTokenUtil, IUserService userService, IAppService appService, JedisCluster jedisCluster) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.appService = appService;
        this.jedisCluster = jedisCluster;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserInfo info = userService.validate(authenticationRequest);
        if (!StringUtils.isEmpty(info.getId())) {
            return jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    /**
     * app登录
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> appLogin(JwtAuthenticationRequest authenticationRequest) throws Exception {
        AppUserInfo info = appService.validate(authenticationRequest);
        if (!StringUtils.isEmpty(info.getId())) {
            String access_token = jwtTokenUtil.generateAccessToken(new AppJWTInfo(info.getUsername(), info.getId(), info.getName(), info.getEffectiveCode()));
            String refresh_token = jedisCluster.get(CommonConstants.JWT_REFRESH_TOKEN_KEY_PRIFIX+"_"+info.getUsername());
            if(StringUtils.isEmpty(refresh_token)){
                refresh_token = jwtTokenUtil.generateRefreshToken(new AppJWTInfo(info.getUsername(), info.getId(), info.getName()));
                jedisCluster.set(CommonConstants.JWT_REFRESH_TOKEN_KEY_PRIFIX+"_"+info.getUsername(), refresh_token,"NX","EX", refreshExpire+new Random().nextInt(60*60*24));
            }
            //将effectiveCode保存在redis
            jedisCluster.set(CommonConstants.JWT_ACCESS_TOKEN_EFFECTIVE_CODE+"_"+info.getUsername(), info.getEffectiveCode());
            //将access_token, refresh_token传递到客户端
            Map<String, String> map = new HashMap<String, String>();
            map.put(CommonConstants.ACCESS_TOKEN_HEADER, access_token);
            map.put(CommonConstants.REFRESH_TOKEN_HEADER, refresh_token);
            return map;
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
