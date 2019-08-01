package com.biocome.platform.wechatapplet.rpc.fallbackfactory;

import com.biocome.platform.wechatapplet.rpc.service.OpenDoorPasswordRpc;
import com.biocome.platform.wechatapplet.vo.visitor.OpendoorpasswordResp;
import com.biocome.platform.wechatapplet.vo.visitor.VisitorPasswordVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author hxy
 * @date 2019/7/30 16:12
 */
@Component
public class OpenDoorPasswordFallbackFactory implements FallbackFactory<OpenDoorPasswordRpc> {
    @Override
    public OpenDoorPasswordRpc create(Throwable throwable) {
        return new OpenDoorPasswordRpc() {

            @Override
            public OpendoorpasswordResp opendoorpassword(VisitorPasswordVo vo) {
                return new OpendoorpasswordResp("远程调用下发动态密码限流", "0", "204", null);
            }
        };
    }
}
