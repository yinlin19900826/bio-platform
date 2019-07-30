package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.inter.basemanager.vo.card.LogoutCardVo;
import com.biocome.platform.inter.basemanager.vo.card.OpenCardVo;
import com.biocome.platform.wechatapplet.rpc.fallbackfactory.CardFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

/**
 * @ClassName: CardRpc
 * @Author: shenlele
 * @Date: 2019/7/29 18:01
 * @Description:
 */
@FeignClient(name = "card-push", url = "don't care", fallbackFactory = CardFallbackFactory.class)
public interface CardRpc {
    /**
     * 开卡
     *
     * @param baseUri 请求地址
     * @param vo      请求参数
     * @return com.biocome.platform.common.msg.auth.BaseRpcResponse
     * @Author shenlele
     * @Date 2019/7/30 16:01
     */
    @RequestMapping(value = {"/cardinfo/open"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse openCard(URI baseUri, @RequestBody OpenCardVo vo);

    /**
     * 注销卡
     *
     * @param baseUri 请求地址
     * @param vo      请求参数
     * @return com.biocome.platform.common.msg.auth.BaseRpcResponse
     * @Author shenlele
     * @Date 2019/7/30 16:01
     */
    @RequestMapping(value = {"/cardinfo/logout"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse logoutCard(URI baseUri, @RequestBody LogoutCardVo vo);
}
