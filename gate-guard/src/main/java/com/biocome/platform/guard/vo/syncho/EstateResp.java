package com.biocome.platform.guard.vo.syncho;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: EstateResp
 * @Author: shenlele
 * @Date: 2019/5/14 16:37
 * @Description:
 */
@ApiModel(value = "远程同步小区返回实体类")
public class EstateResp extends BaseRpcResponse {

    @ApiModelProperty(value = "小区编码")
    private String estatecode;

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }
}
