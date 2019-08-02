package com.biocome.platform.auth.client.interceptor;

import com.biocome.platform.auth.client.annotation.IgnoreAppToken;
import com.biocome.platform.auth.client.config.AppAuthConfig;
import com.biocome.platform.auth.client.jwt.UserAuthUtil;
import com.biocome.platform.auth.common.util.constatns.CommonConstants;
import com.biocome.platform.auth.common.util.util.jwt.AppJWTInfo;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.exception.auth.UserTokenException;
import com.biocome.platform.common.exception.auth.UserTokenExpireException;
import com.biocome.platform.common.exception.auth.UserRefreshTokenInvalidException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AppAuthRestInterceptor
 * @Author: zengqiang
 * @Date: 2019/8/2
 * @Description:
 */
public class AppAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    protected UserAuthUtil userAuthUtil;

    @Autowired
    private AppAuthConfig appAuthConfig;

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreAppToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreAppToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreAppToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String access_token = request.getHeader(appAuthConfig.getAccessTokenHeader());
        String refresh_token = request.getHeader(appAuthConfig.getRefreshTokenHeader());
        if (StringUtils.isEmpty(access_token) || StringUtils.isEmpty(refresh_token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(appAuthConfig.getAccessTokenHeader())) {
                        access_token = cookie.getValue();
                    }
                    if(cookie.getName().equals(appAuthConfig.getAccessTokenHeader())){
                        refresh_token = cookie.getValue();
                    }
                }
            }
        }
        AppJWTInfo accessInfo = null;
        try {
            accessInfo= userAuthUtil.getAcccessInfoFromToken(access_token);
            //判断当前用户的token是否有效（单客户端登录，踢掉之前的登录）
            String clientEffectiveCode = accessInfo.getEffectiveCode();
            String redisEffectiveCode = jedisCluster.get(CommonConstants.JWT_ACCESS_TOKEN_EFFECTIVE_CODE+"_"+accessInfo.getUsername());
            if(!clientEffectiveCode.equals(redisEffectiveCode)){
                throw new UserTokenException("由于在其他客户端登录，您已经被迫下线!");
            }
        }catch (ExpiredJwtException ex){
            //若access token过期，则使用refresh_token重新生成access_token（refresh_token有效的前提下）
            AppJWTInfo refreshInfo = userAuthUtil.getRefreshInfoFromToken(refresh_token);
            String redisRefreshToken = jedisCluster.get(CommonConstants.JWT_REFRESH_TOKEN_KEY_PRIFIX+"_"+refreshInfo.getUsername());
            if(StringUtils.isEmpty(redisRefreshToken)){
                throw new UserTokenExpireException("user token expired!");
            }
            if(!redisRefreshToken.equals(refresh_token)){
                throw new UserRefreshTokenInvalidException("user refresh token invalid!");
            }
            //重新生成access_token,并放入response header
            access_token = userAuthUtil.generateNewAccessToken(accessInfo);
            response.setHeader(CommonConstants.NEW_ACCESS_TOKEN, access_token);
        }catch (SignatureException ex){
            throw new UserTokenException("user token signature error!");
        }catch (IllegalArgumentException ex){
            throw new UserTokenException("user token is null or empty!");
        }
        BaseContextHandler.setUsername(accessInfo.getUsername());
        BaseContextHandler.setName(accessInfo.getName());
        BaseContextHandler.setUserID(accessInfo.getId());
        BaseContextHandler.setUsercode(accessInfo.getUsercode());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
