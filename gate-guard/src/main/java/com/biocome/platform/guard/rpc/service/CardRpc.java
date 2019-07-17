package com.biocome.platform.guard.rpc.service;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.rpc.fallbackfactory.CardFallbackFactory;
import com.biocome.platform.inter.basemanager.vo.card.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/13 18:28
 */
@FeignClient(name = "card-push", url = "don't care", fallbackFactory = CardFallbackFactory.class)
public interface CardRpc {
    /**
     * 开卡
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value={"/cardinfo/open"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
    BaseRpcResponse openCard(URI baseUri, @RequestBody OpenCardVo vo);

    /**
     * 批量开卡
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value={"/cardinfo/openbulk"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
    List<OpenblukResp> openbulk(URI baseUri, @RequestBody OpenblukVo vo);

    /**
     * 注销卡
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value={"/cardinfo/logout"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
    BaseRpcResponse logoutCard(URI baseUri, @RequestBody LogoutCardVo vo);
    /**
     * 禁用/恢复卡
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value={"/cardinfo/manage"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
    BaseRpcResponse manageCard(URI baseUri, @RequestBody ManagerCardVo vo);
}
