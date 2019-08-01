package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.wechatapplet.rpc.fallbackfactory.OpenDoorPasswordFallbackFactory;
import com.biocome.platform.wechatapplet.vo.visitor.OpendoorpasswordResp;
import com.biocome.platform.wechatapplet.vo.visitor.VisitorPasswordVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author hxy
 * @date 2019/7/30 15:54
 */
@FeignClient(name = "opendoorpassword", url = "${gateguard.url}", fallbackFactory = OpenDoorPasswordFallbackFactory.class)
public interface OpenDoorPasswordRpc {
    /**
     * 请求开门密码
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/rpc/opendoorpassword"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    OpendoorpasswordResp opendoorpassword(@RequestBody VisitorPasswordVo vo);
}
