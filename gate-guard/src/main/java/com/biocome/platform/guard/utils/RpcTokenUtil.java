package com.biocome.platform.guard.utils;

import com.biocome.platform.guard.constant.BrandEnum;
import com.biocome.platform.guard.vo.otherrpc.TokenResp;
import com.biocome.platform.guard.vo.otherrpc.TokenVo;
import com.biocome.platform.guard.rpc.service.OtherRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.net.URI;

/**
 * @author hxy
 * @date 2019/5/27 18:43
 */
@Component
@Slf4j
public class RpcTokenUtil {
    @Autowired
    JedisCluster jedisCluster;
    @Autowired
    OtherRpc otherRpc;
    @Autowired
    UriUtil uriUtil;

    /**
     *
     * @param brand 厂家编号
     * @return
     */
    public String getRpcToken(String brand) {
        String brandName = BrandEnum.getBrandByBrandCode(brand);
        String token = jedisCluster.get(brandName);
        if (StringUtils.isNotBlank(token)) {
            return token;
        } else {
            //如果获取不到，重新发起请求获取token
            URI uri = uriUtil.getUriByBrand(brand);
            TokenResp tokenResp = otherRpc.token(uri, new TokenVo("xinyi", "123"));
            log.info("获取的token{}", tokenResp.getToken());
            if (StringUtils.isNotBlank(tokenResp.getToken()) && StringUtils.isNotBlank(tokenResp.getExpires())) {
                jedisCluster.set(brand, tokenResp.getToken());
                jedisCluster.expire(brand, Integer.valueOf(tokenResp.getExpires()) * 60 * 60);
            }
            return tokenResp.getToken();
        }
    }
}
