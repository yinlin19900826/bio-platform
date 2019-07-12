package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.GateCardLogBiz;
import com.biocome.platform.basemanager.entity.GateCardLog;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ClassName: GateCardLogController
 * @Author: yinlin
 * @Date: 2019/5/9 15:36
 * @Description:
 */
@Controller
@RequestMapping("gateCardLog")
@Api(value = "门禁卡", tags = {"门禁卡操作日志"})
public class GateCardLogController extends BaseController<GateCardLogBiz, GateCardLog> {

    @Autowired
    protected GateCardLogBiz gateCardLogBiz;

    @ApiOperation("获取门禁卡操作日志列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "physicalcardNo", value = "物理卡号", paramType = "query"),
            @ApiImplicitParam(name = "optName", value = "操作人", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<GateCardLog> list(@RequestParam(defaultValue = "20") int pageSize,
                                                 @RequestParam(defaultValue = "1") int pageNum,
                                                 String physicalcardNo, String optName) {
        return gateCardLogBiz.selectByAttribute(pageSize, pageNum, physicalcardNo, optName);
    }
}
