package com.biocome.platform.auth.client.interceptor;

import com.biocome.platform.auth.client.annotation.IgnoreUserToken;
import com.biocome.platform.auth.client.config.UserAuthConfig;
import com.biocome.platform.auth.client.jwt.UserAuthUtil;
import com.biocome.platform.auth.common.util.constatns.CommonConstants;
import com.biocome.platform.auth.common.util.util.jwt.IJWTInfo;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.exception.auth.UserTokenException;
import com.biocome.platform.common.exception.auth.UserTokenExpireException;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.ValidateUtils;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by ace on 2017/9/10.
 */
@Slf4j
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);

    @Autowired
    protected UserAuthUtil userAuthUtil;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader(userAuthConfig.getTokenHeader());
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(userAuthConfig.getTokenHeader())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        IJWTInfo infoFromToken = userAuthUtil.getInfoFromToken(token);
        String effectiveCode = infoFromToken.getEffectiveCode();
        if(!ValidateUtils.isEmpty(effectiveCode)){
            String code = jedisCluster.get(CommonConstants.JWT_ACCESS_TOKEN_EFFECTIVE_CODE+"_"+infoFromToken.getUniqueName());
            Assert.isTrue(code.equals(effectiveCode),"有效码验证通过！");
            if(!code.equals(effectiveCode)){
                log.info("effective code from token : "+effectiveCode+" , "+"effective code from cache : "+code);
                throw new UserTokenException("由于在其他客户端登录，您已经被迫下线!");
            }
        }
        String endTime = infoFromToken.getEndtime();
        if(!ValidateUtils.isEmpty(endTime)){
            Date endDate = DateUtils.parseTime(endTime);
            Date now = new Date();
            if(endDate.before(now)){
                throw new UserTokenExpireException("token已过期，请重新登录!");
            }
            if(endDate.after(now) && endDate.before(DateUtils.getAddMinuteTime(now, CommonConstants.TOKEN_CHANGE_MINITE))){
                infoFromToken.setEndtime(DateUtils.getAddDaysDateStr(new Date(), CommonConstants.TOKEN_EFFETIVE_DAY));
                String newToken = userAuthUtil.generateNewToken(infoFromToken);
                response.setHeader(CommonConstants.NEW_TOKEN, newToken);
            }
        }
        BaseContextHandler.setUsername(infoFromToken.getUniqueName());
        BaseContextHandler.setName(infoFromToken.getName());
        BaseContextHandler.setUserID(infoFromToken.getId());
        BaseContextHandler.setUsercode(infoFromToken.getUsercode());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
