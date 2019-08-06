package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.vo.device.DeviceInfoResp;
import com.biocome.platform.wechatapplet.biz.VisitorBiz;
import com.biocome.platform.wechatapplet.rpc.service.OpenDoorPasswordRpc;
import com.biocome.platform.wechatapplet.vo.visitor.GetRecordReq;
import com.biocome.platform.wechatapplet.vo.visitor.GetRecordResp;
import com.biocome.platform.wechatapplet.vo.visitor.OpendoorpasswordResp;
import com.biocome.platform.wechatapplet.vo.visitor.VisitorPasswordReq;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * @author hxy
 * @date 2019/7/30 15:29
 */
@RestController
@RequestMapping("/visit")
@Api(value = "访客记录相关", tags = {"访客记录相关"})
public class VisitorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OpenDoorPasswordRpc openDoorPasswordRpc;
    @Autowired
    private VisitorBiz biz;
    @Value("${gateguard.url}")
    private String url;

    @ApiOperation("发送动态密码")
    @PostMapping("/password")
    public ObjectRestResponse<String> password(@RequestBody VisitorPasswordReq req) {
        ObjectRestResponse resp = new ObjectRestResponse();
        String password = null;
        if (ValidateUtils.isEmpty(req.getUsercode())) {
            req.setUsercode(BaseContextHandler.getUsercode());
        }
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

    @ApiOperation("访客记录列表")
    @GetMapping("/getRecord")
    public TableResultResponse<GetRecordResp> getRecord(@RequestParam(defaultValue = "20") int pageSize,
                                                        @RequestParam(defaultValue = "1") int pageNum,
                                                        @RequestParam("status") String status) {
        GetRecordReq req = new GetRecordReq();
        req.setStatus(status);
        if (ValidateUtils.isEmpty(req.getUsercode())){
            req.setUsercode(BaseContextHandler.getUsercode());
        }
        Page<GetRecordResp> result = PageHelper.startPage(pageNum, pageSize);
        biz.getRecord(req);
        return new TableResultResponse<>(result.getTotal(), result.getResult());
    }
}
