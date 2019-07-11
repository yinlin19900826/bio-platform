package com.biocome.platform.admin.vo.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019.5.6
 */
@ApiModel(value = "卡设备编号")
public class CardSnVo {
    @ApiModelProperty(value = "设备编号")
    private String sn;

    public String getSn() {
        return this.sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
