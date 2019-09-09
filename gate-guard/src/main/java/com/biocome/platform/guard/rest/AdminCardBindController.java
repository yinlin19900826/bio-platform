package com.biocome.platform.guard.rest;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.guard.biz.AdminCardBindBiz;
import com.biocome.platform.guard.entity.AdminCardBind;
import com.biocome.platform.guard.vo.admin.AdminCardBindVo;
import com.biocome.platform.guard.vo.admin.AdminCardVo;
import com.biocome.platform.guard.vo.admin.AdminSimpleCardVo;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.inter.basemanager.biz.CardBiz;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.vo.card.AddCardParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "管理员卡", tags = {"管理员卡操作"})
@Controller
@RequestMapping("adminCardBind")
@Slf4j
public class AdminCardBindController extends BaseController<AdminCardBindBiz, AdminCardBind> {

    @Autowired
    AdminCardBindBiz adminCardBindBiz;
    @Autowired
    CardBiz cardBiz;

    @ApiOperation("所有管理员卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardNo", value = "门禁卡号", paramType = "query"),
            @ApiImplicitParam(name = "isalive", value = "门禁卡状态 0、注销，1、有效，2、发卡 3、禁用 4、黑名单", paramType = "query"),
            @ApiImplicitParam(name = "buildcode", value = "楼栋编码", paramType = "query", required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/adminCardListOnBuilding", method = RequestMethod.GET)
    public TableResultResponse<AdminCardVo> adminCardListOnBuilding(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo,
                                                                    String buildcode) {
        return adminCardBindBiz.adminCardListOnBuilding(buildcode, pageSize, pageNo);
    }

    @ApiOperation("管理员门禁卡绑定楼栋列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usercode", value = "用户编码", paramType = "query", required = true),
            @ApiImplicitParam(name = "cardNo", value = "门禁卡物理卡号", paramType = "query", required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/cardBindBuildingList" , method = RequestMethod.GET)
    public TableResultResponse<AdminCardBindVo> cardBindBuildingList(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo,
                                                                     String usercode, String cardNo){
        return adminCardBindBiz.cardBindBuildingList(usercode, cardNo, pageSize, pageNo);
    }

    @ApiOperation("管理员门禁卡绑定更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usercode", value = "用户编码", paramType = "query", required = true),
            @ApiImplicitParam(name = "cardNo", value = "门禁卡物理卡号", paramType = "query", required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/bindBuildingCard" , method = RequestMethod.POST)
    public ObjectRestResponse bindBuildingCard(String usercode, String cardNo,
                                                              @RequestBody List<AdminCardBindVo> cardVos){
        return adminCardBindBiz.bindBuildingCard(usercode, cardNo, cardVos);
    }

    @ApiOperation("注销卡")
    @ResponseBody
    @RequestMapping(value = "/unregisterCard" , method = RequestMethod.POST)
    public ObjectRestResponse unregisterCard(@RequestBody AdminCardVo vo){
        return adminCardBindBiz.unregisterCard(vo);
    }

    @ApiOperation("移除卡")
    @ResponseBody
    @RequestMapping(value = "/removeCard" , method = RequestMethod.POST)
    public ObjectRestResponse removeCard(@RequestBody AdminCardVo vo){
        return adminCardBindBiz.removeCard(vo);
    }

    @ApiOperation("管理员批量发卡")
    @ResponseBody
    @RequestMapping(value = "/batchDeliverAdminCard" , method = RequestMethod.POST)
    public ObjectRestResponse batchDeliverAdminCard(@RequestBody List<AdminSimpleCardVo> list){
        return adminCardBindBiz.batchDeliverAdminCardNotify(list);
    }

    @ApiOperation("管理员批量注销卡")
    @ResponseBody
    @RequestMapping(value = "/batchUnregisterAdminCard" , method = RequestMethod.POST)
    public BaseRpcResponse batchUnregisterAdminCard(@RequestBody List<AdminSimpleCardVo> list){
        return adminCardBindBiz.batchUnregisterAdminCard(list);
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
        return adminCardBindBiz.adminCardList(admincode, cardNo, isalive, pageSize, pageNo);
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
        return adminCardBindBiz.adminManageCardList(admincode, cardNo, isalive, pageSize, pageNo);
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
        return adminCardBindBiz.superCardList(username, certNo, communityname, pageSize, pageNo);
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