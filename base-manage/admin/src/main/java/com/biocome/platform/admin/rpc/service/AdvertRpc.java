package com.biocome.platform.admin.rpc.service;

import com.biocome.platform.admin.rpc.fallbackfactory.AdvertFallbackFactory;
import com.biocome.platform.admin.vo.advert.AddAdvert;
import com.biocome.platform.admin.vo.advert.AddAdvertList;
import com.biocome.platform.admin.vo.advert.AdvertAddListRpcResp;
import com.biocome.platform.admin.vo.advert.RemoveAdvertList;
import com.biocome.platform.admin.vo.device.DeviceSnVo;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/30 14:09
 */
@FeignClient(name = "advert-push", url = "don't care", fallbackFactory = AdvertFallbackFactory.class)
public interface AdvertRpc {
    /**
     * 门禁广告下发
     *
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/doordevice/ad"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse Advert(URI baseUri, @RequestBody AddAdvert vo);

    /**
     * 门禁广告批量下发
     *
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/doordevice/adlist"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    List<AdvertAddListRpcResp> AdvertList(URI baseUri, @RequestBody AddAdvertList vo);

    /**
     * 门禁广告批量删除
     *
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/doordevice/removeadlist"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    List<AdvertAddListRpcResp> removeadlist(URI baseUri, @RequestBody RemoveAdvertList vo);

    /**
     * 清空广告
     *
     * @param baseUri
     * @param vo
     * @return
     */
    @RequestMapping(value = {"/doordevice/clearad"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    BaseRpcResponse clearAdvert(URI baseUri, @RequestBody DeviceSnVo vo);
}
