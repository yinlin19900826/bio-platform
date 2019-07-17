package com.biocome.platform.guard.rpc.fallbackfactory;

import com.biocome.platform.guard.otherrpc.*;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.rpc.service.OtherRpc;
import com.biocome.platform.inter.basemanager.vo.device.DeviceSnVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author hxy
 * @date 2019/5/15 17:06
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

            @Override
            public BaseRpcResponse opendoorpassword(URI baseUri, OpendoorpasswordVo vo) {
                return error();
            }

            @Override
            public BaseRpcResponse restart(URI baseUri, DeviceSnVo vo) {
                return error();
            }

            @Override
            public BaseRpcResponse remoteupdate(URI baseUri, RemoteUpdateVo vo) {
                return error();
            }

            @Override
            public AddressResp address(URI baseUri, AddressVo vo) {
                return addressResp();
            }

            @Override
            public TokenResp token(URI baseUri, TokenVo vo) {
                TokenResp resp = new TokenResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }

            private BaseRpcResponse error() {
                BaseRpcResponse resp = new BaseRpcResponse();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }

            private AddressResp addressResp() {
                AddressResp resp = new AddressResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }
        };
    }
}
