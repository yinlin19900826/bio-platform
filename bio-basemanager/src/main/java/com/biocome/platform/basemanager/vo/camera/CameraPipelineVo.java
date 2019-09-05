package com.biocome.platform.basemanager.vo.camera;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("视频通道")
public class CameraPipelineVo {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "通道名称")
    private String name;
    @ApiModelProperty(value = "通道序号")
    private String serailNo;
    /*@ApiModelProperty(value = "镜头类型")
    private Integer type;*/
    @ApiModelProperty(value = "类型名称")
    private String typeName;
    @ApiModelProperty(value = "镜头编码")
    private Integer encode;
    @ApiModelProperty(value = "镜头编码名称")
    private String encodeName;
    @ApiModelProperty(value = "镜头解码")
    private Integer decode;
    @ApiModelProperty(value = "镜头解码名称")
    private String decodeName;
    @ApiModelProperty(value = "镜头类型")
    private Integer shotType;
    @ApiModelProperty(value = "镜头类型名称")
    private String shotTypeName;
    @ApiModelProperty(value = "编号")
    private String serialCode;
    @ApiModelProperty(value = "模数关联编号")
    private String relatedSerial;
    @ApiModelProperty(value = "云台可控（0 是 1 否）")
    private Integer platformCtrl;
    @ApiModelProperty(value = "有镜头接入（0 是 1 否）")
    private Integer cameraConnect;
    @ApiModelProperty(value = "设备id")
    private Integer comeraId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerailNo() {
        return serailNo;
    }

    public void setSerailNo(String serailNo) {
        this.serailNo = serailNo;
    }

    /*public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }*/

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getEncode() {
        return encode;
    }

    public void setEncode(Integer encode) {
        this.encode = encode;
    }

    public String getEncodeName() {
        return encodeName;
    }

    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName;
    }

    public Integer getDecode() {
        return decode;
    }

    public void setDecode(Integer decode) {
        this.decode = decode;
    }

    public String getDecodeName() {
        return decodeName;
    }

    public void setDecodeName(String decodeName) {
        this.decodeName = decodeName;
    }

    public Integer getShotType() {
        return shotType;
    }

    public void setShotType(Integer shotType) {
        this.shotType = shotType;
    }

    public String getShotTypeName() {
        return shotTypeName;
    }

    public void setShotTypeName(String shotTypeName) {
        this.shotTypeName = shotTypeName;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getRelatedSerial() {
        return relatedSerial;
    }

    public void setRelatedSerial(String relatedSerial) {
        this.relatedSerial = relatedSerial;
    }

    public Integer getPlatformCtrl() {
        return platformCtrl;
    }

    public void setPlatformCtrl(Integer platformCtrl) {
        this.platformCtrl = platformCtrl;
    }

    public Integer getCameraConnect() {
        return cameraConnect;
    }

    public void setCameraConnect(Integer cameraConnect) {
        this.cameraConnect = cameraConnect;
    }

    public Integer getComeraId() {
        return comeraId;
    }

    public void setComeraId(Integer comeraId) {
        this.comeraId = comeraId;
    }
}
