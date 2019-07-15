package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.Device;
import com.biocome.platform.basemanager.vo.req.device.CardSnVo;
import com.biocome.platform.basemanager.vo.resp.device.DeviceInfoResp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: hxy
 * @Date:2019/5/7 11:29
 * @Description:
 */
public interface DeviceMapper extends Mapper<Device> {
    /**
     * @param deviceName 设备名称
     * @param sn         设备编号
     * @param brand      设备品牌
     * @param estatecode 小区编码
     * @return
     */
    public List<DeviceInfoResp> selectByAdditionForList(@Param("deviceName") String deviceName, @Param("sn") String sn,
                                                        @Param("brand") String brand, @Param("estatecode") String estatecode, @Param("id") Integer id);

    /**
     * @param list 主键list
     * @return
     */
    public int deleteDevice(@Param("list") List<Integer> list);

}
