package com.biocome.platform.admin.rpc.fallbackfactory;

import com.biocome.platform.admin.vo.device.DeviceSnVo;
import com.biocome.platform.admin.vo.device.DeviceStatusRes;
import com.biocome.platform.admin.vo.device.DoorStatusRes;
import com.biocome.platform.admin.rpc.service.DeviceRpc;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @ClassName: DeviceFallbackFactory
 * @Author: shenlele
 * @Date: 2019/5/15 17:44
 * @Description:
 */
@Component
public class DeviceFallbackFactory  implements FallbackFactory<DeviceRpc> {

    @Override
    public DeviceRpc create(Throwable throwable) {
        return new DeviceRpc() {
            private DeviceStatusRes deviceError() {
                DeviceStatusRes resp = new DeviceStatusRes();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }

            private DoorStatusRes doorError() {
                DoorStatusRes resp = new DoorStatusRes();
                resp.setErrorcode("105");
                resp.setMessage("服务调用失败");
                resp.setResult("0");
                return resp;
            }

            @Override
            public DeviceStatusRes deviceStatus(URI baseUri, DeviceSnVo deviceSnVo) {
                return deviceError();
            }

            @Override
            public DoorStatusRes doorStatus(URI baseUri, DeviceSnVo deviceSnVo) {
                return doorError();
            }
        };
    }
}
