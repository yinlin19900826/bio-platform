package com.biocome.platform.guard.rpc.service;

import com.biocome.platform.guard.otherrpc.*;
import com.biocome.platform.guard.rpc.fallbackfactory.OtherFallbackFactory;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.inter.basemanager.vo.device.DeviceSnVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

/**
 * @author hxy
 * @date 2019/5/15 17:05
 */
@FeignClient(name = "Other-RPC", url = "don't care", fallbackFactory = OtherFallbackFactory.class)
public interface OtherRpc {
    /**
     * 远程开门
     *
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/opendoor"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse openDoor(URI baseUri, @RequestBody OpenDoorVo vo);

    /**
     * 请求开门密码
     *
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/opendoorpassword"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse opendoorpassword(URI baseUri, @RequestBody OpendoorpasswordVo vo);

    /**
     * 远程重启
     *
     * @param baseUri 请求地址
     * @param vo      请求参数
     * @return BaseRpcResponse
     * @Author shenlele
     * @Date 2019/5/20 16:31
     */
    @RequestMapping(value = {"/doordevice/restart"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse restart(URI baseUri, @RequestBody DeviceSnVo vo);

    /**
     * 远程升级
     *
     * @param baseUri 请求地址
     * @param vo      请求参数
     * @return BaseRpcResponse
     * @Author shenlele
     * @Date 2019/5/20 16:36
     */
    @RequestMapping(value = {"/doordevice/remoteupdate"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse remoteupdate(URI baseUri, @RequestBody RemoteUpdateVo vo);

    /**
     * 获取mac地址
     *
     * @param baseUri 请求地址
     * @param vo      请求参数
     * @return AddressResp
     * @Author shenlele
     * @Date 2019/5/20 17:40
     */
    @RequestMapping(value = {"/doordevice/address"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    AddressResp address(URI baseUri, @RequestBody AddressVo vo);

    /**
     * 获取token
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/token"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    TokenResp token(URI baseUri, @RequestBody TokenVo vo);
}
