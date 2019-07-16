package com.biocome.platform.basemanager.rest;

import com.biocome.platform.inter.basemanager.biz.CardBiz;
import com.biocome.platform.inter.basemanager.biz.DictBiz;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.basemanager.constant.CardKindEnum;
import com.biocome.platform.basemanager.constant.CardStatusEnum;
import com.biocome.platform.basemanager.vo.resp.card.CardInfoResp;
import com.biocome.platform.common.handler.ResultHandler;
import com.biocome.platform.common.msg.MapMsgAndTableResultResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hxy
 * @create:2019/5/7 15:25
 */
@Controller
@RequestMapping("/card")
@Api(value = "门禁卡", tags = {"门禁卡操作"})
public class CardController extends BaseController<CardBiz, Card> {

    Logger log = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private DictBiz dictBiz;

    @ApiOperation("获取门禁卡,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键ID", paramType = "query"),
            @ApiImplicitParam(name = "cardno", value = "逻辑卡号或物理卡号", paramType = "query"),
            @ApiImplicitParam(name = "cardType", value = "卡类别", paramType = "query"),
            @ApiImplicitParam(name = "cardStatus", value = "卡状态", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public MapMsgAndTableResultResponse<CardInfoResp> page(@RequestParam(defaultValue = "20") int pageSize,
                                                           @RequestParam(defaultValue = "1") int pageNum,
                                                           Integer id,
                                                           String cardno,
                                                           String cardType,
                                                           String cardStatus) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            if (id == null) {
                map.put("cardType", CardKindEnum.getAllCardType());
                map.put("cardStatus", CardStatusEnum.getAllCardStatus());
            }
            Page<CardInfoResp> result = PageHelper.startPage(pageNum, pageSize);
            baseBiz.selectByAdditionForCardList(cardno, cardType, cardStatus, id);
            return new MapMsgAndTableResultResponse<CardInfoResp>(map, result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.info("------获取门禁卡列表时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            MapMsgAndTableResultResponse<CardInfoResp> resp = new MapMsgAndTableResultResponse<>();
            resp.setStatus(401);
            resp.setMessage("无法获取门禁卡列表，请联系管理员");
            return resp;
        }
    }

    @ApiOperation("根据主键物理删除卡")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse deleteCard(@RequestBody String ids) {
        ObjectRestResponse resp = new ObjectRestResponse();
        try {
            List<Integer> deviceIds = IdUtils.getIds(ids);
            int result = baseBiz.deleteCard(deviceIds);
            return ResultHandler.objectRestResponseHandle(resp, result);
        } catch (Exception e) {
            log.info("------删除时发生异常------");
            log.info(e.getMessage());
            return resp.error();
        }
    }

}

