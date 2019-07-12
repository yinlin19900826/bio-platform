package com.biocome.platform.guard.aspect;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.util.RequestUtil;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.guard.annotation.NoRepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName: SubmitAspect
 * @Author: shenlele
 * @Date: 2019/7/11 10:48
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class SubmitAspect {

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
            log.info("try lock success! key = [{}], value=[{}]", key, value);
            Object result;

            try {
                result = pjp.proceed();
            }finally {
                redisLock.releaseLock(key, value);
                log.info("release lock success! key = [{}], value=[{}]", key, value);
            }

            return result;
        }else {
            log.info("try lock fail! key = [{}]", key);
            return new BaseResponse(CommonConstants.EX_OTHER_CODE, "重复请求，请稍后再试！");
        }
    }

    private String generateKey(String token, String uri) {
        return uri+"-"+token;
    }
}
