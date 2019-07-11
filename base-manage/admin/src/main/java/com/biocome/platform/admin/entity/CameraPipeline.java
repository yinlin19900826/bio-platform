package com.biocome.platform.admin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 视频通道表
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-04 11:10:46
 */
@Table(name = "base_camera_pipeline")
public class CameraPipeline {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //编号
    @Column(name = "serial_code")
    private String serialCode;
	
	    //序号
    @Column(name = "serial_no")
    private Integer serialNo;
	
	    //名称
    @Column(name = "name")
    private String name;
	
	    //类型
    @Column(name = "type")
    private Integer type;
	
	    //镜头编码
    @Column(name = "shot_encode")
    private Integer shotEncode;
	
	    //镜头解码
    @Column(name = "shot_decode")
    private Integer shotDecode;
	
	    //镜头类型
    @Column(name = "shot_type")
    private Integer shotType;
	
	    //球机地址
    @Column(name = "ball_address")
    private String ballAddress;
	
	    //模数关联编号
    @Column(name = "related_serial")
    private String relatedSerial;
	
	    //云台可控（0 是 1 否）
    @Column(name = "platform_ctrl")
    private Integer platformCtrl;
	
	    //有镜头接入（0 是 1 否）
    @Column(name = "camera_connect")
    private Integer cameraConnect;

	//有镜头接入（0 是 1 否）
	@Column(name = "camera_id")
	private Integer cameraId;
	

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
	 * 设置：编号
	 */
	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}
	/**
	 * 获取：编号
	 */
	public String getSerialCode() {
		return serialCode;
	}
	/**
	 * 设置：序号
	 */
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * 获取：序号
	 */
	public Integer getSerialNo() {
		return serialNo;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：镜头编码
	 */
	public void setShotEncode(Integer shotEncode) {
		this.shotEncode = shotEncode;
	}
	/**
	 * 获取：镜头编码
	 */
	public Integer getShotEncode() {
		return shotEncode;
	}
	/**
	 * 设置：镜头解码
	 */
	public void setShotDecode(Integer shotDecode) {
		this.shotDecode = shotDecode;
	}
	/**
	 * 获取：镜头解码
	 */
	public Integer getShotDecode() {
		return shotDecode;
	}
	/**
	 * 设置：镜头类型
	 */
	public void setShotType(Integer shotType) {
		this.shotType = shotType;
	}
	/**
	 * 获取：镜头类型
	 */
	public Integer getShotType() {
		return shotType;
	}
	/**
	 * 设置：球机地址
	 */
	public void setBallAddress(String ballAddress) {
		this.ballAddress = ballAddress;
	}
	/**
	 * 获取：球机地址
	 */
	public String getBallAddress() {
		return ballAddress;
	}
	/**
	 * 设置：模数关联编号
	 */
	public void setRelatedSerial(String relatedSerial) {
		this.relatedSerial = relatedSerial;
	}
	/**
	 * 获取：模数关联编号
	 */
	public String getRelatedSerial() {
		return relatedSerial;
	}
	/**
	 * 设置：云台可控（0 是 1 否）
	 */
	public void setPlatformCtrl(Integer platformCtrl) {
		this.platformCtrl = platformCtrl;
	}
	/**
	 * 获取：云台可控（0 是 1 否）
	 */
	public Integer getPlatformCtrl() {
		return platformCtrl;
	}
	/**
	 * 设置：有镜头接入（0 是 1 否）
	 */
	public void setCameraConnect(Integer cameraConnect) {
		this.cameraConnect = cameraConnect;
	}
	/**
	 * 获取：有镜头接入（0 是 1 否）
	 */
	public Integer getCameraConnect() {
		return cameraConnect;
	}

	public Integer getCameraId() {
		return cameraId;
	}

	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}
}
