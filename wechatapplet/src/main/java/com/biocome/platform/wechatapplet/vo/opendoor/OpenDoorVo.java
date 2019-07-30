package com.biocome.platform.wechatapplet.vo.opendoor;

/**
 * @ClassName: OpenDoor
 * @Author: shenlele
 * @Date: 2019/7/29 18:01
 * @Description:
 */
public class OpenDoorVo {
    private String token;
    private String sn;
    private String usercode;
    private String userdesc;
    private String usertype;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
