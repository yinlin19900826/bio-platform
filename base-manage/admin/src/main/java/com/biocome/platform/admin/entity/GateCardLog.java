package com.biocome.platform.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "gatecard_log")
public class GateCardLog {

    @Id
    private Integer id; //主键ID

    @Column(name = "physicalcard_no")
    private String physicalcardNo;  //物理卡号

    @Column(name = "logicalcard_no")
    private String logicalcardNo;  //逻辑卡号

    @Column(name = "credential_no")
    private String credentialNo;  //持卡人证件号

    @Column(name = "credential_type")
    private String credentialType;  //证件类型

    @Column(name = "change_type")
    private String changeType;  //变更类型

    @Column(name = "disable_startdate")
    private Date disableStartdate;  //禁用起始日期

    @Column(name = "disable_deadline")
    private Date disableDeadline; //禁用截止日期

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

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Date getDisableStartdate() {
        return disableStartdate;
    }

    public void setDisableStartdate(Date disableStartdate) {
        this.disableStartdate = disableStartdate;
    }

    public Date getDisableDeadline() {
        return disableDeadline;
    }

    public void setDisableDeadline(Date disableDeadline) {
        this.disableDeadline = disableDeadline;
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