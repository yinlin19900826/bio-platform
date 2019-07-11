package com.biocome.platform.guard.otherrpc;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: AddressResp
 * @Author: shenlele
 * @Date: 2019/5/20 17:38
 * @Description:
 */
@ApiModel(value = "请求设备MAC地址返回实体类")
public class AddressResp extends BaseRpcResponse {

    @ApiModelProperty(value = "Mac地址,格式 00:EE:6E:4D:01:25")
    private String macaddr;
    @ApiModelProperty(value = "设备序列号")
    private String serialno;

    public String getMacaddr() {
        return macaddr;
    }

    public void setMacaddr(String macaddr) {
        this.macaddr = macaddr;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}
