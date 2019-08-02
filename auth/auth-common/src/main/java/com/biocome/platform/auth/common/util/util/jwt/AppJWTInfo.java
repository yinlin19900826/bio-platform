package com.biocome.platform.auth.common.util.util.jwt;

import java.io.Serializable;

/**
 * @ClassName: AppJWTInfo
 * @Author: zengqiang
 * @Date: 2019/8/2
 * @Description:
 */
public class AppJWTInfo extends JWTInfo {
    private String username;
    private String userId;
    private String name;
    private String usercode;
    private String effectiveCode;

    public AppJWTInfo(String username, String userId, String name){
        super(username, userId, name);
    }

    public AppJWTInfo(String username, String userId, String name, String effectiveCode){
        this(username, userId, name);
        this.effectiveCode = effectiveCode;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    @Override
    public String getUniqueName() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
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

    public String getEffectiveCode() {
        return effectiveCode;
    }

    public void setEffectiveCode(String effectiveCode) {
        this.effectiveCode = effectiveCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppJWTInfo jwtInfo = (AppJWTInfo) o;

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
}
