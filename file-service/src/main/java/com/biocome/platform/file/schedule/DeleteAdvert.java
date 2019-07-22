package com.biocome.platform.file.schedule;

import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.file.biz.MinioTemplateBiz;
import com.biocome.platform.file.constant.CommonConstant;
import com.biocome.platform.file.vo.FileVo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class DeleteAdvert {


    @Autowired
    private MinioTemplateBiz biz;
    @Autowired
    private JedisCluster jedisCluster;

    @Scheduled(cron = "${job.deleteFile}")
    public void deleteFile() {
        log.info("---------------定时删除文件服务器文件计划开始---------------");
        try {
            if (jedisCluster.exists(CommonConstant.DELETE_KEY)) {
                Long len = jedisCluster.llen(CommonConstant.DELETE_KEY);
                Long l = 0L;
                while (l < len) {
                    l = l + 1L;
                    FileVo fileVo = null;
                    try {
                        fileVo = JsonUtils.jsonToBean(jedisCluster.rpop(CommonConstant.DELETE_KEY), FileVo.class);
                        if (ValidateUtils.isNotEmpty(fileVo)) {
                            biz.removeObject(fileVo.getTopic(), fileVo.getFilename());
                        }
                    } catch (Exception e) {
                        if (ValidateUtils.isNotEmpty(fileVo)) {
                            String str = JsonUtils.beanToJson(fileVo);
                            log.error("删除文件服务器文件失败，重新放入redis中！错误地址为：" + str);
                            jedisCluster.lpush(CommonConstant.DELETE_KEY, str);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("定时删除文件服务器文件计划异常，错误信息为：" + e.getMessage());
        }
        log.info("---------------定时删除文件服务器文件计划结束---------------");
    }
}
