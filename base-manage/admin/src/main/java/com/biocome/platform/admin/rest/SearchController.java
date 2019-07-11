package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.AdminBuildingBindBiz;
import com.biocome.platform.admin.entity.AdminBuildingBind;
import com.biocome.platform.admin.vo.card.CardInfoResp;
import com.biocome.platform.common.msg.MapMsgAndTableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
@Api(value = "搜索", tags = {"搜索操作"})
public class SearchController extends BaseController<AdminBuildingBindBiz, AdminBuildingBind> {

    @ApiOperation("一键搜索")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键ID", paramType = "query"),
            @ApiImplicitParam(name = "cardno", value = "逻辑卡号或物理卡号", paramType = "query"),
            @ApiImplicitParam(name = "card", value = "是否搜索门禁卡 0 是 1 否", paramType = "query"),
            @ApiImplicitParam(name = "device", value = "是否搜索门禁机 0 是 1 否", paramType = "query"),
            @ApiImplicitParam(name = "tenancy", value = "是否搜索租户 0 是 1 否", paramType = "query"),
            @ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/searchByKeyword", method = RequestMethod.GET)
    public MapMsgAndTableResultResponse<CardInfoResp> page(@RequestParam(defaultValue = "20") int pageSize,
                                                           @RequestParam(defaultValue = "1") int pageNum,
                                                           Integer id, String cardno, String cardType, String cardStatus,String keyword) {
        return null;
    }

}