package com.biocome.platform.guard.rest;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.biz.ShowManageRpcBiz;
import com.biocome.platform.guard.biz.TreeDistrictBiz;
import com.biocome.platform.guard.vo.showmanage.DeviceAndStatusVo;
import com.biocome.platform.guard.vo.showmanage.ShowManageVo;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ShowManageController
 * @Author: shenlele
 * @Date: 2019/5/17 13:49
 * @Description:
 */
@Controller
@RequestMapping("show")
@Api(value = "展示", tags = {"展示操作"})
@Slf4j
public class ShowManageController {

    @Autowired
    private TreeDistrictBiz treeDistrictBiz;
    @Autowired
    private ShowManageRpcBiz showManageRpcBiz;

    @ApiOperation("门禁管理查询展示导航树操作")
    @ApiImplicitParam(name = "parentcode", value = "行政区划代码", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ObjectRestResponse<List<TreeDistrictVO>> treeDistrict(String parentcode) {
        try {
            List<TreeDistrictVO> list = treeDistrictBiz.getTreeDistrict(parentcode);
            return new ObjectRestResponse<>(true).data(list);
        } catch (Exception e) {
            log.error("查询展示导航树操作失败，错误信息为：" + e.getMessage());
            return new ObjectRestResponse<>().failure();
        }
    }

    @ApiOperation("门禁管理页面展示，楼栋信息，门禁信息，房间信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", paramType = "query"),
            @ApiImplicitParam(name = "buildcode", value = "楼栋编号", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/showpage", method = RequestMethod.GET)
    public ObjectRestResponse<ShowManageVo> showPage(String token, String buildcode) {
        if (ValidateUtils.isNotEmpty(token) && ValidateUtils.isNotEmpty(buildcode)) {
            ShowManageVo showManageVo = showManageRpcBiz.buildAndHouseAnddevice(buildcode);
            return new ObjectRestResponse<>(true).data(showManageVo);
        } else {
            log.error("门禁管理页面展示，楼栋信息，门禁信息，房间信息请求参数错误！");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("门禁管理页面展示设备信息与设备状态信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", paramType = "query"),
            @ApiImplicitParam(name = "buildcode", value = "楼栋编号", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/showdevice", method = RequestMethod.GET)
    public ObjectRestResponse<List<DeviceAndStatusVo>> showDevice(String token, String buildcode) {
        if (ValidateUtils.isNotEmpty(token) && ValidateUtils.isNotEmpty(buildcode)) {
            List<DeviceAndStatusVo> deviceAndStatusVos = showManageRpcBiz.showDevice(buildcode);
            return new ObjectRestResponse<>(true).data(deviceAndStatusVos);
        } else {
            log.error("门禁管理页面展示设备信息与设备状态信息请求参数错误！");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("多媒体管理查询展示导航树操作")
    @ApiImplicitParam(name = "parentcode", value = "行政区划代码", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/treeForAdvert", method = RequestMethod.GET)
    public ObjectRestResponse<List<TreeDistrictVO>> treeForAdvert(String parentcode) {
        try {
            List<TreeDistrictVO> list = treeDistrictBiz.getAdvertTree(parentcode);
            return new ObjectRestResponse<>(true).data(list);
        } catch (Exception e) {
            log.error("多媒体管理查询展示导航树操作失败，错误信息为：" + e.getMessage());
            return new ObjectRestResponse<>().failure();
        }
    }
}
