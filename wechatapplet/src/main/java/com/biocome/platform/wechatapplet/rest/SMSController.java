package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.wechatapplet.constant.AppConstant;
import com.biocome.platform.wechatapplet.constant.WechatConstant;
import com.biocome.platform.wechatapplet.service.SMSService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("sendResetSMS")
    public BaseResponse sendResetSMS(@RequestParam String phone) {
        BaseResponse resp = new BaseResponse();
        try {
            if (ValidateUtils.isEmpty(phone)) {
                resp = new BaseResponse(CommonConstants.EX_APP_SMS_PHONE_NULL, "电话号码不能为空！");
            }
            resp = smsService.sendSMS(AppConstant.SMS_RESET_PASSWORD_PRE + "_" + phone, phone, WechatConstant.SENDMAIL_CONTENT);
        } catch (Exception e) {
            log.error("下发短信失败：{}", e.getMessage());
            resp.setStatus(204);
            resp.setMessage("下发短信失败，请稍后再试");
        }
        return resp;
    }

    @GetMapping("sendSMS")
    public BaseResponse sendSMS(@RequestParam String pre, @RequestParam String phone) {
        BaseResponse resp = new BaseResponse();
        try {
            if (StringUtils.isBlank(phone)) {
                return new BaseResponse(CommonConstants.EX_APP_SMS_PHONE_NULL, "电话号码不能为空！");
            }
            resp = smsService.sendSMS(pre + BaseContextHandler.getUsercode(), phone, WechatConstant.SENDMAIL_CONTENT);
        } catch (Exception e) {
            log.error("下发短信失败：{}", e.getMessage());
            resp.setStatus(204);
            resp.setMessage("下发短信失败，请稍后再试");
        }
        return resp;
    }

}
