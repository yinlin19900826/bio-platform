package com.biocome.platform.wechatapplet.service;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.vo.duanxin.SmsResp;
import com.biocome.platform.wechatapplet.vo.duanxin.VertifyResp;

/**
 * @author hxy
 * @date 2019/8/8 09:05
 */
public interface SMSService {

    ObjectRestResponse<SmsResp> sendSMS(String pre, String phone , String message) throws Exception;

    VertifyResp vertifyCode(String pre, String code);
}
