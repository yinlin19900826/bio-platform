package com.biocome.platform.guard.vo.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: hxy
 * @Date:2019/5/7 10:48
 * @Description:设备实体类
 */
@ApiModel(value = "设备返回实体")
public class DeviceInfoResp {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;
    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private String sn;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    /**
     * 小区编号
     */
    @ApiModelProperty(value = "小区编号")
    private String estatecode;
    /**
     * 楼栋编号
     */
    @ApiModelProperty(value = "楼栋编号")
    private String buildcode;
    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    private String ip;
    /**
     * 设备类型 1、单元机。2、公共机
     */
    @ApiModelProperty(value = "设备类型 1、单元机。2、公共机")
    private String deviceType;
    /**
     * 品牌编号 1、奔凯
     */
    @ApiModelProperty(value = "品牌编号 1、奔凯")
    private String brandcode;
    /**
     * 品牌 1、奔凯
     */
    @ApiModelProperty(value = "品牌 1、奔凯")
    private String brand;
    /**
     * 设备密钥
     */
    @ApiModelProperty(value = "设备密钥")
    private String secretkey;
    /**
     * 设备mac
     */
    @ApiModelProperty(value = "设备mac")
    private String mac;
    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号")
    private String seriesNumber;
    /**
     * 关联摄像头1
     */
    @ApiModelProperty(value = "关联摄像头1")
    private String camera1;
    /**
     * 关联摄像头2
     */
    @ApiModelProperty(value = "关联摄像头2")
    private String camera2;
    /**
     * 关联摄像头3
     */
    @ApiModelProperty(value = "关联摄像头3")
    private String camera3;
    /**
     * 楼栋名
     */
    @ApiModelProperty(value = "楼栋名")
    private String buildName;
    /**
     * 小区名
     */
    @ApiModelProperty(value = "小区名")
    private String estateName;
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getCamera1() {
        return camera1;
    }

    public void setCamera1(String camera1) {
        this.camera1 = camera1;
    }

    public String getCamera2() {
        return camera2;
    }

    public void setCamera2(String camera2) {
        this.camera2 = camera2;
    }

    public String getCamera3() {
        return camera3;
    }

    public void setCamera3(String camera3) {
        this.camera3 = camera3;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }
}
