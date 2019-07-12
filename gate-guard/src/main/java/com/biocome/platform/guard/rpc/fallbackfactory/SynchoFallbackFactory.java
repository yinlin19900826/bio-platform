package com.biocome.platform.guard.rpc.fallbackfactory;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.rpc.service.SynchoRpc;
import com.biocome.platform.guard.vo.common.CommonListVo;
import com.biocome.platform.guard.vo.device.DoorDeviceVo;
import com.biocome.platform.guard.vo.syncho.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SynchoFallbackFactory
 * @Author: shenlele
 * @Date: 2019/5/14 17:26
 * @Description:
 */
@Component
public class SynchoFallbackFactory implements FallbackFactory<SynchoRpc> {
    @Override
    public SynchoRpc create(Throwable throwable) {
        return new SynchoRpc() {
            private BaseRpcResponse error() {
                BaseRpcResponse resp = new BaseRpcResponse();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }

            private List<EstateResp> estateError() {
                List<EstateResp> list = new ArrayList<>();
                EstateResp resp = new EstateResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                list.add(resp);
                return list;
            }

            private List<BuildResp> buildError() {
                List<BuildResp> list = new ArrayList<>();
                BuildResp resp = new BuildResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                list.add(resp);
                return list;
            }

            private List<UserResp> userError() {
                List<UserResp> list = new ArrayList<>();
                UserResp resp = new UserResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                list.add(resp);
                return list;
            }

            @Override
            public List<EstateResp> estate(URI baseUri, CommonListVo<EstateVo> listVo) {
                return estateError();
            }

            @Override
            public List<BuildResp> build(URI baseUri, CommonListVo<BuildVo> listVo) {
                return buildError();
            }

            @Override
            public List<UserResp> user(URI baseUri, CommonListVo<LesseeUserVo> listVo) {
                return userError();
            }

            @Override
            public BaseRpcResponse doorDevice(URI baseUri, CommonListVo<DoorDeviceVo> listVo) {
                return error();
            }
        };
    }
}
