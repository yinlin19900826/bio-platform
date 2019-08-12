package com.biocome.platform.guard.feign;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.rpc.fallbackfactory.AdvertFallbackFactory;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.biocome.platform.inter.basemanager.vo.card.OpenCardVo;
import com.biocome.platform.inter.gateguard.vo.user.AppAccountVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

/**
 * @ClassName: AppUserRpc
 * @Author: zengqiang
 * @Date: 2019/8/9
 * @Description:
 */
@FeignClient(value = "wechat-applet",configuration = {})
public interface AppAccountService {
    @RequestMapping(value={"/app/user/createAppAccount"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
    BaseResponse createAppAccount(AppAccountVo vo);
}
