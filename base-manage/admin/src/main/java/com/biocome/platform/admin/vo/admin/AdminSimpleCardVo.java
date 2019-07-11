package com.biocome.platform.admin.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("归属管理员门禁卡信息")
public class AdminSimpleCardVo {
    @ApiModelProperty(value = "使用者用户编码")
    private String usercode;
    @ApiModelProperty(value = "门禁卡号")
    private String cardNo;
    @ApiModelProperty(value = "门禁卡状态")
    private String cardStatus;
    @ApiModelProperty(value = "开卡时间")
    private String createTime;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
