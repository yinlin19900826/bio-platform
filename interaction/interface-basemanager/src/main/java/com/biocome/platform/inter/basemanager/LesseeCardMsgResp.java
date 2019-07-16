package com.biocome.platform.inter.basemanager;

/**
 * @author hxy
 * @date 2019/5/13 11:41
 * @description 用户开卡信息返回
 */
public class LesseeCardMsgResp {
    /**
     * 卡片主键ID
     */
    private Integer id;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 证件号码
     */
    private String idNumber;
    /**
     * 物理卡号
     */
    private String physicalCardno;
    /**
     * 入住时间
     */
    private String checkintime;
    /**
     * 楼栋编号
     */
    private String buildcode;
    /**
     * 楼栋名称
     */
    private String buildName;
    /**
     * 小区编号
     */
    private String estatecode;
    /**
     * 小区名称
     */
    private String estateName;
    /**
     * 租户状态
     */
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhysicalCardno() {
        return physicalCardno;
    }

    public void setPhysicalCardno(String physicalCardno) {
        this.physicalCardno = physicalCardno;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
