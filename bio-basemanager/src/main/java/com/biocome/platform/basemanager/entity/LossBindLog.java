package com.biocome.platform.basemanager.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "lossbind_log")
public class LossBindLog {

    @Id
    private Integer id; //主键ID

    @Column(name = "tenant_name")
    private String tenantName;  //租户姓名

    @Column(name = "physicalcard_no")
    private String physicalcardNo;  //物理卡号

    @Column(name = "logicalcard_no")
    private String logicalcardNo;  //逻辑卡号

    @Column(name = "cancelcard_no")
    private String cancelcardNo;  //已注销卡号

    @Column(name = "opt_type")
    private String optType;  //操作类型

    @Column(name = "opt_name")
    private String optName;  //操作人

    @Column(name = "opt_time")
    private Date optTime;  //操作时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPhysicalcardNo() {
        return physicalcardNo;
    }

    public void setPhysicalcardNo(String physicalcardNo) {
        this.physicalcardNo = physicalcardNo;
    }

    public String getLogicalcardNo() {
        return logicalcardNo;
    }

    public void setLogicalcardNo(String logicalcardNo) {
        this.logicalcardNo = logicalcardNo;
    }

    public String getCancelcardNo() {
        return cancelcardNo;
    }

    public void setCancelcardNo(String cancelcardNo) {
        this.cancelcardNo = cancelcardNo;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }
}