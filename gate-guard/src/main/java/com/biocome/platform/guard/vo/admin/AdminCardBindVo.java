package com.biocome.platform.guard.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("管理员门禁卡绑定信息")
public class AdminCardBindVo {
    @ApiModelProperty("楼栋编码")
    private String buildCode;
    @ApiModelProperty("楼栋名称")
    private String buildName;
    @ApiModelProperty("是否绑定 true:绑定 false:未绑定")
    private boolean bind;
    @ApiModelProperty("绑定id 作为请求参数时，有id则传入，无id则不传")
    private Integer id;

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public boolean isBind() {
        return bind;
    }

    public void setBind(boolean bind) {
        this.bind = bind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
