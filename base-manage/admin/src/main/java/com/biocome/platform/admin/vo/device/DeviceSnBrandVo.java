package com.biocome.platform.admin.vo.device;

import com.biocome.platform.admin.vo.common.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DeviceSnBrandVo
 * @Author: shenlele
 * @Date: 2019/5/15 17:46
 * @Description:
 */
@ApiModel(value = "设备编号实体类")
public class DeviceSnBrandVo extends CommonVo {

    @ApiModelProperty(value = "设备厂家")
    private String brandcode;
    @ApiModelProperty(value = "设备编号")
    private String sn;

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
