package com.biocome.platform.guard.rest;

import com.alibaba.fastjson.JSON;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.biz.BluetoothBiz;
import com.biocome.platform.guard.vo.otherrpc.UploadBluetoothReq;
import com.biocome.platform.inter.basemanager.vo.device.DeviceSnBrandVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hxy
 * @date 2019/8/21 13:57
 */
@Controller
@RequestMapping("")
@Api(value = "蓝牙相关操作", tags = {"蓝牙相关操作"})
public class BluetoothController {
    private Logger log = LoggerFactory.getLogger(BluetoothController.class);
    @Autowired
    private BluetoothBiz bluetoothBiz;

    @ApiOperation("下发蓝牙摘要")
    @ResponseBody
    @PostMapping(value = "updatebluetooth")
    public ObjectRestResponse updatebluetooth(@RequestBody DeviceSnBrandVo vo) {
        ObjectRestResponse resp = new ObjectRestResponse();
        if (ValidateUtils.isEmpty(vo) || ValidateUtils.isEmpty(vo.getSn()) || ValidateUtils.isEmpty(vo.getBrandcode())) {
            return resp.error();
        }
        try {
            resp = bluetoothBiz.updatebluetooth(vo);
        } catch (Exception e) {
            log.error("下发蓝牙摘要格式错误！参数为：" + JSON.toJSONString(vo));
            resp = new ObjectRestResponse<>().customError("下发蓝牙摘要发生异常");
        }
        return resp;
    }

    @ApiOperation("上报蓝牙摘要结果")
    @ResponseBody
    @PostMapping(value = "/upload/bluetooth")
    public BaseRpcResponse uploadBluetooth(@RequestBody UploadBluetoothReq req) {
        BaseRpcResponse resp = new BaseRpcResponse();
        try {
            return bluetoothBiz.updatebluetooth(req);
        } catch (Exception e) {
            log.error("接收蓝牙摘要错误！参数为：" + JSON.toJSONString(req));
            resp.error();
        }
        return resp;
    }
}
