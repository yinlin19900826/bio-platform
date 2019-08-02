package com.biocome.platform.auth.util.user;

import com.biocome.platform.auth.common.util.util.jwt.AppJWTInfo;
import com.biocome.platform.auth.common.util.util.jwt.IJWTInfo;
import com.biocome.platform.auth.common.util.util.jwt.JWTHelper;
import com.biocome.platform.auth.configuration.KeyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ace on 2017/9/10.
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;
    @Value("${jwt.access.expire}")
    private int accessExpire;
    @Value("${jwt.refresh.expire}")
    private int refreshExpire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    /*@Autowired
    private RedisTemplate<String, Object> redisTemplate;*/

    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getUserPriKey(),expire);
    }

    public String generateAccessToken(AppJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateAccessToken(jwtInfo, keyConfiguration.getUserPriKey(),accessExpire);
    }

    public String generateRefreshToken(AppJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateRefreshToken(jwtInfo, keyConfiguration.getUserPriKey(),accessExpire);
    }

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token, keyConfiguration.getUserPubKey());
    }

}
