package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.wechatapplet.biz.CardManageBiz;
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
 * @ClassName: CardManageController
 * @Author: yinlin
 * @Date: 2019/7/31 13:49
 * @Description:
 */
@Controller
@RequestMapping("cardmanage")
@Api(value = "门禁卡管理", tags = {"门禁卡管理"})
public class CardManageController extends BaseController<CardManageBiz, CardManageVo> {

    private Logger log = LoggerFactory.getLogger(CardManageController.class);

    @Autowired
    private CardManageBiz cardManageBiz;

    @ApiOperation("获取不同权限用户下的所有门禁卡")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<CardManageVo> list(@RequestParam(defaultValue = "20") int pageSize,
                                           @RequestParam(defaultValue = "1") int pageNum) {
        String usercode = BaseContextHandler.getUsercode();
        return cardManageBiz.selectByAttribute(pageSize, pageNum,usercode);
    }

    @ApiOperation("不同权限用户挂失其丢失的门禁卡(0失败，1成功)")

    @ApiImplicitParams(@ApiImplicitParam(name = "physicalcardno", value = "物理卡号", paramType = "path"))
    @ResponseBody
    @RequestMapping(value = "/loss", method = RequestMethod.GET)
    public BaseResponse cardOperation(String physicalcardno) {
        try {
            return cardManageBiz.cardLossOperation(physicalcardno);
            //return null;
        } catch (Exception e) {
            log.info("挂失卡操作失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("挂失卡操作失败!");
        }
    }

}
