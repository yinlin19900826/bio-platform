package com.biocome.platform.wechatapplet.controller;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.entity.VisitorRecord;
import com.biocome.platform.wechatapplet.biz.VisitorBiz;
import com.biocome.platform.wechatapplet.rpc.service.OpenDoorPasswordRpc;
import com.biocome.platform.wechatapplet.vo.visitor.OpendoorpasswordResp;
import com.biocome.platform.wechatapplet.vo.visitor.VisitorPasswordReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 15:29
 */
@RestController
@RequestMapping("/visit")
public class VisitorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OpenDoorPasswordRpc openDoorPasswordRpc;
    @Autowired
    private VisitorBiz biz;
    @Value("${gateguard.url}")
    private String url;

    @PostMapping("/password")
    public ObjectRestResponse<String> password(@RequestBody VisitorPasswordReq req) {
        ObjectRestResponse resp = new ObjectRestResponse();
        String password = null;

        try {
            //远程调用，向小平台发送开门密码
            OpendoorpasswordResp opendoorpassword = biz.opendoorpassword(req);
            password = opendoorpassword.getPassword();
            if (!CommonConstants.RESP_RESULT_SUCCESS.equals(opendoorpassword.getResult())) {
                resp.failure();
                resp.setMessage("下发开门密码失败");
                return resp;
            }

            //存库
            int result = biz.insertRecord(opendoorpassword, req);

            if (result < 1) {
                resp.failure();
                resp.setMessage("存库失败");
                return resp;
            }
            //预留发送短信或直接推送回前台


        } catch (Exception e) {
            logger.error("发生异常，异常信息为:{}", e.getMessage());
            resp.failure();
            resp.setMessage("下发开门密码失败");
            return resp;
        }
        resp.setData(password);
        return resp;
    }
    @PostMapping("/getRecord")
    public List<VisitorRecord> getRecord(@RequestBody ){

    }
}
