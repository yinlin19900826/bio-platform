package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.UserLogBiz;
import com.biocome.platform.basemanager.entity.UserLog;
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
@RequestMapping("userLog")
@Api(value = "用户", tags = {"用户操作日志"})
public class UserLogController extends BaseController<com.biocome.platform.basemanager.biz.UserLogBiz, UserLog> {

    @Autowired
    protected UserLogBiz UserLogBiz;

    @ApiOperation("获取用户操作日志列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "optBusiness", value = "操作业务", paramType = "query"),
            @ApiImplicitParam(name = "optName", value = "操作人", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<UserLog> list(@RequestParam(defaultValue = "20") int pageSize,
                                             @RequestParam(defaultValue = "1") int pageNum,
                                             String optBusiness, String optName) {
            return UserLogBiz.selectByAttribute(pageSize, pageNum, optBusiness, optName);
    }
}
