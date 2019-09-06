package com.biocome.platform.guard.rest;

import com.biocome.platform.guard.biz.AdminBuildingBindBiz;
import com.biocome.platform.guard.entity.AdminBuildingBind;
import com.biocome.platform.guard.vo.admin.AdminBuildingVo;
import com.biocome.platform.guard.vo.admin.AdminSummaryVo;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("adminBuildingBind")
@Api(value = "管理员楼栋绑定", tags = {"管理员楼栋绑定操作"})
public class AdminBuildingBindController extends BaseController<AdminBuildingBindBiz, AdminBuildingBind> {
    @Autowired
    AdminBuildingBindBiz adminBuildingBindBiz;

    @ApiOperation("楼栋管理员列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "楼栋编码", paramType = "query" , required = true),
            @ApiImplicitParam(name = "type", value = "管理员类型", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/adminListOnBuilding", method = RequestMethod.GET)
    public TableResultResponse<AdminSummaryVo> adminListOnBuilding(String code, Integer type) {
        return adminBuildingBindBiz.selectAdminListOnbuilding(code,type);
    }

    @ApiOperation("查找管理员绑定楼栋列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usercode", value = "用户编号 查询楼栋管理员列表为必填", paramType = "query" ),
            @ApiImplicitParam(name = "buildName", value = "楼栋名称", paramType = "query"),
            @ApiImplicitParam(name = "communityName", value = "社区名称", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "管理员姓名", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "管理员电话", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/adminBindBuildings", method = RequestMethod.GET)
    public TableResultResponse<AdminBuildingVo> adminBindBuildings(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo,
                                                                   String usercode, String buildName, String communityName, String username, String phone){
        return adminBuildingBindBiz.adminBindBuildingList(usercode, buildName, communityName, username, phone, pageNo, pageSize);
    }

    @ApiOperation("查询管理员未授权的楼栋信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usercode", value = "用户编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "buildName", value = "楼栋名称", paramType = "query"),
            @ApiImplicitParam(name = "communityName", value = "社区名称", paramType = "query"),
    })
    @ResponseBody
    @RequestMapping(value = "adminBindlessBuildings" , method = RequestMethod.GET)
    public TableResultResponse<AdminBuildingVo> adminBindlessBuildings(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo,
                                                                      String usercode, String buildName, String communityName){
        return adminBuildingBindBiz.adminBindlessBuilding(usercode, buildName, communityName, pageNo, pageSize);
    }

    @ApiOperation("管理员授权到指定楼栋")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usercode", value = "用户编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "buildcode", value = "楼栋编号", paramType = "query", required = true)
    })
    @ResponseBody
    @RequestMapping(value = "adminBindBuilding" , method = RequestMethod.POST)
    public ObjectRestResponse adminBindBuild(String usercode, String buildcode){
        return adminBuildingBindBiz.adminBindBuilding(usercode, buildcode);
    }

    @ApiOperation("增加楼栋授权")
    @ApiImplicitParam(name = "usercode", value = "用户编码", paramType = "query",  required = true)
    @ResponseBody
    @RequestMapping(value = "addBinding" , method = RequestMethod.POST)
    public ObjectRestResponse addBinding(String usercode, @RequestBody List<AdminBuildingVo> buildings){
        return adminBuildingBindBiz.addBinding(usercode, buildings);
    }

    @ApiOperation("移除楼栋授权")
    @ApiImplicitParam(name = "usercode", value = "用户编码", paramType = "query", required = true)
    @ResponseBody
    @RequestMapping(value = "removeBinding" , method = RequestMethod.POST)
    public ObjectRestResponse removeBinding(String usercode, @RequestBody List<AdminBuildingVo> buildings){
        return adminBuildingBindBiz.removeBingding(usercode, buildings);
    }

    @ApiOperation("管理员信息详情")
    @ApiImplicitParam(name = "usercode", value = "用户编码", paramType = "query", required = true)
    @ResponseBody
    @RequestMapping(value = "adminDetail" , method = RequestMethod.GET)
    public ObjectRestResponse adminDetail(String usercode){
        return adminBuildingBindBiz.adminDetail(usercode);
    }
}