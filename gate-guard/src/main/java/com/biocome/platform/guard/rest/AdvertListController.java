package com.biocome.platform.guard.rest;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.biz.AdvertListBiz;
import com.biocome.platform.guard.entity.Advert;
import com.biocome.platform.guard.vo.advert.AdvertVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: AdvertController
 * @Author: shenlele
 * @Date: 2019/6/3 15:00
 * @Description:
 */
@Controller
@RequestMapping("advertList")
@Api(value = "广告列表操作", tags = {"广告列表操作"})
@Slf4j
public class AdvertListController extends BaseController<AdvertListBiz, Advert> {


    @Autowired
    private AdvertListBiz biz;

    @ApiOperation("根据所传广告信息查询")
    @ResponseBody
    @RequestMapping(value = "/selectByAttribute", method = RequestMethod.GET)
    public TableResultResponse<Advert> selectByAttribute(@RequestParam(defaultValue = "20") int pageSize,
                                                         @RequestParam(defaultValue = "1") int pageNum,
                                                         AdvertVo vo) {
        TableResultResponse<Advert> res;
        if (ValidateUtils.isNotEmpty(vo.getSn()) || ValidateUtils.isNotEmpty(vo.getBuildCode())) {
            try {
                res = biz.selectByAttribute(pageSize, pageNum, vo);
            } catch (Exception e) {
                log.error("查询广告列表信息失败！错误信息为：" + e.getMessage());
                res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询广告列表信息失败！");
            }
        }else{
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "异常，参数格式不正确！");
        }
        return res;
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键", paramType = "query"),
            @ApiImplicitParam(name = "sn", value = "设备编号", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public ObjectRestResponse remove(Integer id, String sn) {
        if (ValidateUtils.isNotEmpty(id) && ValidateUtils.isNotEmpty(sn)) {
            try {
                return biz.remove(id, sn);
            } catch (Exception e) {
                log.error("删除广告信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除广告信息失败!");
            }
        } else {
            log.error("删除广告信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("清空广告信息")
    @ApiImplicitParam(name = "sn", value = "设备编号", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/clearAd", method = RequestMethod.DELETE)
    public ObjectRestResponse clearAd(String sn) {
        if (ValidateUtils.isNotEmpty(sn)) {
            try {
                return biz.clearAd(sn);
            } catch (Exception e) {
                log.error("清空广告信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "清空广告信息失败!");
            }
        } else {
            log.error("清空广告信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
