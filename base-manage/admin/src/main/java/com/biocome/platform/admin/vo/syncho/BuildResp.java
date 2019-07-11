package com.biocome.platform.admin.vo.syncho;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: BuildResp
 * @Author: shenlele
 * @Date: 2019/5/14 16:51
 * @Description:
 */
@ApiModel(value = "远程同步楼栋返回实体类")
public class BuildResp extends BaseRpcResponse {

    @ApiModelProperty(value = "楼幢编码")
    private String buildcode;

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }
}
