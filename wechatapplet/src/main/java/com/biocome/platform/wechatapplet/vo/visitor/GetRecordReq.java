package com.biocome.platform.wechatapplet.vo.visitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/7/31 13:46
 */
@ApiModel(value = "获取记录请求")
public class GetRecordReq {
    @ApiModelProperty(value = "用户编号")
    private String usercode;
    @ApiModelProperty(value = "1 待使用 0 已失效")
    private String status;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
