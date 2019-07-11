package com.biocome.platform.guard.rpc.fallbackfactory;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.rpc.service.AdvertRpc;
import com.biocome.platform.guard.vo.advert.AddAdvert;
import com.biocome.platform.guard.vo.advert.AddAdvertList;
import com.biocome.platform.guard.vo.advert.AdvertAddListRpcResp;
import com.biocome.platform.guard.vo.advert.RemoveAdvertList;
import com.biocome.platform.guard.vo.device.DeviceSnVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/30 14:10
 */
@Component
public class AdvertFallbackFactory implements FallbackFactory<AdvertRpc> {

    @Override
    public AdvertRpc create(Throwable throwable) {
        return new AdvertRpc() {

            private List<AdvertAddListRpcResp> removeAdListError() {
                List<AdvertAddListRpcResp> list = new ArrayList<>();
                AdvertAddListRpcResp resp = new AdvertAddListRpcResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                list.add(resp);
                return list;
            }

            @Override
            public BaseRpcResponse Advert(URI baseUri, AddAdvert vo) {
                return new BaseRpcResponse().error();
            }

            @Override
            public List<AdvertAddListRpcResp> AdvertList(URI baseUri, AddAdvertList vo) {
                return null;
            }

            @Override
            public List<AdvertAddListRpcResp> removeadlist(URI baseUri, RemoveAdvertList vo) {
                return removeAdListError();
            }

            @Override
            public BaseRpcResponse clearAdvert(URI baseUri, DeviceSnVo vo) {
                return new BaseRpcResponse().error();
            }
        };
    }
}
