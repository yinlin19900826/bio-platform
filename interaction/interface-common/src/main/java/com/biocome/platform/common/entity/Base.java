package com.biocome.platform.common.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @ClassName: Base
 * @Author: shenlele
 * @Date: 2019/5/5 09:12
 * @Description:
 */
public class Base {

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据有效(0有效1无效)
     */
    private Integer status;

    /**
     * 是否删除（0未删除1已删除）
     */
    private Integer del;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人账号
     */
    private String createcode;

    /**
     * 创建人昵称
     */
    private String createname;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 更新人账号
     */
    private String updatecode;

    /**
     * 更新人昵称
     */
    private String updatename;

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取数据有效(0有效1无效)
     *
     * @return status - 数据有效(0有效1无效)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置数据有效(0有效1无效)
     *
     * @param status 数据有效(0有效1无效)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否删除（0未删除1已删除）
     *
     * @return del - 是否删除（0未删除1已删除）
     */
    public Integer getDel() {
        return del;
    }

    /**
     * 设置是否删除（0未删除1已删除）
     *
     * @param del 是否删除（0未删除1已删除）
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取创建人账号
     *
     * @return createcode - 创建人账号
     */
    public String getCreatecode() {
        return createcode;
    }

    /**
     * 设置创建人账号
     *
     * @param createcode 创建人账号
     */
    public void setCreatecode(String createcode) {
        this.createcode = createcode;
    }

    /**
     * 获取创建人昵称
     *
     * @return createname - 创建人昵称
     */
    public String getCreatename() {
        return createname;
    }

    /**
     * 设置创建人昵称
     *
     * @param createname 创建人昵称
     */
    public void setCreatename(String createname) {
        this.createname = createname;
    }

    /**
     * 获取更新时间
     *
     * @return updatetime - 更新时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取更新人账号
     *
     * @return updatecode - 更新人账号
     */
    public String getUpdatecode() {
        return updatecode;
    }

    /**
     * 设置更新人账号
     *
     * @param updatecode 更新人账号
     */
    public void setUpdatecode(String updatecode) {
        this.updatecode = updatecode;
    }

    /**
     * 获取更新人昵称
     *
     * @return updatename - 更新人昵称
     */
    public String getUpdatename() {
        return updatename;
    }

    /**
     * 设置更新人昵称
     *
     * @param updatename 更新人昵称
     */
    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }
}
