package com.biocome.platform.auth.client.jwt;

import com.biocome.platform.auth.client.config.UserAuthConfig;
import com.biocome.platform.auth.common.util.constatns.CommonConstants;
import com.biocome.platform.auth.common.util.util.jwt.IJWTInfo;
import com.biocome.platform.auth.common.util.util.jwt.JWTHelper;
import com.biocome.platform.auth.common.util.util.jwt.JWTInfo;
import com.biocome.platform.common.exception.auth.UserTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;

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

    public String generateNewToken(IJWTInfo info) throws Exception {
        return JWTHelper.generateToken(new JWTInfo(info.getUniqueName(), info.getId(), info.getName(), info.getUsercode(), info.getEffectiveCode(), info.getEndtime()),
                userAuthConfig.getPubKeyByte(), CommonConstants.REFRESH_TOKEN_EXPIRE);
    }
}
