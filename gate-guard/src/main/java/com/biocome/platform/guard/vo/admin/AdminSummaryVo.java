package com.biocome.platform.guard.vo.admin;

import com.biocome.platform.common.util.IdUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("管理员信息")
public class AdminSummaryVo {
    @ApiModelProperty(value = "管理员id")
    private int adminId;
    @ApiModelProperty(value = "管理员编码")
    private String usercode;
    @ApiModelProperty(value = "管理员名称")
    private String username;
    @ApiModelProperty(value = "性别")
    private String gendar;
    @ApiModelProperty(value = "管理员类别")
    private String landlordtype;
    @ApiModelProperty(value = "管理员类别名称")
    private String landlordtypename;
    @ApiModelProperty(value = "证件类别")
    private String paperstype;
    @ApiModelProperty(value = "证件号码")
    private String papersnum;
    @ApiModelProperty(value = "照片")
    private String photo;
    @ApiModelProperty(value = "证件照")
    private String papersphoto;
    @ApiModelProperty(value = "电话号码")
    private String tel;
    @ApiModelProperty(value = "管理权限")
    private String authName;
    private String cards;
    @ApiModelProperty(value = "自用卡")
    private List<String> cardList;
    @ApiModelProperty(value = "门禁卡数量")
    private Integer cardNum;
    @ApiModelProperty(value = "楼栋数量")
    private Integer buildingNum;
    @ApiModelProperty(value = "是否开通app")
    private String appOpen;
    @ApiModelProperty(value = "登记人")
    private String regPerson;
    @ApiModelProperty(value = "户籍地址")
    private String domicileAddress;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public void setRegPerson(String regPerson) {
        this.regPerson = regPerson;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGendar() {
        return gendar;
    }

    public void setGendar(String gendar) {
        this.gendar = gendar;
    }

    public String getLandlordtype() {
        return landlordtype;
    }

    public void setLandlordtype(String landlordtype) {
        this.landlordtype = landlordtype;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        //this.cards = cards;
        setCardList(IdUtils.seperate(cards, IdUtils.SEPERATOR_COMMA));
    }

    public List<String> getCardList() {
        return cardList;
    }

    public void setCardList(List<String> cardList) {
        this.cardList = cardList;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getPaperstype() {
        return paperstype;
    }

    public void setPaperstype(String paperstype) {
        this.paperstype = paperstype;
    }

    public String getPapersnum() {
        return papersnum;
    }

    public void setPapersnum(String papersnum) {
        this.papersnum = papersnum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPapersphoto() {
        return papersphoto;
    }

    public void setPapersphoto(String papersphoto) {
        this.papersphoto = papersphoto;
    }

    public String getAppOpen() {
        return appOpen;
    }

    public void setAppOpen(String appOpen) {
        this.appOpen = appOpen;
    }

    public String getRegPerson() {
        return regPerson;
    }

    public void setRegPersion(String regPerson) {
        this.regPerson = regPerson;
    }

    public String getLandlordtypename() {
        return landlordtypename;
    }

    public void setLandlordtypename(String landlordtypename) {
        this.landlordtypename = landlordtypename;
    }

    public String getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(String domicileAddress) {
        this.domicileAddress = domicileAddress;
    }
}
