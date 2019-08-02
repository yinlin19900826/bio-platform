package com.biocome.platform.common.vo.user;

import java.io.Serializable;

/**
 * @ClassName: AppUserInfo
 * @Author: zengqiang
 * @Date: 2019/8/2
 * @Description:
 */
public class AppUserInfo implements Serializable {
    private String id;
    private String username;
    private String name;
    private String usercode;
    private String effectiveCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getEffectiveCode() {
        return effectiveCode;
    }

    public void setEffectiveCode(String effectiveCode) {
        this.effectiveCode = effectiveCode;
    }
}
