package com.biocome.platform.guard.rpc.fallbackfactory;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.rpc.service.CardRpc;
import com.biocome.platform.inter.basemanager.vo.card.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/14 10:03
 */
@Component
public class CardFallbackFactory implements FallbackFactory<CardRpc> {
    @Override
    public CardRpc create(Throwable throwable) {

        return new CardRpc() {
            @Override
            public BaseRpcResponse openCard(URI baseUri, OpenCardVo vo) {
                return error();
            }

            @Override
            public List<OpenblukResp> openbulk(URI baseUri, OpenblukVo vo) {
                return errorList();
            }

            @Override
            public BaseRpcResponse logoutCard(URI baseUri, LogoutCardVo vo) {
                return error();
            }

            @Override
            public BaseRpcResponse manageCard(URI baseUri, ManagerCardVo vo) {
                return error();
            }

            private BaseRpcResponse error() {
                BaseRpcResponse resp = new BaseRpcResponse();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }
            private List<OpenblukResp> errorList() {
                List<OpenblukResp> list = new ArrayList<>();
                OpenblukResp resp = new OpenblukResp();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return list;
            }
        };
    }

}
