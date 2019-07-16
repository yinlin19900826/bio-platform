package com.biocome.platform.basemanager.rest;

import com.biocome.platform.inter.basemanager.biz.UnitBiz;
import com.biocome.platform.inter.basemanager.entity.Unit;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.ValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UnitController
 * @Author: shenlele
 * @Date: 2019/5/7 10:39
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("unit")
@Api(value = "单元", tags = {"单元操作"})
public class UnitController extends BaseController<UnitBiz, Unit> {

    @Autowired
    protected UnitBiz unitBiz;

    @ApiOperation("获取单元列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "unitname", value = "单元名称", paramType = "query"),
            @ApiImplicitParam(name = "buildname", value = "楼栋名称", paramType = "query"),
            @ApiImplicitParam(name = "estatename", value = "小区名称", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<Unit> list(@RequestParam(defaultValue = "20") int pageSize,
                                          @RequestParam(defaultValue = "1") int pageNum,
                                          String unitname, String buildname,
                                          String estatename) {
        return unitBiz.selectByAttribute(pageSize, pageNum, unitname, buildname, estatename);
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParam(name = "idList", value = "id列表，逗号分隔", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/deleteList", method = RequestMethod.DELETE)
    public ObjectRestResponse<Unit> deleteUnit(String idList) {
        if (ValidateUtils.isNotEmpty(idList)) {
            try {
                return unitBiz.deleteUnit(idList);
            } catch (Exception e) {
                log.error("删除单元信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除单元信息失败!");
            }
        } else {
            log.error("删除单元信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("根据所传单元信息查询")
    @ApiImplicitParam(name = "unit", value = "想怎么传怎么传，so happy", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/selectByUnit", method = RequestMethod.GET)
    public TableResultResponse<Unit> selectByUnit(@RequestParam(defaultValue = "20") int pageSize,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                  Unit unit) {
        return unitBiz.selectByUnit(pageSize, pageNum, unit);
    }

    @ApiOperation("保存单元信息，判断单元编号是否唯一")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse insert(@RequestBody Unit unit) {
        ObjectRestResponse res;
        try {
            res = unitBiz.insertUnit(unit);
        } catch (Exception e) {
            log.error("保存失败！错误信息为：" + e.getMessage());
            return new ObjectRestResponse(204, "保存失败！");
        }
        return res;
    }
}
