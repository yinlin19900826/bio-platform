package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.wechatapplet.constant.WechatConstant;
import com.biocome.platform.wechatapplet.rpc.service.WinxinPushService;
import com.biocome.platform.wechatapplet.service.SMSService;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsAlarmCommonVo;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsResp;
import com.biocome.platform.wechatapplet.vo.duanxin.VertifyResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @author hxy
 * @date 2019/8/7 18:23
 */
@Service
public class EnterpriseWeChatBiz implements SMSService {

    @Autowired
    private WinxinPushService winxinPushService;
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public BaseResponse sendSMS() throws Exception {
        BaseResponse resp = new BaseResponse();
        int password = (int) ((Math.random() * 9 + 1) * 100000);
        IacsAlarmCommonVo vo = new IacsAlarmCommonVo();
        vo.setAlarmMessage("短信验证码为：" + password);
        vo.setAlarmName("短信发送");
        vo.setHappenTime(DateUtils.getCurrentTime());
        IacsResp iacsResp = winxinPushService.alarmWeixin(vo);
        if ("0".equals(iacsResp.getResult())) {
            resp.setStatus(204);
            resp.setMessage("短信发送失败");
            return resp;
        }
        jedisCluster.setex("sms_" + BaseContextHandler.getUsercode(), WechatConstant.SMS_KEY_EXPIRE, String.valueOf(password));
        return resp;
    }

    @Override
    public VertifyResp vertifyCode(String code) {
        VertifyResp resp = new VertifyResp();
        boolean result = false;
        String value = jedisCluster.get("sms_" + BaseContextHandler.getUsercode());
        if (ValidateUtils.isNotEmpty(value)) {
            if (code.equals(value)) {
                result = true;
                resp.setMessage("短信验证码匹配");
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
