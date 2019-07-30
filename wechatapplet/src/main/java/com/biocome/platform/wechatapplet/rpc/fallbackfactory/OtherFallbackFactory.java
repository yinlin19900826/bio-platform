package com.biocome.platform.wechatapplet.rpc.fallbackfactory;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.wechatapplet.rpc.service.OtherRpc;
import com.biocome.platform.wechatapplet.vo.opendoor.OpenDoorVo;
import com.biocome.platform.wechatapplet.vo.token.TokenResp;
import com.biocome.platform.wechatapplet.vo.token.TokenVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @ClassName: OtherFallbackFactory
 * @Author: shenlele
 * @Date: 2019/7/29 18:01
 * @Description:
 */
@Component
public class OtherFallbackFactory implements FallbackFactory<OtherRpc> {
    @Override
    public OtherRpc create(Throwable throwable) {
        return new OtherRpc() {
            @Override
            public BaseRpcResponse openDoor(URI baseUri, OpenDoorVo vo) {
                return error();
            }

            private BaseRpcResponse error() {
                BaseRpcResponse res = new BaseRpcResponse();
                res.setErrorcode("105");
                res.setMessage("服务调用失败");
                res.setResult("0");
                return res;
            }

            @Override
            public TokenResp token(URI baseUri, TokenVo vo) {
                TokenResp resp = new TokenResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }
        };
    }
}
