package com.biocome.platform.admin.aspect;

import com.biocome.platform.admin.annotation.NoRepeatSubmit;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.util.RequestUtil;
import com.biocome.platform.common.util.UUIDUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class SubmitAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitAspect.class);

    @Autowired
    RedisLock redisLock;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointcut(NoRepeatSubmit noRepeatSubmit){

    }

    @Around("pointcut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable{
        long lockTime = noRepeatSubmit.lockTime();

        HttpServletRequest request = RequestUtil.getRequest();
        String token =  BaseContextHandler.getToken();
        String uri = request.getRequestURI();
        String key = generateKey(token, uri);
        String value = UUIDUtils.generateShortUuid();

        boolean success = redisLock.tryLock(key, value, lockTime);
        if(success){
            LOGGER.info("try lock success! key = [{}], value=[{}]", key, value);
            Object result;

            try {
                result = pjp.proceed();
            }finally {
                redisLock.releaseLock(key, value);
                LOGGER.info("release lock success! key = [{}], value=[{}]", key, value);
            }

            return result;
        }else {
            LOGGER.info("try lock fail! key = [{}]", key);
            return new BaseResponse(CommonConstants.EX_OTHER_CODE, "重复请求，请稍后再试！");
        }
    }

    private String generateKey(String token, String uri) {
        return uri+"-"+token;
    }
}
