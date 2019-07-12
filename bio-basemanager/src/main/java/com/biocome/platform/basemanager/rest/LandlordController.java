package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.LandlordBiz;
import com.biocome.platform.basemanager.entity.Landlord;
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
 * @ClassName: LandlordController
 * @Author: shenlele
 * @Date: 2019/5/7 10:10
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("landlord")
@Api(value = "房东", tags = {"房东操作"})
public class LandlordController extends BaseController<LandlordBiz, Landlord> {

    @Autowired
    protected LandlordBiz landlordBiz;

    @ApiOperation("获取房东列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "姓名", paramType = "query"),
            @ApiImplicitParam(name = "tel", value = "电话号码", paramType = "query"),
            @ApiImplicitParam(name = "papersnum", value = "证件号码", paramType = "query"),
            @ApiImplicitParam(name = "landlordtype", value = "房东类型", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<Landlord> list(@RequestParam(defaultValue = "20") int pageSize,
                                              @RequestParam(defaultValue = "1") int pageNum,
                                              String username, String tel,
                                              String papersnum, Integer landlordtype) {
        return landlordBiz.selectByAttribute(pageSize, pageNum, username, tel, papersnum, landlordtype);
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParam(name = "idList", value = "id列表，逗号分隔", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/deleteList", method = RequestMethod.DELETE)
    public ObjectRestResponse<Landlord> deleteLandlord(String idList) {
        if (ValidateUtils.isNotEmpty(idList)) {
            try {
                return landlordBiz.deleteLandlord(idList);
            } catch (Exception e) {
                log.error("删除房东信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除房东信息失败!");
            }
        } else {
            log.error("删除房东信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("保存房东信息，判断房东编号是否唯一")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse insert(@RequestBody Landlord landlord) {
        ObjectRestResponse res;
        try {
            res = landlordBiz.insertLandlord(landlord);
        } catch (Exception e) {
            log.error("保存失败！错误信息为：" + e.getMessage());
            return new ObjectRestResponse(204, "保存失败！");
        }
        return res;
    }

}
