package com.biocome.platform.auth.client.jwt;

import com.biocome.platform.auth.client.config.AppAuthConfig;
import com.biocome.platform.auth.client.config.UserAuthConfig;
import com.biocome.platform.auth.common.util.util.jwt.AppJWTInfo;
import com.biocome.platform.auth.common.util.util.jwt.IJWTInfo;
import com.biocome.platform.auth.common.util.util.jwt.JWTHelper;
import com.biocome.platform.common.exception.auth.UserTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private AppAuthConfig appAuthConfig;

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTHelper.getInfoFromToken(token, userAuthConfig.getPubKeyByte());
        }catch (ExpiredJwtException ex){
            throw new UserTokenException("User token expired!");
        }catch (SignatureException ex){
            throw new UserTokenException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new UserTokenException("User token is null or empty!");
        }
    }

    public AppJWTInfo getAcccessInfoFromToken(String token) throws Exception{
        return JWTHelper.getAccessInfoFromToken(token, userAuthConfig.getPubKeyByte());
    }

    public AppJWTInfo getRefreshInfoFromToken(String token) throws Exception{
        try {
            return JWTHelper.getAccessInfoFromToken(token, userAuthConfig.getPubKeyByte());
        }catch (ExpiredJwtException ex){
            throw new UserTokenException("User token expired!");
        }catch (SignatureException ex){
            throw new UserTokenException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new UserTokenException("User token is null or empty!");
        }
    }

    public String generateNewAccessToken(AppJWTInfo info) throws Exception {
        return JWTHelper.generateAccessToken(new AppJWTInfo(info.getUsername(), info.getUserId(), info.getName(), info.getEffectiveCode()),appAuthConfig.getPubKeyByte(),appAuthConfig.getAccessExpire() );
    }
}
