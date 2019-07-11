package com.biocome.platform.admin.vo.device;

import com.biocome.platform.admin.vo.common.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DeviceSnVo
 * @Author: shenlele
 * @Date: 2019/5/15 17:46
 * @Description:
 */
@ApiModel(value = "设备编号实体类")
public class DeviceSnVo extends CommonVo {

    @ApiModelProperty(value = "设备编号")
    private String sn;

    public DeviceSnVo(){}

    public DeviceSnVo(String token, String sn) {
        this.setToken(token);
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
