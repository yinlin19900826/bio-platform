package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.Device;
import com.biocome.platform.admin.vo.syncho.BuildAndBrandVo;
import com.biocome.platform.admin.vo.card.CardSnVo;
import com.biocome.platform.admin.vo.device.DeviceInfoResp;
import com.biocome.platform.admin.vo.device.DoorDeviceVo;
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

    /**
     * 根据楼栋所属区划代码与设备厂家信息查询封装指定类
     *
     * @param vo
     * @return java.util.List<DoorDeviceVo>
     * @Author shenlele
     * @Date 2019/5/15 15:49
     */
    List<DoorDeviceVo> selectByVo(@Param("vo") BuildAndBrandVo vo);

    /**
     * 根据sn和ip修改设备MAC地址和序列号
     *
     * @param device 参数
     * @return int
     * @Author shenlele
     * @Date 2019/5/21 10:06
     */
    int updateBySnAndIp(@Param("device") Device device);

    List<CardSnVo> selectSnByBuildCode(@Param("buildcode")String buildcode);

    List<CardSnVo> selectSnByBuildCodes(@Param("codes") List<String> codes);

    List<Device> selectSnAndCardByBuildCodes(@Param("codes") List<String> codes);

    /**
     * 根据device查询返回相关实体类
     *
     * @param device
     * @return java.util.List<DeviceInfoResp>
     * @Author shenlele
     * @Date 2019/5/28 10:28
     */
    List<DeviceInfoResp> selectByDevice(@Param("device") DeviceInfoResp device);
}
