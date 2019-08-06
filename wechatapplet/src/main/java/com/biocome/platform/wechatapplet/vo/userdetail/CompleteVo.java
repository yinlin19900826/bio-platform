package com.biocome.platform.wechatapplet.vo.userdetail;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName: CompleteVo
 * @Author: shenlele
 * @Date: 2019/8/2 09:56
 * @Description: 完善信息VO类
 */
@ApiModel(value = "完善信息VO类")
public class CompleteVo {

    @ApiModelProperty(value = "用户编码")
    private String usercode;

    @ApiModelProperty(value = "手机号码")
    private String tel;

    @ApiModelProperty(value = "户口所在地类型（0：本地城镇1：本地农村2：省内城镇3：省内农村4：省外城镇5：省外农村）")
    private Integer domiciletype;

    @ApiModelProperty(value = "婚姻状况（0：未婚1：已婚2：离异）")
    private Integer maritalstatus;

    @ApiModelProperty(value = "文化程度（0：博士1：硕士2：本科3：大专4：中专5：中技6：技工学校7：高中8：初中9：小学10：文盲11：半文盲）")
    private Integer culture;

    @ApiModelProperty(value = "职业")
    private String occupation;

    @ApiModelProperty(value = "紧急联系人姓名")
    private String emergencyname;

    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencytel;

    @ApiModelProperty(value = "工作单位")
    private String workunit;

    @ApiModelProperty(value = "来深圳事由（0：工作1：嫁娶2：旅游）")
    private Integer reason;

    @ApiModelProperty(value = "与户主关系（0：合租人1：直系亲属2：承租人）")
    private Integer relation;

    @ApiModelProperty(value = "身高")
    private String high;

    @ApiModelProperty(value = "政治面貌(1中共党员2中共预备党员3共青团员4民革党员5致公党党员6九三学社社员7台盟盟员8民盟盟员9民建会员10民进会员11农工党党员12群众)")
    private Integer politicsstatus;

    @ApiModelProperty(value = "居住方式（0：合租1：整租2：购买）")
    private Integer residentmanner;

    @ApiModelProperty(value = "首次来深日期")
    private Date firsttime;

    @ApiModelProperty(value = "是否告知办理居住证(0是1否)")
    private Integer sfgzbljzz;

    @ApiModelProperty(value = "是否告知地址改写(0是1否)")
    private Integer sfgzdzgx;

    @ApiModelProperty(value = "生育动态(0：无1：待孕2：已孕3：有子女)")
    private Integer fertility;

    @ApiModelProperty(value = "避孕动态(0：有1：无)")
    private Integer birthcontrol;

    @ApiModelProperty(value = "是否发卡(0是1否)")
    private Integer iscard;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDomiciletype() {
        return domiciletype;
    }

    public void setDomiciletype(Integer domiciletype) {
        this.domiciletype = domiciletype;
    }

    public Integer getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(Integer maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public Integer getCulture() {
        return culture;
    }

    public void setCulture(Integer culture) {
        this.culture = culture;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmergencyname() {
        return emergencyname;
    }

    public void setEmergencyname(String emergencyname) {
        this.emergencyname = emergencyname;
    }

    public String getEmergencytel() {
        return emergencytel;
    }

    public void setEmergencytel(String emergencytel) {
        this.emergencytel = emergencytel;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public Integer getPoliticsstatus() {
        return politicsstatus;
    }

    public void setPoliticsstatus(Integer politicsstatus) {
        this.politicsstatus = politicsstatus;
    }

    public Integer getResidentmanner() {
        return residentmanner;
    }

    public void setResidentmanner(Integer residentmanner) {
        this.residentmanner = residentmanner;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Integer getSfgzbljzz() {
        return sfgzbljzz;
    }

    public void setSfgzbljzz(Integer sfgzbljzz) {
        this.sfgzbljzz = sfgzbljzz;
    }

    public Integer getSfgzdzgx() {
        return sfgzdzgx;
    }

    public void setSfgzdzgx(Integer sfgzdzgx) {
        this.sfgzdzgx = sfgzdzgx;
    }

    public Integer getFertility() {
        return fertility;
    }

    public void setFertility(Integer fertility) {
        this.fertility = fertility;
    }

    public Integer getBirthcontrol() {
        return birthcontrol;
    }

    public void setBirthcontrol(Integer birthcontrol) {
        this.birthcontrol = birthcontrol;
    }

    public Integer getIscard() {
        return iscard;
    }

    public void setIscard(Integer iscard) {
        this.iscard = iscard;
    }
}
