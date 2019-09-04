package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.TreeDistrictBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.biz.BuildBiz;
import com.biocome.platform.inter.basemanager.biz.DistrictBiz;
import com.biocome.platform.inter.basemanager.biz.EstateBiz;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Build;
import com.biocome.platform.inter.basemanager.entity.Estate;
import com.biocome.platform.inter.basemanager.vo.DistrictResp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ShowManageController
 * @Author: shenlele
 * @Date: 2019/5/17 13:49
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("show")
@Api(value = "展示", tags = {"展示操作"})
public class ShowManageController {

    private final BuildBiz buildBiz;
    private final EstateBiz estateBiz;
    private final DistrictBiz districtBiz;
    private final TreeDistrictBiz treeDistrictBiz;

    @Autowired
    public ShowManageController(BuildBiz buildBiz, EstateBiz estateBiz, DistrictBiz districtBiz, TreeDistrictBiz treeDistrictBiz) {
        this.buildBiz = buildBiz;
        this.estateBiz = estateBiz;
        this.districtBiz = districtBiz;
        this.treeDistrictBiz = treeDistrictBiz;
    }

    @ApiOperation("基础管理查询展示导航树操作")
    @ApiImplicitParam(name = "parentcode", value = "行政区划代码", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/treeformanage", method = RequestMethod.GET)
    public ObjectRestResponse<List<TreeDistrictVO>> treeformanage(String parentcode) {
        try {
            List<TreeDistrictVO> list = treeDistrictBiz.getTree(parentcode);
            return new ObjectRestResponse<>(true).data(list);
        } catch (Exception e) {
            log.error("基础管理查询展示导航树操作失败，错误信息为：" + e.getMessage());
            return new ObjectRestResponse<>().failure();
        }
    }

    @ApiOperation("行政区划字典操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(1:省,2:市,3:县/区,4:乡/街道,5:村/派出所,6:组/小区,7:楼栋,8:单元)", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "父级代码，省的父级传空", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/selectDistrict", method = RequestMethod.GET)
    public ObjectRestResponse<List<DistrictResp>> selectDistrict(String type, String code) {
        if (ValidateUtils.isEmpty(type)) {
            return new ObjectRestResponse<>().error();
        }
        try {
            List<DistrictResp> resps = districtBiz.selectDistrict(type, code);
            return new ObjectRestResponse<>(true).data(resps);
        } catch (Exception e) {
            log.error("行政区划字典操作失败，错误信息为：" + e.getMessage());
            return new ObjectRestResponse<>().failure();
        }
    }

    @ApiOperation("菜单与列表关联操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "option", value = "触发模块（0:小区管理,1:楼栋管理）", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型(1:省,2:市,3:县/区,4:乡/街道,5:村/派出所,6:组/小区,7:楼栋)", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "行政区划代码", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/selectListByTree", method = RequestMethod.GET)
    public TableResultResponse selectListByTree(String option, @RequestParam String type, @RequestParam String code) {
        try {
            if (AdminCommonConstant.DEFAULT_ZERO.equals(option)) {
                Estate estate = new Estate();
                if (AdminCommonConstant.DISTRICT_PROVINCE.equals(type)) {
                    estate.setProvince(code);
                } else if (AdminCommonConstant.DISTRICT_CITY.equals(type)) {
                    estate.setCity(code);
                } else if (AdminCommonConstant.DISTRICT_COUNTY.equals(type)) {
                    estate.setCounty(code);
                } else if (AdminCommonConstant.DISTRICT_STREET.equals(type)) {
                    estate.setStreet(code);
                } else if (AdminCommonConstant.DISTRICT_POLICESTATIO.equals(type)) {
                    estate.setPolicestatio(code);
                } else {
                    return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "异常，请检查参数格式");
                }
                return estateBiz.selectByEstate(20, 1, estate);
            } else if (AdminCommonConstant.DEFAULT_ONE.equals(option)) {
                Build build = new Build();
                if (AdminCommonConstant.DISTRICT_PROVINCE.equals(type)) {
                    build.setProvince(code);
                } else if (AdminCommonConstant.DISTRICT_CITY.equals(type)) {
                    build.setCity(code);
                } else if (AdminCommonConstant.DISTRICT_COUNTY.equals(type)) {
                    build.setCounty(code);
                } else if (AdminCommonConstant.DISTRICT_STREET.equals(type)) {
                    build.setStreet(code);
                } else if (AdminCommonConstant.DISTRICT_POLICESTATIO.equals(type)) {
                    build.setPolicestatio(code);
                } else {
                    return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "异常，请检查参数格式");
                }
                return buildBiz.selectByBuild(20, 1, build);
            } else {
                return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "异常，请检查参数格式");
            }
        } catch (Exception e) {
            log.error("菜单与列表关联操作失败，错误信息为：" + e.getMessage());
            return new TableResultResponse<>(204, " 查询失败！");
        }
    }
}
