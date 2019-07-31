package com.biocome.platform.wechatapplet.controller;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.DeviceDetailBiz;
import com.biocome.platform.wechatapplet.vo.device.DeviceDetailResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 10:25
 */
@RestController
@RequestMapping("/deviceDetail")
public class DeviceDetailController {

    @Autowired
    private DeviceDetailBiz biz;

    @PostMapping("/getdevice")
    public ObjectRestResponse<List<DeviceDetailResp>> getDevice(@RequestBody String buildcode){
        return new ObjectRestResponse<>().data(biz.getDeviceByBuildcode(buildcode));
    }
}
