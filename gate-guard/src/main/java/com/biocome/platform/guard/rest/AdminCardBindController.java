package com.biocome.platform.guard.rest;

import com.biocome.platform.guard.vo.admin.AdminCardBindVo;
import com.biocome.platform.guard.vo.admin.AdminCardVo;
import com.biocome.platform.guard.vo.admin.AdminSimpleCardVo;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "管理员卡", tags = {"管理员卡操作"})
@Controller
@RequestMapping("adminCardBind")
public class AdminCardBindController extends BaseController<AdminCardBindBiz, AdminCardBind> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminCardBindBiz adminCardBindBiz;

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
    public ObjectRestResponse batchDeliverAdminCard(@RequestParam List<AdminSimpleCardVo> list){
        return adminCardBindBiz.batchDeliverAdminCardNotify(list);
    }

    @ApiOperation("管理员批量注销卡")
    @ResponseBody
    @RequestMapping(value = "/batchUnregisterAdminCard" , method = RequestMethod.POST)
    public BaseRpcResponse batchUnregisterAdminCard(@RequestParam List<AdminSimpleCardVo> list){
        return adminCardBindBiz.batchUnregisterAdminCard(list);
    }
}