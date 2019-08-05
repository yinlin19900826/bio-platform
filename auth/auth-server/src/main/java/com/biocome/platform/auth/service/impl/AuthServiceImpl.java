package com.biocome.platform.auth.service.impl;

import com.biocome.platform.auth.common.util.constatns.CommonConstants;
import com.biocome.platform.auth.common.util.util.jwt.JWTInfo;
import com.biocome.platform.auth.feign.IAppService;
import com.biocome.platform.auth.feign.IUserService;
import com.biocome.platform.auth.service.AuthService;
import com.biocome.platform.auth.util.user.JwtAuthenticationRequest;
import com.biocome.platform.auth.util.user.JwtTokenUtil;
import com.biocome.platform.common.exception.auth.UserInvalidException;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.common.vo.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;
    private IAppService appService;
    private JedisCluster jedisCluster;


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
    public String appLogin(JwtAuthenticationRequest authenticationRequest) throws Exception {
        AppUserInfo info = appService.validate(authenticationRequest);
        if (!StringUtils.isEmpty(info.getId())) {
            String token = jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId(), info.getName(), info.getUsercode(), info.getEffectiveCode(), DateUtils.getAddDaysDateStr(new Date(),CommonConstants.TOKEN_EFFETIVE_DAY)), CommonConstants.REFRESH_TOKEN_EXPIRE);
            //将effectiveCode保存在redis
            jedisCluster.set(CommonConstants.JWT_ACCESS_TOKEN_EFFECTIVE_CODE+"_"+info.getUsername(), info.getEffectiveCode());
            return token;
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
