package com.biocome.platform.guard.rest;

import com.alibaba.fastjson.JSON;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.biz.SynchoBiz;
import com.biocome.platform.inter.basemanager.vo.syncho.SynchoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 同步数据Controller
 *
 * @ClassName: SynchoController
 * @Author: shenlele
 * @Date: 2019/5/14 18:18
 * @Description:
 */
@Controller
@RequestMapping("syncho")
@Api(value = "同步", tags = {"同步数据操作"})
public class SynchoController {

    private Logger log = LoggerFactory.getLogger(SynchoController.class);

    @Autowired
    SynchoBiz synchoBiz;

    @ApiOperation("同步小区信息操作")
    @ResponseBody
    @RequestMapping(value = "/estate", method = RequestMethod.POST)
    public ObjectRestResponse estate(@RequestBody SynchoVo vo) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(vo.getType()) && ValidateUtils.isNotEmpty(vo.getCode()) && ValidateUtils.isNotEmpty(vo.getToken())) {
            try {
                res = synchoBiz.estate(vo);
            } catch (Exception e) {
                log.error("同步小区信息失败，参数为：" + JSON.toJSONString(vo) + "错误信息为：" + e.getMessage());
                res = new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "同步小区信息失败!");
            }
        } else {
            log.error("参数格式错误！参数为：" + JSON.toJSONString(vo));
            res = new ObjectRestResponse<>().error();
        }
        return res;
    }

    @ApiOperation("同步楼栋信息操作")
    @ResponseBody
    @RequestMapping(value = "/build", method = RequestMethod.POST)
    public ObjectRestResponse build(@RequestBody SynchoVo vo) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(vo.getType()) && ValidateUtils.isNotEmpty(vo.getCode()) && ValidateUtils.isNotEmpty(vo.getToken())) {
            try {
                res = synchoBiz.build(vo);
            } catch (Exception e) {
                log.error("同步楼栋信息失败，参数为：" + JSON.toJSONString(vo) + "错误信息为：" + e.getMessage());
                res = new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "同步楼栋信息失败!");
            }
        } else {
            log.error("参数格式错误！参数为：" + JSON.toJSONString(vo));
            res = new ObjectRestResponse<>().error();
        }
        return res;
    }

    @ApiOperation("同步人员信息操作")
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ObjectRestResponse user(@RequestBody SynchoVo vo) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(vo.getType()) && ValidateUtils.isNotEmpty(vo.getCode()) && ValidateUtils.isNotEmpty(vo.getToken())) {
            try {
                res = synchoBiz.user(vo);
            } catch (Exception e) {
                log.error("同步人员信息失败，参数为：" + JSON.toJSONString(vo) + "错误信息为：" + e.getMessage());
                res = new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "同步人员信息失败!");
            }
        } else {
            log.error("参数格式错误！参数为：" + JSON.toJSONString(vo));
            res = new ObjectRestResponse<>().error();
        }
        return res;
    }

    @ApiOperation("同步门禁机操作")
    @ResponseBody
    @RequestMapping(value = "/doordevice", method = RequestMethod.POST)
    public ObjectRestResponse doordevice(@RequestBody SynchoVo vo) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(vo.getType()) && ValidateUtils.isNotEmpty(vo.getCode()) && ValidateUtils.isNotEmpty(vo.getToken())) {
            try {
                res = synchoBiz.doorDevice(vo);
            } catch (Exception e) {
                log.error("同步门禁机信息失败，参数为：" + JSON.toJSONString(vo) + "错误信息为：" + e.getMessage());
                res = new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "同步门禁机信息失败!");
            }
        } else {
            log.error("参数格式错误！参数为：" + JSON.toJSONString(vo));
            res = new ObjectRestResponse<>().error();
        }
        return res;
    }
}
