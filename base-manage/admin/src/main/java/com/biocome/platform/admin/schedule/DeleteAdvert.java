package com.biocome.platform.admin.schedule;

import com.biocome.platform.admin.constant.AdminCommonConstant;
import com.biocome.platform.admin.fastdfs.FastDfsClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * @ClassName: DeleteAdvert
 * @Author: shenlele
 * @Date: 2019/5/31 09:39
 * @Description:
 */
@Component
public class DeleteAdvert {

    private Logger log = LoggerFactory.getLogger(DeleteAdvert.class);

    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private FastDfsClientWrapper fastDfsClientWrapper;

    @Scheduled(cron = "${job.deleteAdvert}")
    public void deleteAdvert() {
        log.info("---------------定时删除广告素材计划开始---------------");
        try {
            if (jedisCluster.exists(AdminCommonConstant.DELETE_KEY)) {
                Long len = jedisCluster.llen(AdminCommonConstant.DELETE_KEY);
                Long l = 0L;
                while (l < len) {
                    l = l + 1L;
                    String filePath = null;
                    try {
                        filePath = jedisCluster.rpop(AdminCommonConstant.DELETE_KEY);
                        fastDfsClientWrapper.deleteFile(filePath);
                    } catch (Exception e) {
                        log.error("广告素材删除失败，重新放入redis中，错误文件地址为：" + filePath);
                        jedisCluster.lpush(AdminCommonConstant.DELETE_KEY, filePath);
                    }
                }
            }
        } catch (Exception e) {
            log.error("定时删除广告素材计划异常，错误信息为：" + e.getMessage());
        }
        log.info("---------------定时删除广告素材计划结束---------------");
    }
}
