package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.HouseBiz;
import com.biocome.platform.admin.entity.House;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.ValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: HouseController
 * @Author: shenlele
 * @Date: 2019/5/7 10:02
 * @Description:
 */
@Controller
@RequestMapping("house")
@Api(value = "房屋", tags = {"房屋操作"})
public class HouseController extends BaseController<HouseBiz, House> {

    private Logger log = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    protected HouseBiz houseBiz;

    @ApiOperation("获取房屋列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "housename", value = "房屋名称", paramType = "query"),
            @ApiImplicitParam(name = "housesite", value = "房屋地址", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<House> list(@RequestParam(defaultValue = "20") int pageSize,
                                           @RequestParam(defaultValue = "1") int pageNum,
                                           String housename, String housesite) {
        return houseBiz.selectByAttribute(pageSize, pageNum, housename, housesite);
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParam(name = "idList", value = "id列表，逗号分隔", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/deleteList", method = RequestMethod.DELETE)
    public ObjectRestResponse<House> deleteHouse(String idList) {
        if (ValidateUtils.isNotEmpty(idList)) {
            try {
                return houseBiz.deleteHouse(idList);
            } catch (Exception e) {
                log.error("删除房屋信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除房屋信息失败!");
            }
        } else {
            log.error("删除房屋信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("根据所传房屋信息查询")
    @ApiImplicitParam(name = "house", value = "想怎么传怎么传，so happy", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/selectByHouse", method = RequestMethod.GET)
    public TableResultResponse<House> selectByHouse(@RequestParam(defaultValue = "20") int pageSize,
                                                    @RequestParam(defaultValue = "1") int pageNum,
                                                    House house) {
        return houseBiz.selectByHouse(pageSize, pageNum, house);
    }

    @ApiOperation("保存房屋信息，判断房屋编号是否唯一")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse insert(@RequestBody House house) {
        ObjectRestResponse res;
        try {
            res = houseBiz.insertHouse(house);
        } catch (Exception e) {
            log.error("保存失败！错误信息为：" + e.getMessage());
            return new ObjectRestResponse(204, "保存失败！");
        }
        return res;
    }
}
