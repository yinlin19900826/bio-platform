package com.biocome.platform.wechatapplet.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName: AppUser
 * @Author: zengqiang
 * @Date: 2019/8/5
 * @Description:
 */
@ApiModel("app用户信息")
public class AppUserVo {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("usercode")
    private String usercode;
    @ApiModelProperty("是否完成资料")
    private Integer iscomplete;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("户口所在地类型")
    private Integer domiciletype;
    @ApiModelProperty("婚姻状况")
    private Integer marriage;
    @ApiModelProperty("文化程度")
    private Integer education;
    @ApiModelProperty("职业")
    private Integer career;
    @ApiModelProperty("居住处所类型")
    private Integer residenceType;
    @ApiModelProperty("工作单位")
    private String workOrganization;
    @ApiModelProperty("来自地区")
    private Integer fromPlaceType;
    @ApiModelProperty("来深居住事由")
    private Integer comeReason;
    @ApiModelProperty("与户主关系")
    private Integer realtionship;
    @ApiModelProperty("身高")
    private Double height;
    @ApiModelProperty("政治面貌")
    private Integer politicPosition;
    @ApiModelProperty("行业")
    private String profession;
    @ApiModelProperty("居住方式")
    private Integer dwellType;
    @ApiModelProperty("职称")
    private Integer professionalTitle;
    @ApiModelProperty("首次来深日期")
    private String arriveDate;
    @ApiModelProperty("是否告知办理居住证")
    private Integer residePermitNotify;
    @ApiModelProperty("是否告知地址改写")
    private Integer placeChangeNotify;
    @ApiModelProperty("生育动态")
    private Integer reproduction;
    @ApiModelProperty("避孕动态")
    private String contraception;
    @ApiModelProperty("持卡动态")
    private String cardDynamic;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDomiciletype() {
        return domiciletype;
    }

    public void setDomiciletype(Integer domiciletype) {
        this.domiciletype = domiciletype;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getCareer() {
        return career;
    }

    public void setCareer(Integer career) {
        this.career = career;
    }

    public Integer getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(Integer residenceType) {
        this.residenceType = residenceType;
    }

    public String getWorkOrganization() {
        return workOrganization;
    }

    public void setWorkOrganization(String workOrganization) {
        this.workOrganization = workOrganization;
    }

    public Integer getFromPlaceType() {
        return fromPlaceType;
    }

    public void setFromPlaceType(Integer fromPlaceType) {
        this.fromPlaceType = fromPlaceType;
    }

    public Integer getComeReason() {
        return comeReason;
    }

    public void setComeReason(Integer comeReason) {
        this.comeReason = comeReason;
    }

    public Integer getRealtionship() {
        return realtionship;
    }

    public void setRealtionship(Integer realtionship) {
        this.realtionship = realtionship;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getPoliticPosition() {
        return politicPosition;
    }

    public void setPoliticPosition(Integer politicPosition) {
        this.politicPosition = politicPosition;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getDwellType() {
        return dwellType;
    }

    public void setDwellType(Integer dwellType) {
        this.dwellType = dwellType;
    }

    public Integer getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(Integer professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Integer getResidePermitNotify() {
        return residePermitNotify;
    }

    public void setResidePermitNotify(Integer residePermitNotify) {
        this.residePermitNotify = residePermitNotify;
    }

    public Integer getPlaceChangeNotify() {
        return placeChangeNotify;
    }

    public void setPlaceChangeNotify(Integer placeChangeNotify) {
        this.placeChangeNotify = placeChangeNotify;
    }

    public Integer getReproduction() {
        return reproduction;
    }

    public void setReproduction(Integer reproduction) {
        this.reproduction = reproduction;
    }

    public String getContraception() {
        return contraception;
    }

    public void setContraception(String contraception) {
        this.contraception = contraception;
    }

    public String getCardDynamic() {
        return cardDynamic;
    }

    public void setCardDynamic(String cardDynamic) {
        this.cardDynamic = cardDynamic;
    }
}
