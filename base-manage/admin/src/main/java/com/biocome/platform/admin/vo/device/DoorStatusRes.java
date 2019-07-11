package com.biocome.platform.admin.vo.device;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DoorStatusRes
 * @Author: shenlele
 * @Date: 2019/5/15 17:49
 * @Description:
 */
@ApiModel(value = "请求门状态返回实体类")
public class DoorStatusRes extends BaseRpcResponse {

    @ApiModelProperty(value = "设备编号")
    private String sn;
    @ApiModelProperty(value = "门状态（0 门开，1 门关）")
    private String doorstatus;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDoorstatus() {
        return doorstatus;
    }

    public void setDoorstatus(String doorstatus) {
        this.doorstatus = doorstatus;
    }
}
