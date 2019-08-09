package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.wechatapplet.service.SMSService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hxy
 * @date 2019/8/7 18:31
 */
@RestController
@RequestMapping("sms")
@Api(value = "短信相关", tags = {"短信相关"})
public class SMSController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SMSService smsService;

    @GetMapping("sendSMS")
    public BaseResponse sendSMS() {
        BaseResponse resp = new BaseResponse();
        try {
            resp = smsService.sendSMS();
        } catch (Exception e) {
            log.error("下发短信失败：{}", e.getMessage());
            e.printStackTrace();
            resp.setStatus(204);
            resp.setMessage("下发短信失败，请稍后再试");
        }
        return resp;
    }

}
