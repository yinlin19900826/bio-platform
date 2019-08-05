package com.biocome.platform.inter.gateguard.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-07-31 18:22:58
 */
@Table(name = "app_user")
public class AppUser {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //用户名
    @Column(name = "username")
    private String username;

    //密码
    @Column(name = "password")
    private String password;

    //usercode
    @Column(name = "usercode")
    private String usercode;

    //usercode
    @Column(name = "type")
    private Integer type;

    /**
     * 是否完善信息（0是1否）
     */
    @Column(name = "iscomplete")
    private Integer iscomplete;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //创建人
    @Column(name = "create_user")
    private String createUser;

    //登录时间
    @Column(name = "login_time")
    private Date loginTime;

    //登录ip
    @Column(name = "login_ip")
    private String loginIp;


    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Integer getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(Integer iscomplete) {
        this.iscomplete = iscomplete;
    }


    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置：登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取：登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置：登录ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取：登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
