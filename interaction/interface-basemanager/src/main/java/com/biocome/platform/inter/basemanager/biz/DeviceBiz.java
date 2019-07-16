package com.biocome.platform.inter.basemanager.biz;

import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.mapper.DeviceMapper;
import com.biocome.platform.inter.basemanager.vo.card.CardSnVo;
import com.biocome.platform.inter.basemanager.vo.device.DeviceInfoResp;
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

    /**
     * 根据sn和ip修改设备MAC地址和序列号
     *
     * @param device 参数
     * @return int
     * @Author shenlele
     * @Date 2019/5/21 10:05
     */
    public int updateBySnAndIp(Device device) throws Exception{
        return mapper.updateBySnAndIp(device);
    }

    /***
     * 根据楼栋列表查找设备号
     * @param codes
     * @return
     */
    public List<CardSnVo> selectSnByBuildCodes(List<String> codes) {
        return mapper.selectSnByBuildCodes(codes);
    }
}
