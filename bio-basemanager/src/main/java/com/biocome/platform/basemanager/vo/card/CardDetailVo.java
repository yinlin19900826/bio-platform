package com.biocome.platform.basemanager.vo.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("门禁卡详情")
public class CardDetailVo {
    @ApiModelProperty("开卡用户")
    private String usercode;
    @ApiModelProperty("用户姓名")
    private String username;
    @ApiModelProperty("手机号码")
    private String tel;
    @ApiModelProperty("门禁卡号")
    private String cardNo;
    @ApiModelProperty("门禁卡状态")
    private String cardStatus;
    @ApiModelProperty("开卡时间")
    private String createtime;
    @ApiModelProperty("备注")
    private String remark;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
