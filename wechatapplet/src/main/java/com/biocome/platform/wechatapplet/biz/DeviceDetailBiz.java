package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.wechatapplet.mapper.DeviceDetailMapper;
import com.biocome.platform.wechatapplet.vo.device.DeviceDetailResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 14:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceDetailBiz {

    @Autowired
    DeviceDetailMapper mapper;

    public List<DeviceDetailResp> getDeviceByBuildcode(String buildcode) {
        return mapper.getDeviceByBuildcode(buildcode);
    }
}
