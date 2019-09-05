package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.TreeDistrictBiz;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.biz.BuildBiz;
import com.biocome.platform.inter.basemanager.biz.DistrictBiz;
import com.biocome.platform.inter.basemanager.biz.EstateBiz;
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
}
