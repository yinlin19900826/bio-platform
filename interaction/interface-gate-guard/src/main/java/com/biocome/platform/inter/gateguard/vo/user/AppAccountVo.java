package com.biocome.platform.inter.gateguard.vo.user;

import java.util.Date;

/**
 * @ClassName: AppAccountVo
 * @Author: zengqiang
 * @Date: 2019/8/9
 * @Description:
 */
public class AppAccountVo {
    private String usercode;
    private String username;
    private String password;
    private String createUser;
    private Date createTime;
    private int type;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
