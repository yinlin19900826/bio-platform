package com.biocome.platform.wechatapplet.vo.build;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/7/30 10:32
 */
@ApiModel(value = "楼栋详情")
public class BuildDetailResp{
    @ApiModelProperty(value = "楼栋名称")
    private String buildName;
    @ApiModelProperty(value = "楼栋编号")
    private String buildCode;

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }
}
