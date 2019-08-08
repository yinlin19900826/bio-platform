package com.biocome.platform.wechatapplet.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 故障申报实体类
 *
 * @ClassName: Fault
 * @Author: yinlin
 * @Date: 2019/7/30 10:48
 * @Description:
 */
@ApiModel(value = "故障申报实体类")
@Table(name = "fault_declaration")
public class Fault {
    @ApiParam(name = "id", value = "id, 新增的时候id为空")
    @Id
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 所在楼栋
     */
    @ApiParam(name = "buildname", value = "所在楼栋")
    @Column(name = "buildname")
    @ApiModelProperty(value = "所在楼栋")
    private String buildname;

    /**
     * 对应的门禁机
     */
    @ApiParam(name = "devicename", value = "对应的门禁机")
    @Column(name = "devicename")
    @ApiModelProperty(value = "对应的门禁机")
    private String devicename;

    /**
     * 所在楼栋编号
     */
    @ApiParam(name = "buildcode", value = "所在楼栋编号")
    @Column(name = "buildcode")
    @ApiModelProperty(value = "所在楼栋编号")
    private String buildcode;

    /**
     * 对应的门禁机
     */
    @ApiParam(name = "sn", value = "设备编号")
    @Column(name = "sn")
    @ApiModelProperty(value = "设备编号")
    private String sn;

    /**
     * 申报类型
     */
    @ApiParam(name = "declarationtype", value = "申报类型")
    @Column(name = "declaration_type")
    @ApiModelProperty(value = "申报类型(1:性能故障。2:改进建议）")
    private String declarationtype;

    /**
     * 具体描述
     */
    @ApiParam(name = "detaildescription", value = "具体描述")
    @Column(name = "detail_description")
    @ApiModelProperty(value = "具体描述")
    private String detaildescription;

    /**
     * 创建时间
     */
    @ApiParam(name = "createtime", value = "创建时间")
    @Column(name = "createtime")
    @JsonFormat( pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    /**
     * 修改时间
     */
    @ApiParam(name = "updatetime", value = "修改时间")
    @Column(name = "updatetime")
    @JsonFormat( pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updatetime;

    /**
     * 创建人
     */
    @ApiParam(name = "createname", value = "创建人")
    @Column(name = "createname")
    @ApiModelProperty(value = "创建人")
    private String createname;

    /**
     * 修改人
     */
    @ApiParam(name = "updatename", value = "修改人")
    @Column(name = "updatename")
    @ApiModelProperty(value = "修改人")
    private String updatename;

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDeclarationtype() {
        return declarationtype;
    }

    public void setDeclarationtype(String declarationtype) {
        this.declarationtype = declarationtype;
    }

    public String getDetaildescription() {
        return detaildescription;
    }

    public void setDetaildescription(String detaildescription) {
        this.detaildescription = detaildescription;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }
}
