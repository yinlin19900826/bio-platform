package com.biocome.platform.wechatapplet.service;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.wechatapplet.vo.duanxin.VertifyResp;

/**
 * @author hxy
 * @date 2019/8/8 09:05
 */
public interface SMSService {
    public BaseResponse sendSMS() throws Exception;

    VertifyResp vertifyCode(String code);
}
