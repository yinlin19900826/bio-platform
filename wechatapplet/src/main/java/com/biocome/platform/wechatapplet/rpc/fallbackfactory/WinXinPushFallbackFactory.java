package com.biocome.platform.wechatapplet.rpc.fallbackfactory;

import com.biocome.platform.wechatapplet.rpc.service.WinxinPushService;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsAlarmCommonVo;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsResp;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class WinXinPushFallbackFactory implements FallbackFactory<WinxinPushService> {
    private Logger log = LoggerFactory.getLogger(WinXinPushFallbackFactory.class);

    @Override
    public WinxinPushService create(Throwable e) {
        log.error("调用微信服务接口失败:{}", e.getMessage());
        return new WinxinPushService() {

            @Override
            public IacsResp alarmWeixin(IacsAlarmCommonVo model) {
                return null;
            }

        };
    }
}
