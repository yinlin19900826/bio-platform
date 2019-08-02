package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.wechatapplet.biz.AuditOperateBiz;
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
 * @ClassName: AuditOperateController
 * @Author: yinlin
 * @Date: 2019/8/2 13:49
 * @Description:
 */
@Controller
@RequestMapping("audit")
@Api(value = "审核操作", tags = {"审核操作"})
public class AuditOperateController extends BaseController<AuditOperateBiz, Lessee> {

    private Logger log = LoggerFactory.getLogger(AuditOperateController.class);

    @Autowired
    private AuditOperateBiz auditOperateBiz;

    @ApiOperation("获取待审核列表和已审核列表")
    @ApiImplicitParams(@ApiImplicitParam(name = "isaudit", value = "是否审核", paramType = "query"))
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<Lessee> list(@RequestParam(defaultValue = "20") int pageSize,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                 int isaudit) {
        return auditOperateBiz.selectByAttribute(pageSize, pageNum,isaudit);
    }

   /* @ApiOperation("单个租户退租(0失败，1成功)")
    @ApiImplicitParams({@ApiImplicitParam(name = "cardNo", value = "卡号", paramType = "path"),
            @ApiImplicitParam(name = "username", value = "租户姓名", paramType = "path")})
    @ResponseBody
    @RequestMapping(value = "/rent/{cardNo}/{username}", method = RequestMethod.POST)
    public String refundRent(@PathVariable String cardNo, @PathVariable String username) {
        try {
            return refundRentBiz.refundRent(cardNo, username);
        } catch (Exception e) {
            log.info("单个租户退租失败，错误信息为：{}", e.getMessage());
            return AdminCommonConstant.BOOLEAN_NUMBER_FALSE;
        }
    }

    @ApiOperation("全部退租(0失败，1成功)")
    @ApiImplicitParams({@ApiImplicitParam(name = "cardNo", value = "卡号", paramType = "path"),
            @ApiImplicitParam(name = "username", value = "租户姓名", paramType = "path")})
    @ResponseBody
    @RequestMapping(value = "/rentall/{cardNo}/{username}", method = RequestMethod.POST)
    public String refundAllRent(@PathVariable String cardNo, @PathVariable String username) {
        try {
            return refundRentBiz.refundAllRent(cardNo, username);
        } catch (Exception e) {
            log.info("全部退租失败，错误信息为：{}", e.getMessage());
            return AdminCommonConstant.BOOLEAN_NUMBER_FALSE;
        }
    }
*/
}
