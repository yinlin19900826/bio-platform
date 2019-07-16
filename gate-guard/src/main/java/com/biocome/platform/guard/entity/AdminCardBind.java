package com.biocome.platform.guard.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 管理员卡
 *
 * @author zengqiang
 * @email 360713542@qq.com
 * @date 2019-05-17 10:06:14
 */
@Table(name = "admin_card_bind")
public class AdminCardBind {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    //逻辑卡号
    @Column(name = "logic_cardno")
    private String logicCardno;

    //物理卡号
    @Column(name = "physical_cardno")
    private String physicalCardno;

    //是否有效(0、注销，1、有效，2、发卡 3、禁用 4、黑名单)',
    @Column(name = "isalive")
    private Integer isalive;

    //createtime
    @Column(name = "createtime")
    private Date createtime;

    //卡种类(1、IC卡 2、CPU卡 3、深圳通卡)
    @Column(name = "cardkind")
    private String cardkind;

    //管理员编码
    @Column(name = "usercode")
    private String usercode;

    //楼栋编码
    @Column(name = "build_code")
    private String buildCode;

    //楼栋名称
    @Column(name = "build_name")
    private String buildName;

    //卡类型
    @Column(name = "cardtype")
    private String cardtype;

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
     * 设置：逻辑卡号
     */
    public void setLogicCardno(String logicCardno) {
        this.logicCardno = logicCardno;
    }

    /**
     * 获取：逻辑卡号
     */
    public String getLogicCardno() {
        return logicCardno;
    }

    /**
     * 设置：物理卡号
     */
    public void setPhysicalCardno(String physicalCardno) {
        this.physicalCardno = physicalCardno;
    }

    /**
     * 获取：物理卡号
     */
    public String getPhysicalCardno() {
        return physicalCardno;
    }

    /**
     * 设置：是否有效(0、注销，1、有效，2、发卡 3、禁用 4、黑名单)',
     */
    public void setIsalive(Integer isalive) {
        this.isalive = isalive;
    }

    /**
     * 获取：是否有效(0、注销，1、有效，2、发卡 3、禁用 4、黑名单)',
     */
    public Integer getIsalive() {
        return isalive;
    }

    /**
     * 设置：createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取：createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置：卡种类(1、IC卡 2、CPU卡 3、深圳通卡)
     */
    public String getCardkind() {
        return cardkind;
    }

    public void setCardkind(String cardkind) {
        this.cardkind = cardkind;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    /**
     * 获取：卡种类(1、IC卡 2、CPU卡 3、深圳通卡)
     */

    /**
     * 设置：管理员编码
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    /**
     * 获取：管理员编码
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * 设置：楼栋编码
     */
    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    /**
     * 获取：楼栋编码
     */
    public String getBuildCode() {
        return buildCode;
    }

    /**
     * 设置：楼栋名称
     */
    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    /**
     * 获取：楼栋名称
     */
    public String getBuildName() {
        return buildName;
    }


}
