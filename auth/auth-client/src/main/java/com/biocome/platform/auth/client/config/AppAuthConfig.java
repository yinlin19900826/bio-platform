package com.biocome.platform.auth.client.config;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengqiang on 2019/8/2.
 */
public class AppAuthConfig {

    @Value("${auth.app.access.token-header}")
    private String accessTokenHeader;

    @Value("${auth.app.refresh.token-header}")
    private String refreshTokenHeader;

    @Value("${auth.app.access.expire}")
    private int accessExpire;

    private byte[] pubKeyByte;

    public String getAccessTokenHeader() {
        return accessTokenHeader;
    }

    public void setAccessTokenHeader(String accessTokenHeader) {
        this.accessTokenHeader = accessTokenHeader;
    }

    public String getRefreshTokenHeader() {
        return refreshTokenHeader;
    }

    public void setRefreshTokenHeader(String refreshTokenHeader) {
        this.refreshTokenHeader = refreshTokenHeader;
    }

    public String getAccessToken(HttpServletRequest request){
        return request.getHeader(this.getAccessTokenHeader());
    }

    public String getRefreshToken(HttpServletRequest request){
        return request.getHeader(this.getRefreshTokenHeader());
    }

    public byte[] getPubKeyByte() {
        return pubKeyByte;
    }

    public void setPubKeyByte(byte[] pubKeyByte) {
        this.pubKeyByte = pubKeyByte;
    }

    public int getAccessExpire() {
        return accessExpire;
    }

    public void setAccessExpire(int accessExpire) {
        this.accessExpire = accessExpire;
    }
}
