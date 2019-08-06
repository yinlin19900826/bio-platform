package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.wechatapplet.biz.CardManageBiz;
import com.biocome.platform.wechatapplet.biz.RefundRentBiz;
import com.biocome.platform.wechatapplet.vo.common.CardManageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RefundRentController
 * @Author: yinlin
 * @Date: 2019/8/1 13:49
 * @Description:
 */
@Controller
@RequestMapping("refund")
@Api(value = "退租操作", tags = {"退租操作"})
public class RefundRentController extends BaseController<CardManageBiz, CardManageVo> {

    private Logger log = LoggerFactory.getLogger(RefundRentController.class);

    @Autowired
    private RefundRentBiz refundRentBiz;

    @ApiOperation("单个租户退租(0失败，1成功)")
    @ApiImplicitParams({@ApiImplicitParam(name = "cardNo", value = "卡号", paramType = "path"),
            @ApiImplicitParam(name = "username", value = "租户姓名", paramType = "path")})
    @ResponseBody
    @RequestMapping(value = "/rent/{cardNo}/{username}", method = RequestMethod.POST)
    public BaseResponse refundRent(@PathVariable String cardNo, @PathVariable String username) {
        try {
            return refundRentBiz.refundRent(cardNo, username);
        } catch (Exception e) {
            log.info("单个租户退租失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("单个租户退租失败!");
        }
    }

    @ApiOperation("全部退租(0失败，1成功)")
    @ApiImplicitParams({@ApiImplicitParam(name = "cardNo", value = "卡号", paramType = "path"),
            @ApiImplicitParam(name = "username", value = "租户姓名", paramType = "path")})
    @ResponseBody
    @RequestMapping(value = "/rentall/{cardNo}/{username}", method = RequestMethod.POST)
    public BaseResponse refundAllRent(@PathVariable String cardNo, @PathVariable String username) {
        try {
            return refundRentBiz.refundAllRent(cardNo, username);
        } catch (Exception e) {
            log.info("全部退租失败，错误信息为：{}", e.getMessage());
            //return AdminCommonConstant.BOOLEAN_NUMBER_FALSE;
            return new ObjectRestResponse().customError("全部退租失败!");
        }
    }

}
