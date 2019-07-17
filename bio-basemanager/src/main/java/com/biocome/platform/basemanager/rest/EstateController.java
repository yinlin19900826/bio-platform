package com.biocome.platform.basemanager.rest;

import com.biocome.platform.inter.basemanager.biz.EstateBiz;
import com.biocome.platform.inter.basemanager.entity.Estate;
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
 * @ClassName: EstateController
 * @Author: shenlele
 * @Date: 2019/5/7 09:35
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("estate")
@Api(value = "小区", tags = {"小区操作"})
public class EstateController extends BaseController<EstateBiz, Estate> {

    @Autowired
    protected EstateBiz estateBiz;

    @ApiOperation("获取小区列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "estatename", value = "小区名称", paramType = "query"),
            @ApiImplicitParam(name = "estatecode", value = "小区编号", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<Estate> list(@RequestParam(defaultValue = "20") int pageSize,
                                            @RequestParam(defaultValue = "1") int pageNum,
                                            String estatename, String estatecode) {
        return estateBiz.selectByAttribute(pageSize, pageNum, estatename, estatecode);
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParam(name = "idList", value = "id列表，逗号分隔", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/deleteList", method = RequestMethod.DELETE)
    public ObjectRestResponse<Estate> deleteEstate(String idList) {
        if (ValidateUtils.isNotEmpty(idList)) {
            try {
                return estateBiz.deleteEstate(idList);
            } catch (Exception e) {
                log.error("删除小区信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除小区信息失败!");
            }
        } else {
            log.error("删除小区信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("根据所给条件查询小区列表")
    @ApiImplicitParam(name = "estate", value = "需要什么传什么，so easy", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/selectByEstate", method = RequestMethod.GET)
    public TableResultResponse<Estate> list(@RequestParam(defaultValue = "20") int pageSize,
                                            @RequestParam(defaultValue = "1") int pageNum,
                                            Estate estate) {
        return estateBiz.selectByEstate(pageSize, pageNum, estate);
    }

    @ApiOperation("保存小区信息，判断小区编号是否唯一")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse insert(@RequestBody Estate estate) {
        ObjectRestResponse res;
        try {
            res = estateBiz.insertEstate(estate);
        } catch (Exception e) {
            log.error("保存失败！错误信息为：" + e.getMessage());
            return new ObjectRestResponse(204, "保存失败！");
        }
        return res;
    }
}
