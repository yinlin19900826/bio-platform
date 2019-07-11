package com.biocome.platform.admin.vo.showmanage;

import com.biocome.platform.admin.vo.device.DeviceInfoResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: ShowManageVo
 * @Author: shenlele
 * @Date: 2019/5/15 19:41
 * @Description:
 */
@ApiModel(value = "门禁管理展示实体类")
public class ShowManageVo {
    /**
     * 楼栋房屋
     */
    @ApiModelProperty(value = "楼栋房屋信息")
    private BuildAndHouseVo buildAndHouse;
    /**
     * 设备信息与状态信息
     */
    @ApiModelProperty(value = "设备信息")
    private List<DeviceInfoResp> devices;
    /**
     * 房东信息
     */
    @ApiModelProperty(value = "房东信息")
    private String landlords;

    public BuildAndHouseVo getBuildAndHouse() {
        return buildAndHouse;
    }

    public void setBuildAndHouse(BuildAndHouseVo buildAndHouse) {
        this.buildAndHouse = buildAndHouse;
    }

    public List<DeviceInfoResp> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceInfoResp> devices) {
        this.devices = devices;
    }

    public String getLandlords() {
        return landlords;
    }

    public void setLandlords(String landlords) {
        this.landlords = landlords;
    }
}
