package com.biocome.platform.admin.schedule;

import com.biocome.platform.admin.biz.DictBiz;
import com.biocome.platform.admin.entity.Dictionary;
import com.biocome.platform.admin.rpc.service.OtherRpc;
import com.biocome.platform.admin.utils.UriUtil;
import com.biocome.platform.admin.vo.otherRpc.TokenResp;
import com.biocome.platform.admin.vo.otherRpc.TokenVo;
import com.biocome.platform.common.util.ThreadManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.net.URI;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/23 15:08
 */
@Component
public class TokenForBrand {
    Logger log = LoggerFactory.getLogger(TokenForBrand.class);
    @Autowired
    JedisCluster jedisCluster;
    @Autowired
    OtherRpc otherRpc;
    @Autowired
    DictBiz dictBiz;
    @Autowired
    UriUtil uriUtil;

    //@Scheduled(cron = "*/60 * * * *")
    @Scheduled(cron = "${job.getToken}")
    public void getTokenFromManufacturers() {
        Dictionary entity = new Dictionary();
        entity.setDictCode("008");
        List<Dictionary> brands = dictBiz.selectList(entity);
        for (Dictionary dict : brands) {
            ThreadManager.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    URI uri = uriUtil.getUriByBrand(dict.getDictKey());
                    TokenResp tokenResp = otherRpc.token(uri, new TokenVo("xinyi", "123"));
                    log.info("获取的token{}", tokenResp.getToken());
                    if (StringUtils.isNotBlank(tokenResp.getToken()) && StringUtils.isNotBlank(tokenResp.getExpires())) {
                        jedisCluster.set(dict.getComment(), tokenResp.getToken());
                        jedisCluster.expire(dict.getComment(), Integer.valueOf(tokenResp.getExpires()) * 60 * 60);
                    }
                }
            });
        }
    }
}
