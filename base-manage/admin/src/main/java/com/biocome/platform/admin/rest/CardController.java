package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.BuildBiz;
import com.biocome.platform.admin.biz.CardBiz;
import com.biocome.platform.admin.biz.EstateBiz;
import com.biocome.platform.admin.vo.card.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.constant.CardKindEnum;
import com.biocome.platform.admin.constant.CardStatusEnum;
import com.biocome.platform.admin.entity.Card;
import com.biocome.platform.admin.rpc.service.CardRpc;
import com.biocome.platform.admin.vo.admin.AdminCardVo;
import com.biocome.platform.admin.vo.admin.AdminSimpleCardVo;
import com.biocome.platform.admin.vo.card.*;
import com.biocome.platform.admin.vo.LesseeCardMsgResp;
import com.biocome.platform.admin.vo.lesseecard.LesseecardListReq;
import com.biocome.platform.admin.vo.lesseecard.LesseecardListResp;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.handler.ResultHandler;
import com.biocome.platform.common.msg.MapMsgAndTableResultResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.IdUtils;
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
    EstateBiz estateBiz;
    @Autowired
    BuildBiz buildBiz;
    @Autowired
    CardRpc cardRpc;
    @Autowired
    CardBiz cardBiz;


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

    @ApiOperation("获取租户开卡信息,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户姓名", paramType = "query"),
            @ApiImplicitParam(name = "idNumber", value = "证件号码", paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别", paramType = "query"),
            @ApiImplicitParam(name = "buildName", value = "房屋地址", paramType = "query"),
            @ApiImplicitParam(name = "estateName", value = "小区姓名", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/lesseeCardMsgPage", method = RequestMethod.GET)
    public MapMsgAndTableResultResponse<LesseeCardMsgResp> lesseeCardMsgPage(@RequestParam(defaultValue = "20") int pageSize,
                                                                             @RequestParam(defaultValue = "1") int pageNum,
                                                                             String username,
                                                                             String idNumber,
                                                                             Integer sex,
                                                                             String buildName,
                                                                             String estateName) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            map.put("estates", estateBiz.selectListAll());
            map.put("builds", buildBiz.selectListAll());
            Page<LesseeCardMsgResp> result = PageHelper.startPage(pageNum, pageSize);
            baseBiz.selectByAdditionForLesseeCardMsgPage(username, idNumber, sex, buildName, estateName);
            return new MapMsgAndTableResultResponse<LesseeCardMsgResp>(map, result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.info("------获取租户开卡信息时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            MapMsgAndTableResultResponse<LesseeCardMsgResp> resp = new MapMsgAndTableResultResponse<>();
            resp.setStatus(401);
            resp.setMessage("无法获取租户开卡信息，请联系管理员");
            return resp;
        }
    }

    @ApiOperation("增加卡")
    @ResponseBody
    @RequestMapping(value = "/addCard", method = RequestMethod.POST)
    public ObjectRestResponse addCard(@RequestBody AddCardParam param) {
        try {
            return baseBiz.addCard(param);
        }catch(Exception e){
            log.info(e.getMessage());
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "添加卡失败，错误信息："+e.getMessage());
        }
    }

    @ApiOperation("开卡")
    @ResponseBody
    @RequestMapping(value = "/openCard", method = RequestMethod.POST)
    public BaseRpcResponse openCard(@RequestBody OpenCardVo req) {
        return baseBiz.openCard(req);
    }

    @ApiOperation("批量开卡")
    @ResponseBody
    @RequestMapping(value = "/openbulk", method = RequestMethod.POST)
    public List<OpenblukResp> openbulk(@RequestBody OpenblukVo req) {
        return baseBiz.openbulk(req);
    }

    @ApiOperation("注销卡")
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseRpcResponse logout(@RequestBody LogoutCardVo req) {
        return baseBiz.logoutCard(req);
    }

    @ApiOperation("禁用/恢复卡")
    @ResponseBody
    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public BaseRpcResponse manager(@RequestBody ManagerCardVo req) {
        return baseBiz.managerCard(req);
    }

    @ApiOperation("根据楼栋编号获取租户和卡信息")
    @ResponseBody
    @RequestMapping(value = "/lesseecardList", method = RequestMethod.POST)
    public TableResultResponse<LesseecardListResp> lesseecardList(@RequestBody LesseecardListReq req) throws Exception{
        return baseBiz.selectLesseecardList(req);
    }

    @ApiOperation("管理员卡")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "admincode", value = "管理员编码", paramType = "query", required = true),
        @ApiImplicitParam(name = "cardNo", value = "门禁卡号", paramType = "query"),
        @ApiImplicitParam(name = "isalive", value = "门禁卡状态 0、注销，1、有效，2、发卡 3、禁用 4、黑名单", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/adminCardList", method = RequestMethod.GET)
    public TableResultResponse<AdminSimpleCardVo> adminCardList(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo,
                                             String admincode, String cardNo, Integer isalive) {
        return cardBiz.adminCardList(admincode, cardNo, isalive, pageSize, pageNo);
    }

    @ApiOperation("管理员管理的卡列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "admincode", value = "管理员编码", paramType = "query", required = true),
            @ApiImplicitParam(name = "cardNo", value = "门禁卡号", paramType = "query"),
            @ApiImplicitParam(name = "isalive", value = "门禁卡状态 0、注销，1、有效，2、发卡 3、禁用 4、黑名单", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/adminManageCardList", method = RequestMethod.GET)
    public TableResultResponse<AdminSimpleCardVo> adminManageCardList(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo,
                                                                String admincode, String cardNo, Integer isalive) {
        return cardBiz.adminManageCardList(admincode, cardNo, isalive, pageSize, pageNo);
    }

    @ApiOperation("所有管理卡(通卡)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "姓名", paramType = "query"),
            @ApiImplicitParam(name = "certNo", value = "证件号码", paramType = "query"),
            @ApiImplicitParam(name = "communityname", value = "社区名称", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/superCardList", method = RequestMethod.GET)
    public TableResultResponse<AdminCardVo> superCardList(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo,
                                            String username, String certNo, String communityname) {
        return cardBiz.superCardList(username, certNo, communityname, pageSize, pageNo);
    }

    @ApiOperation("门禁卡用户详情")
    @ApiImplicitParam(name = "cardNo", value = "门禁卡物理卡号", paramType = "query", required = true)
    @ResponseBody
    @RequestMapping(value = "/detail" , method = RequestMethod.GET)
    public ObjectRestResponse<Card> detail(String cardNo){
        ObjectRestResponse<Card> res = new ObjectRestResponse<>();
        try{
            Card card = cardBiz.selectByPhysicalCardNo(cardNo);
            res.setData(card);
            return res;
        }catch (Exception e){
            log.info("查找门禁卡详情失败，错误信息：数据库错误！");
            return res.failure();
        }
    }
}

