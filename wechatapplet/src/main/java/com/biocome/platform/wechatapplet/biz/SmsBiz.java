package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.wechatapplet.constant.WechatConstant;
import com.biocome.platform.wechatapplet.rpc.service.SmsRpc;
import com.biocome.platform.wechatapplet.service.SMSService;
import com.biocome.platform.wechatapplet.vo.duanxin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import sun.misc.UUDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @date 2019/8/14 10:37
 */
@Service
public class SmsBiz implements SMSService {
    @Value("${sms.account}")
    private String account;
    @Value("${sms.psw}")
    private String password;

    @Autowired
    private SmsRpc rpc;

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public ObjectRestResponse<SmsResp> sendSMS(String pre, String phone, String message) throws Exception {
        ObjectRestResponse<SmsResp> resp = new ObjectRestResponse<>();

        int code = +(int) ((Math.random() * 9 + 1) * 100000);
        SmsMessageDetail messageDetail = new SmsMessageDetail(message + code, phone);
        List<SmsMessageDetail> list = new ArrayList<>();
        list.add(messageDetail);
        SmsAccoutDetail detail = new SmsAccoutDetail(account, password, list);

        SmsReq req = new SmsReq(UUIDUtils.generateShortUuid(), "send", detail);
        SmsResp smsResp = rpc.sendMail(req);

        if (ValidateUtils.isNotEmpty(smsResp.getError())) {
            resp.setStatus(204);
            resp.setMessage("短信发送失败");
            return resp;
        }

        jedisCluster.setex(pre, WechatConstant.SMS_KEY_EXPIRE, String.valueOf(code));
        resp.setData(smsResp);
        return resp;
    }

    @Override
    public VertifyResp vertifyCode(String pre, String code) {
        VertifyResp resp = new VertifyResp();
        boolean result = false;
        String value = jedisCluster.get(pre);
        if (ValidateUtils.isNotEmpty(value)) {
            if (code.equals(value)) {
                result = true;
                resp.setMessage("短信验证码匹配");
                //删除验证码
                jedisCluster.del(pre);
            } else {
                resp.setMessage("短信验证码不匹配");
            }
        } else {
            resp.setMessage("未发送短信验证码或验证码已过期");
        }
        resp.setResult(result);
        return resp;
    }
}
