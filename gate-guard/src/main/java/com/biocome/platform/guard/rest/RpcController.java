package com.biocome.platform.guard.rest;

import com.alibaba.fastjson.JSON;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.biz.RpcBiz;
import com.biocome.platform.guard.vo.otherrpc.*;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.vo.device.DeviceSnBrandVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hxy
 * @date 2019/5/15 17:02
 */
@Controller
@RequestMapping("/rpc")
@Api(value = "其他远程调用操作", tags = {"其他远程调用操作"})
public class RpcController {
    private Logger log = LoggerFactory.getLogger(RpcController.class);

    @Autowired
    RpcBiz rpcBiz;

    @ApiOperation("开门")
    @ResponseBody
    @RequestMapping(value = "/openDoor", method = RequestMethod.POST)
    public BaseRpcResponse openDoor(@RequestBody OpenDoorVo req) {
        return rpcBiz.openDoor(req);
    }

    @ApiOperation("请求开门密码")
    @ResponseBody
    @RequestMapping(value = "/opendoorpassword", method = RequestMethod.POST)
    public OpendoorpasswordResp opendoorpassword(@RequestBody OpendoorpasswordVo req) {
        return rpcBiz.opendoorpassword(req);
    }

    @ApiOperation("获取mac地址(token,sn,ip,brandcode必填)")
    @ResponseBody
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public ObjectRestResponse address(@RequestBody AddressBrandVo vo) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(vo) && ValidateUtils.isNotEmpty(vo.getSn()) && ValidateUtils.isNotEmpty(vo.getDeviceip())
                && ValidateUtils.isNotEmpty(vo.getToken()) && ValidateUtils.isNotEmpty(vo.getBrandcode())) {
            try {
                res = rpcBiz.address(vo);
                log.info("获取mac地址返回为：" + JSON.toJSONString(res.getData()));
            } catch (Exception e) {
                log.error("获取mac地址信息失败，错误信息为：" + e.getMessage());
                res = new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "获取mac地址信息失败!");
            }
        } else {
            log.error("获取mac地址参数格式错误！参数为：" + JSON.toJSONString(vo));
            res = new ObjectRestResponse<>().error();
        }
        return res;
    }

    @ApiOperation("远程重启(token,sn,brandcode必填)")
    @ResponseBody
    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    public ObjectRestResponse restart(@RequestBody DeviceSnBrandVo vo) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(vo) && ValidateUtils.isNotEmpty(vo.getSn()) && ValidateUtils.isNotEmpty(vo.getToken())
                && ValidateUtils.isNotEmpty(vo.getBrandcode())) {
            try {
                BaseRpcResponse resp = rpcBiz.restart(vo);
                log.info("远程重启返回为：" + JSON.toJSONString(resp));
                if (AdminCommonConstant.BOOLEAN_NUMBER_TRUE.equals(resp.getResult())) {
                    res = new ObjectRestResponse<>().success();
                } else {
                    res = new ObjectRestResponse<>().failure();
                }
            } catch (Exception e) {
                log.error("远程重启失败，错误信息为：" + e.getMessage());
                res = new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "远程重启失败!");
            }
        } else {
            log.error("远程重启参数格式错误！参数为：" + JSON.toJSONString(vo));
            res = new ObjectRestResponse<>().error();
        }
        return res;
    }

    @ApiOperation("远程升级(token,sn,brandcode必填)")
    @ResponseBody
    @RequestMapping(value = "/remoteupdate", method = RequestMethod.POST)
    public ObjectRestResponse remoteUpdate(@RequestBody RemoteUpdateBrandVo vo) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(vo) && ValidateUtils.isNotEmpty(vo.getSn())
                && ValidateUtils.isNotEmpty(vo.getToken()) && ValidateUtils.isNotEmpty(vo.getBrandcode())) {
            try {
                BaseRpcResponse resp = rpcBiz.remoteUpdate(vo);
                log.info("远程升级返回为：" + JSON.toJSONString(resp));
                if (AdminCommonConstant.BOOLEAN_NUMBER_TRUE.equals(resp.getResult())) {
                    res = new ObjectRestResponse<>().success();
                } else {
                    res = new ObjectRestResponse<>().failure();
                }
            } catch (Exception e) {
                log.error("远程升级失败，错误信息为：" + e.getMessage());
                res = new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "远程升级失败!");
            }
        } else {
            log.error("远程升级参数格式错误！参数为：" + JSON.toJSONString(vo));
            res = new ObjectRestResponse<>().error();
        }
        return res;
    }

}
