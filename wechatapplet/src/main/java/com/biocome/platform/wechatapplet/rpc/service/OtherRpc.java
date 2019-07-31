package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.wechatapplet.rpc.fallbackfactory.OtherFallbackFactory;
import com.biocome.platform.wechatapplet.vo.opendoor.OpenDoorVo;
import com.biocome.platform.wechatapplet.vo.token.TokenResp;
import com.biocome.platform.wechatapplet.vo.token.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

/**
 * @ClassName: OtherRpc
 * @Author: shenlele
 * @Date: 2019/7/29 17:43
 * @Description:
 */
@FeignClient(name = "Other-RPC", url = "so easy!", fallbackFactory = OtherFallbackFactory.class)
public interface OtherRpc {
    /**
     * 远程开门
     *
     * @param baseUri 请求URI
     * @param vo      实体类
     * @return com.biocome.platform.common.msg.auth.BaseRpcResponse
     * @Author shenlele
     * @Date 2019/7/29 18:09
     */
    @RequestMapping(value = {"/opendoor"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse openDoor(URI baseUri, @RequestBody OpenDoorVo vo);

    /**
     * 获取token
     *
     * @param baseUri 请求URI
     * @param vo      实体类
     * @return com.biocome.platform.wechatapplet.vo.token.TokenResp
     * @Author shenlele
     * @Date 2019/7/30 10:05
     */
    @RequestMapping(value = {"/token"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    TokenResp token(URI baseUri, @RequestBody TokenVo vo);
}
