package com.biocome.platform.admin.vo.otherRpc;

import com.biocome.platform.admin.vo.common.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: AddressVo
 * @Author: shenlele
 * @Date: 2019/5/20 17:37
 * @Description:
 */
@ApiModel(value = "请求设备MAC地址实体类")
public class AddressVo extends CommonVo {

    @ApiModelProperty(value = "设备编码（唯一性）")
    private String sn;
    @ApiModelProperty(value = "设备IP地址")
    private String deviceip;

    public AddressVo(){}

    public AddressVo(String token, String sn, String deviceip) {
        this.setToken(token);
        this.sn = sn;
        this.deviceip = deviceip;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDeviceip() {
        return deviceip;
    }

    public void setDeviceip(String deviceip) {
        this.deviceip = deviceip;
    }
}
