package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.wechatapplet.rpc.fallbackfactory.SmsFallbackFactory;
import com.biocome.platform.wechatapplet.vo.duanxin.SmsReq;
import com.biocome.platform.wechatapplet.vo.duanxin.SmsResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hxy
 * @date 2019/8/14 11:12
 */
@FeignClient(name="sms-push", url="${sms.sendurl}",fallbackFactory = SmsFallbackFactory.class)
public interface SmsRpc {
    @RequestMapping(value={"/sms3_api/jsonapi/jsonrpc2.jsp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
    public SmsResp sendMail(@RequestBody SmsReq model);
}
