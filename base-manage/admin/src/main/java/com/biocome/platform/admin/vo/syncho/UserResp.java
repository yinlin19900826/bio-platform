package com.biocome.platform.admin.vo.syncho;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: UserResp
 * @Author: shenlele
 * @Date: 2019/5/14 17:15
 * @Description:
 */
@ApiModel(value = "远程同步租户返回实体类")
public class UserResp extends BaseRpcResponse {

    @ApiModelProperty(value = "人员编号")
    private String usercode;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
}
