package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.wechatapplet.rpc.fallbackfactory.WinXinPushFallbackFactory;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsAlarmCommonVo;
import com.biocome.platform.wechatapplet.vo.duanxin.IacsResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hxy
 * Date: 2019-08-07
 * Time: 10:34
 */
@FeignClient(name="weixin-push", url="${weixin.sendurl}",fallbackFactory = WinXinPushFallbackFactory.class)
public interface WinxinPushService {


    @RequestMapping(value={"/alarmCommon"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
    public IacsResp alarmWeixin(@RequestBody IacsAlarmCommonVo model);

}
