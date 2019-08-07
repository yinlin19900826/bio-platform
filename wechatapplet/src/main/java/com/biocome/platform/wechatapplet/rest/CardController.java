package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.CardVoBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: CardController
 * @Author: shenlele
 * @Date: 2019/7/30 10:35
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/card")
@Api(value = "卡相关操作", tags = {"卡相关操作"})
public class CardController {

    private final CardVoBiz biz;

    @Autowired
    public CardController(CardVoBiz biz) {
        this.biz = biz;
    }

    @ApiOperation("获取所有管理员卡")
    @ResponseBody
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ObjectRestResponse adminCard() {
        try {
            String code = BaseContextHandler.getUsercode();
            return biz.selectCardsByCode(code);
        } catch (Exception e) {
            log.info("获取所有管理员卡失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("获取卡信息失败!");
        }
    }

    @ApiOperation("只用于管理员下发卡")

    @ApiImplicitParams({@ApiImplicitParam(name = "userCode", value = "用户编码", paramType = "path"),
            @ApiImplicitParam(name = "cardNo", value = "卡号", paramType = "path"),
            @ApiImplicitParam(name = "buildCode", value = "楼栋编号", paramType = "path")})
    @ResponseBody
    @RequestMapping(value = "/sendDown/{userCode}/{cardNo}/{buildCode}", method = RequestMethod.POST)
    public ObjectRestResponse cardOperation(@PathVariable String userCode, @PathVariable String cardNo, @PathVariable String buildCode) {
        try {
            return biz.cardOperation(userCode, cardNo, buildCode);
        } catch (Exception e) {
            log.info("管理员下发卡失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("发卡失败!");
        }
    }
}
