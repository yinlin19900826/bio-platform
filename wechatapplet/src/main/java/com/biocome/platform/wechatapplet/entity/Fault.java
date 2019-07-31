package com.biocome.platform.wechatapplet.entity;

import io.swagger.annotations.ApiModel;
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
    private Integer id;

    /**
     * 所在楼栋
     */
    @ApiParam(name = "building", value = "所在楼栋")
    @Column(name = "building")
    private String building;

    /**
     * 对应的门禁机
     */
    @ApiParam(name = "accessdevice", value = "对应的门禁机")
    @Column(name = "access_device")
    private String accessdevice;

    /**
     * 申报类型
     */
    @ApiParam(name = "declarationtype", value = "申报类型")
    @Column(name = "declaration_type")
    private String declarationtype;

    /**
     * 具体描述
     */
    @ApiParam(name = "detaildescription", value = "具体描述")
    @Column(name = "detail_description")
    private String detaildescription;

    /**
     * 创建时间
     */
    @ApiParam(name = "createtime", value = "创建时间")
    @Column(name = "createtime")
    private Date createtime;

    /**
     * 修改时间
     */
    @ApiParam(name = "updatetime", value = "修改时间")
    @Column(name = "updatetime")
    private Date updatetime;

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

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }

    /**
     * 创建人
     */
    @ApiParam(name = "createname", value = "创建人")
    @Column(name = "createname")
    private String createname;

    /**
     * 修改人
     */
    @ApiParam(name = "updatename", value = "修改人")
    @Column(name = "updatename")
    private String updatename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAccessdevice() {
        return accessdevice;
    }

    public void setAccessdevice(String accessdevice) {
        this.accessdevice = accessdevice;
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
}
