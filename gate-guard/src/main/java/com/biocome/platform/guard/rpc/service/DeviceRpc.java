package com.biocome.platform.guard.rpc.service;

import com.biocome.platform.guard.rpc.fallbackfactory.DeviceFallbackFactory;
import com.biocome.platform.guard.vo.device.DeviceSnVo;
import com.biocome.platform.guard.vo.device.DeviceStatusRes;
import com.biocome.platform.guard.vo.device.DoorStatusRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

/**
 * @ClassName: DeviceRpc
 * @Author: shenlele
 * @Date: 2019/5/15 17:43
 * @Description:
 */
@FeignClient(name = "device", url = "So easy !", fallbackFactory = DeviceFallbackFactory.class)
public interface DeviceRpc {

    /**
     * 请求门禁机状态信息
     *
     * @param baseUri    请求地址
     * @param deviceSnVo 设备信息
     * @return DeviceStatusRes
     * @Author shenlele
     * @Date 2019/5/15 18:03
     */
    @RequestMapping(value = {"/doordevice/status"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    DeviceStatusRes deviceStatus(URI baseUri, @RequestBody DeviceSnVo deviceSnVo);

    /**
     * 请求门状态信息
     *
     * @param baseUri    请求地址
     * @param deviceSnVo 设备信息
     * @return DoorStatusRes
     * @Author shenlele
     * @Date 2019/5/15 18:04
     */
    @RequestMapping(value = {"/door/status"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    DoorStatusRes doorStatus(URI baseUri, @RequestBody DeviceSnVo deviceSnVo);
}
