package com.biocome.platform.guard.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;

/**
 * @ClassName: RedisLock
 * @Author: shenlele
 * @Date: 2019/7/11 09:24
 * @Description: redis锁
 */
@Component
public class RedisLock {
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    /**
     * 当前设置 过期时间单位, EX = seconds; PX = milliseconds
     */
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    /**
     * if get(key) == value return del(key)
     */
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    @Autowired
    JedisCluster jedisCluster;


    /**
     * 尝试获取锁
     * @param key
     * @param value
     * @param second
     * @return
     */
    public boolean tryLock(String key, String value, long second){
        String result = jedisCluster.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, second);
        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放锁
     * @param key
     * @param value
     * @return
     */
    public boolean releaseLock(String key, String value){
        Object result = jedisCluster.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(value));
        return result.equals(RELEASE_SUCCESS);
    }
}
