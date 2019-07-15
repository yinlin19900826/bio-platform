package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.Device;
import com.biocome.platform.basemanager.mapper.DeviceMapper;
import com.biocome.platform.basemanager.vo.req.device.CardSnVo;
import com.biocome.platform.basemanager.vo.resp.device.DeviceInfoResp;
import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/7 11:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceBiz extends BaseBiz<DeviceMapper, Device> {

    public List<DeviceInfoResp> selectByAdditionForList(String deviceName, String sn, String brand, String estatecode, Integer id) {
        return mapper.selectByAdditionForList(deviceName, sn, brand, estatecode, id);
    }

    public int insertDevice(Device device) throws Exception {
        return mapper.insertSelective(device);
    }

    public int updateDevice(Device device) throws Exception {
        return mapper.updateByPrimaryKey(device);
    }

    public int deleteDevice(List<Integer> device) throws Exception {
        return mapper.deleteDevice(device);
    }
}
