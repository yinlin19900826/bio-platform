package com.biocome.platform.auth.common.util.util.jwt;

import java.io.Serializable;

/**
 * Created by ace on 2017/9/10.
 */
public class JWTInfo implements Serializable,IJWTInfo {
    private String username;
    private String userId;
    private String name;
    private String usercode;
    private String effectiveCode;
    private String endtime;

    public JWTInfo(String username, String userId, String name) {
        this.username = username;
        this.userId = userId;
        this.name = name;
    }

    public JWTInfo(String username, String userId, String name, String usercode, String effectiveCode, String endtime){
        this.username = username;
        this.usercode = usercode;
        this.userId = userId;
        this.name = name;
        this.effectiveCode = effectiveCode;
        this.endtime = endtime;
    }

    @Override
    public String getUniqueName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JWTInfo jwtInfo = (JWTInfo) o;

        if (username != null ? !username.equals(jwtInfo.username) : jwtInfo.username != null) {
            return false;
        }
        return userId != null ? userId.equals(jwtInfo.userId) : jwtInfo.userId == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
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

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
