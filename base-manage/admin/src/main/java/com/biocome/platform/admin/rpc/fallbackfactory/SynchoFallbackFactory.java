package com.biocome.platform.admin.rpc.fallbackfactory;

import com.biocome.platform.admin.vo.common.CommonListVo;
import com.biocome.platform.admin.vo.syncho.*;
import com.biocome.platform.admin.rpc.service.SynchoRpc;
import com.biocome.platform.admin.vo.device.DoorDeviceVo;
import com.biocome.platform.admin.vo.syncho.*;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
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

            private List<EstateResp> EstateError() {
                List<EstateResp> list = new ArrayList<>();
                EstateResp resp = new EstateResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                list.add(resp);
                return list;
            }

            private List<BuildResp> BuildError() {
                List<BuildResp> list = new ArrayList<>();
                BuildResp resp = new BuildResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                list.add(resp);
                return list;
            }

            private List<UserResp> UserError() {
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
                return EstateError();
            }

            @Override
            public List<BuildResp> build(URI baseUri, CommonListVo<BuildVo> listVo) {
                return BuildError();
            }

            @Override
            public List<UserResp> user(URI baseUri, CommonListVo<LesseeUserVo> listVo) {
                return UserError();
            }

            @Override
            public BaseRpcResponse doorDevice(URI baseUri, CommonListVo<DoorDeviceVo> listVo) {
                return error();
            }
        };
    }
}
