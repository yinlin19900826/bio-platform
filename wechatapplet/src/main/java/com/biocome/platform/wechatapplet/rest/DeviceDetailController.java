package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.DeviceDetailBiz;
import com.biocome.platform.wechatapplet.vo.device.DeviceDetailResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 10:25
 */
@RestController
@RequestMapping("/deviceDetail")
@Api(value = "门禁详情相关", tags = {"门禁详情相关"})
public class DeviceDetailController {

    @Autowired
    private DeviceDetailBiz biz;

    @ApiOperation("根据楼栋编号获取相关设备信息")
    @GetMapping("/getdevice")
    public ObjectRestResponse<List<DeviceDetailResp>> getDevice(@RequestParam("buildcode") String buildcode){
        return new ObjectRestResponse<>().data(biz.getDeviceByBuildcode(buildcode));
    }
}
