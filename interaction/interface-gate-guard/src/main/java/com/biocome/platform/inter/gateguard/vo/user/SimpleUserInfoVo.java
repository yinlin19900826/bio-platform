package com.biocome.platform.inter.gateguard.vo.user;

/**
 * @ClassName: SimpleUserInfoVo
 * @Author: zengqiang
 * @Date: 2019/8/7
 * @Description:
 */
public class SimpleUserInfoVo {
    private String usercode;
    private String name;
    private String headPhoto;
    private String phone;
    private String phoneType;
    private String cardNo;
    private String checkinTime;
    private boolean isCharger;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public boolean isCharger() {
        return isCharger;
    }

    public void setCharger(boolean charger) {
        isCharger = charger;
    }
}
