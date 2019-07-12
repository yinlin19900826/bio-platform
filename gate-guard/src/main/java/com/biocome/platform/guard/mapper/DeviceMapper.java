package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.Device;
import com.biocome.platform.guard.vo.device.DeviceInfoResp;
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
     * 根据device查询返回相关实体类
     *
     * @param device
     * @return java.util.List<DeviceInfoResp>
     * @Author shenlele
     * @Date 2019/5/28 10:28
     */
    List<DeviceInfoResp> selectByDevice(@Param("device") DeviceInfoResp device);

    /**
     * 根据楼栋编号列表查询设备与卡信息
     *
     * @param codes 楼栋编号列表
     * @return java.util.List<com.biocome.platform.guard.entity.Device>
     * @Author shenlele
     * @Date 2019/7/12 9:13
     */
    List<Device> selectSnAndCardByBuildCodes(@Param("codes") List<String> codes);
}
