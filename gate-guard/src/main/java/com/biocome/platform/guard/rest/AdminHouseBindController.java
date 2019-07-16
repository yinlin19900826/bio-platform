package com.biocome.platform.guard.rest;

import com.biocome.platform.guard.biz.AdminHouseBindBiz;
import com.biocome.platform.guard.entity.AdminHouseBind;
import com.biocome.platform.guard.vo.admin.AdminHouseVo;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value = "管理员房屋绑定", tags = {"管理员房屋绑定操作"})
@Controller
@RequestMapping("adminHouseBind")
public class AdminHouseBindController extends BaseController<AdminHouseBindBiz, AdminHouseBind> {

    @Autowired
    private AdminHouseBindBiz adminHouseBindBiz;

    @ApiOperation("当前楼栋的所有房屋授权信息")
    @ApiImplicitParam(name = "buildcode", value = "楼栋编码", paramType = "query", required = true)
    @ResponseBody
    @RequestMapping(value = "/buildingHouseList", method = RequestMethod.GET)
    public TableResultResponse<AdminHouseVo> buildingHouseList(String buildcode){
        return adminHouseBindBiz.buildingHouseList(buildcode);
    }

    @ApiOperation("授权房屋更新")
    @ApiImplicitParam(name = "buildcode", value = "手机号码", paramType = "query", required = true)
    @ResponseBody
    @RequestMapping(value = "/updateHouseBind", method = RequestMethod.POST)
    public ObjectRestResponse updateHouseBind(String buildcode,
                                              @RequestBody List<AdminHouseVo> houseVos){
        return adminHouseBindBiz.updateHouseBind(buildcode, houseVos);
    }

    @ApiOperation("全部授权")
    @ApiImplicitParam(name = "buildcode", value = "楼栋编号", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/bindAll", method = RequestMethod.POST)
    public ObjectRestResponse bindAll(String buildcode){
        return adminHouseBindBiz.bindAll(buildcode);
    }

    @ApiOperation("全部取消")
    @ApiImplicitParam(name = "buildcode", value = "楼栋编号", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/removeAll", method = RequestMethod.POST)
    public ObjectRestResponse removeAll(String buildcode){
        return adminHouseBindBiz.removeAll(buildcode);
    }
}