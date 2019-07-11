package com.biocome.platform.guard.vo.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DoorDeviceVo
 * @Author: shenlele
 * @Date: 2019/5/14 17:17
 * @Description:
 */
@ApiModel(value = "远程同步门禁机实体类")
public class DoorDeviceVo {

    @ApiModelProperty(value = "设备编号")
    private String sn;
    @ApiModelProperty(value = "设备名称")
    private String facilityname;
    @ApiModelProperty(value = "设备类型(单元机/公共机)")
    private String facilitytype;
    @ApiModelProperty(value = "PAD登陆密码")
    private String makeno;
    @ApiModelProperty(value = "密钥")
    private String pwdkey;
    @ApiModelProperty(value = "设备ip地址")
    private String deviceip;
    @ApiModelProperty(value = "所属小区编码")
    private String estatecode;
    @ApiModelProperty(value = "所属楼幢编码")
    private String buildcode;
    @ApiModelProperty(value = "所属单元编码")
    private String unitcode;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public String getFacilitytype() {
        return facilitytype;
    }

    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    public String getMakeno() {
        return makeno;
    }

    public void setMakeno(String makeno) {
        this.makeno = makeno;
    }

    public String getPwdkey() {
        return pwdkey;
    }

    public void setPwdkey(String pwdkey) {
        this.pwdkey = pwdkey;
    }

    public String getDeviceip() {
        return deviceip;
    }

    public void setDeviceip(String deviceip) {
        this.deviceip = deviceip;
    }

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
}
