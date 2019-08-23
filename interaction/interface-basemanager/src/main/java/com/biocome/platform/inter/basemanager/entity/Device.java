package com.biocome.platform.inter.basemanager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther: hxy
 * @Date:2019/5/7 10:48
 * @Description:设备实体类
 */
@ApiModel(value = "设备模型")
@Table(name = "base_device")
public class Device {
    /**
     * 主键id
     */
    @ApiModelProperty(value="id")
    @Id
    private Integer id;
    /**
     * 设备编号
     */
    @ApiModelProperty(value="设备编号")
    @Column(name = "sn")
    private String sn;
    /**
     * 设备名称
     */
    @ApiModelProperty(value="设备名称")
    @Column(name = "device_name")
    private String deviceName;
    /**
     * 小区编号
     */
    @ApiModelProperty(value="小区编号")
    @Column(name = "estatecode")
    private String estatecode;
    /**
     * 楼栋编号
     */
    @ApiModelProperty(value="楼栋编号")
    @Column(name = "buildcode")
    private String buildcode;
    /**
     * 单元编号
     */
    @ApiModelProperty(value="单元编号")
    @Column(name = "unitcode")
    private String unitcode;
    /**
     * ip地址
     */
    @ApiModelProperty(value="ip地址")
    @Column(name = "ip")
    private String ip;
    /**
     * 设备类型 1、单元机。2、公共机
     */
    @ApiModelProperty(value="设备类型")
    @Column(name = "device_type")
    private String deviceType;
    /**
     * 品牌 1、奔凯
     */
    @ApiModelProperty(value="品牌")
    @Column(name = "brand")
    private String brand;
    /**
     * 设备密钥
     */
    @ApiModelProperty(value="设备密钥")
    @Column(name = "secretkey")
    private String secretkey;
    /**
     * 设备mac
     */
    @ApiModelProperty(value="设备mac")
    @Column(name = "mac")
    private String mac;
    /**
     * 设备序列号
     */
    @ApiModelProperty(value="设备序列号")
    @Column(name = "series_number")
    private String seriesNumber;
    /**
     * 关联摄像头1
     */
    @ApiModelProperty(value="关联摄像头1")
    @Column(name = "c_number1")
    private String camera1;
    /**
     * 关联摄像头2
     */
    @ApiModelProperty(value="关联摄像头2")
    @Column(name = "c_number2")
    private String camera2;
    /**
     * 关联摄像头3
     */
    @ApiModelProperty(value="关联摄像头3")
    @Column(name = "c_number3")
    private String camera3;

    @ApiModelProperty(value="蓝牙摘要")
    @Column(name = "devdigest")
    private String devdigest;

    @ApiModelProperty(value="蓝牙mac")
    @Column(name = "bluetoothmac")
    private String bluetoothmac;

    @ApiModelProperty(value="蓝牙摘要更新时间")
    @Column(name = "bluetooth_updatetime")
    private String bluetoothUpdatetime;

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

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getDevdigest() {
        return devdigest;
    }

    public void setDevdigest(String devdigest) {
        this.devdigest = devdigest;
    }

    public String getBluetoothmac() {
        return bluetoothmac;
    }

    public void setBluetoothmac(String bluetoothmac) {
        this.bluetoothmac = bluetoothmac;
    }

    public String getBluetoothUpdatetime() {
        return bluetoothUpdatetime;
    }

    public void setBluetoothUpdatetime(String bluetoothUpdatetime) {
        this.bluetoothUpdatetime = bluetoothUpdatetime;
    }
}
