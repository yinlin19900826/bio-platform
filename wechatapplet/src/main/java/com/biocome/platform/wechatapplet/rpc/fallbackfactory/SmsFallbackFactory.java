package com.biocome.platform.wechatapplet.rpc.fallbackfactory;

import com.biocome.platform.wechatapplet.rpc.service.SmsRpc;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsAlarmCommonVo;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsResp;
import com.biocome.platform.wechatapplet.vo.duanxin.SmsReq;
import com.biocome.platform.wechatapplet.vo.duanxin.SmsResp;
import feign.hystrix.FallbackFactory;

/**
 * @author hxy
 * @date 2019/8/14 11:17
 */
public class SmsFallbackFactory implements FallbackFactory<SmsRpc> {
    @Override
    public SmsRpc create(Throwable throwable) {
        return new SmsRpc() {
            @Override
            public SmsResp sendMail(SmsReq model) {
                return new SmsResp();
            }
        };
    }
}
