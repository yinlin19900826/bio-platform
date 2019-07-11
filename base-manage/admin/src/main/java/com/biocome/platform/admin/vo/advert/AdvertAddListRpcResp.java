package com.biocome.platform.admin.vo.advert;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/5/30 16:24
 */
@ApiModel(value = "批量下发广告返回")
public class AdvertAddListRpcResp extends BaseRpcResponse{
    @ApiModelProperty(value = "设备编号")
    private String sn;
    @ApiModelProperty(value = "广告编号")
    private String adno;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAdno() {
        return adno;
    }

    public void setAdno(String adno) {
        this.adno = adno;
    }

    @Override
    public String toString() {
        return "AdvertAddListRpcResp{" +
                "sn='" + sn + '\'' +
                ", adno='" + adno + '\'' +
                '}';
    }
}
