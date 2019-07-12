package com.biocome.platform.basemanager.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_log")
public class UserLog {

    private Integer id; //主键ID

    @Column(name = "user_name")
    private String userName;  //用户名

    @Column(name = "opt_name")
    private String optName;  //操作人

    private String station;  //单位

    @Column(name = "login_ip")
    private String loginIp;  //登录ip

    @Column(name = "opt_time")
    private Date optTime;  //操作时间

    @Column(name = "opt_business")
    private String optBusiness;  //操作业务

    @Column(name = "opt_details")
    private String optDetails; //操作详情

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getOptBusiness() {
        return optBusiness;
    }

    public void setOptBusiness(String optBusiness) {
        this.optBusiness = optBusiness;
    }

    public String getOptDetails() {
        return optDetails;
    }

    public void setOptDetails(String optDetails) {
        this.optDetails = optDetails;
    }
}