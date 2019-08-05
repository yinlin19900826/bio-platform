package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.wechatapplet.biz.AuditOperateBiz;
import com.biocome.platform.wechatapplet.biz.RefundRentBiz;
import com.biocome.platform.wechatapplet.vo.common.AuditOperateVo;
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
public class AuditOperateController extends BaseController<AuditOperateBiz, AuditOperateVo> {

    private Logger log = LoggerFactory.getLogger(AuditOperateController.class);

    @Autowired
    private AuditOperateBiz auditOperateBiz;

    @ApiOperation("获取待审核列表和已审核列表")
    @ApiImplicitParams(@ApiImplicitParam(name = "isaudit", value = "是否审核", paramType = "query"))
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<AuditOperateVo> list(@RequestParam(defaultValue = "20") int pageSize,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                 int isaudit) {
        return auditOperateBiz.selectByAttribute(pageSize, pageNum,isaudit);
    }

    @ApiOperation(" 根据用户名更改审核状态（0失败，1成功）")
    @ResponseBody
    @PostMapping("/change")
    public String updateIsAudit(String username ,int isaudit) {
        try {
            return auditOperateBiz.updateIsAudit(username,isaudit);
        } catch (Exception e) {
            log.info("根据用户名更改审核状态失败：{}", e.getMessage());
            return AdminCommonConstant.BOOLEAN_NUMBER_FALSE;
        }
    }
}
