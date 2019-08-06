package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.wechatapplet.biz.FaultDeclareBiz;
import com.biocome.platform.wechatapplet.entity.Fault;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: FaultDeclareController
 * @Author: yinlin
 * @Date: 2019/7/30 13:49
 * @Description:
 */
@Controller
@RequestMapping("faultdeclare")
@Api(value = "故障申报", tags = {"故障申报"})
public class FaultDeclareController extends BaseController<FaultDeclareBiz, Fault> {

    private Logger log = LoggerFactory.getLogger(FaultDeclareController.class);

    @Autowired
    private FaultDeclareBiz faultDeclareBiz;

    @ApiOperation("故障申报")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse faultDeclare(@RequestBody Fault fault) {
        ObjectRestResponse res;
        try {
            res = faultDeclareBiz.insertFaultDeclare(fault);
        } catch (Exception e) {
            log.error("故障申报失败！错误信息为：" + e.getMessage());
            //return new ObjectRestResponse(204, "保存失败！");
            return new ObjectRestResponse().customError("故障申报失败!");
        }
        return res;
    }
}
