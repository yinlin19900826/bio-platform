package com.biocome.platform.wechatapplet.rpc.fallbackfactory;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.inter.basemanager.vo.card.LogoutCardVo;
import com.biocome.platform.inter.basemanager.vo.card.OpenCardVo;
import com.biocome.platform.wechatapplet.rpc.service.CardRpc;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @ClassName: CardFallbackFactory
 * @Author: shenlele
 * @Date: 2019/7/29 18:01
 * @Description:
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
            public BaseRpcResponse logoutCard(URI baseUri, LogoutCardVo vo) {
                return error();
            }

            private BaseRpcResponse error() {
                BaseRpcResponse resp = new BaseRpcResponse();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }
        };
    }

}
