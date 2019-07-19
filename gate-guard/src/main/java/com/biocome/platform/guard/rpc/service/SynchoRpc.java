package com.biocome.platform.guard.rpc.service;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.rpc.fallbackfactory.SynchoFallbackFactory;
import com.biocome.platform.inter.basemanager.vo.common.CommonListVo;
import com.biocome.platform.inter.basemanager.vo.device.DoorDeviceVo;
import com.biocome.platform.inter.basemanager.vo.syncho.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

/**
 * @ClassName: SynchoRpc
 * @Author: shenlele
 * @Date: 2019/5/14 17:25
 * @Description:
 */
@FeignClient(name = "syncho", url = "So easy !", fallbackFactory = SynchoFallbackFactory.class)
public interface SynchoRpc {

    /**
     * 同步小区方法
     *
     * @param baseUri 同步地址
     * @param listVo  同步参数
     * @return com.biocome.platform.guard.camera.EstateResp
     * @Author shenlele
     * @Date 2019/5/14 17:42
     */
    @RequestMapping(value = {"/estate"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    List<EstateResp> estate(URI baseUri, @RequestBody CommonListVo<EstateVo> listVo);

    /**
     * 同步楼栋方法
     *
     * @param baseUri 同步地址
     * @param listVo  同步参数
     * @return java.util.List<com.biocome.platform.guard.camera.BuildResp>
     * @Author shenlele
     * @Date 2019/5/14 18:02
     */
    @RequestMapping(value = {"/build"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    List<BuildResp> build(URI baseUri, @RequestBody CommonListVo<BuildVo> listVo);

    /**
     * 同步人员方法
     *
     * @param baseUri 同步地址
     * @param listVo  同步参数
     * @return java.util.List<com.biocome.platform.guard.camera.UserResp>
     * @Author shenlele
     * @Date 2019/5/14 18:03
     */
    @RequestMapping(value = {"/user"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    List<UserResp> user(URI baseUri, @RequestBody CommonListVo<LesseeUserVo> listVo);

    /**
     * 同步门禁机方法
     *
     * @param baseUri 同步地址
     * @param listVo  同步参数
     * @return com.biocome.platform.guard.camera.com.biocome.platform.inter.common.CommonResp
     * @Author shenlele
     * @Date 2019/5/14 18:07
     */
    @RequestMapping(value = {"/doordevice"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse doorDevice(URI baseUri, @RequestBody CommonListVo<DoorDeviceVo> listVo);
}
