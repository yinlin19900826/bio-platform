package com.biocome.platform.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-20 10:40:09
 */
@ApiModel(value = "出入记录模型")
@Table(name = "base_inout_record")
public class InoutRecord {
    private static final long serialVersionUID = 1L;

    //主键ID
    @ApiModelProperty(value = "id")
    @Id
    private Integer id;

    //用户编号
    @ApiModelProperty(value="用户编号")
    @Column(name = "usercode")
    private String usercode;

    //用户附加信息
    @ApiModelProperty(value="用户附加信息")
    @Column(name = "userdesc")
    private String userdesc;

    //开门时间
    @ApiModelProperty(value="开门时间")
    @Column(name = "createtime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    //设备编号
    @ApiModelProperty(value="设备编号")
    @Column(name = "sn")
    private String sn;

    //开门方式(1刷卡2动态密码3远程 4视频开门5密码开门6电话开门7按钮开门8非法卡9过期卡)
    @ApiModelProperty(value="开门方式(1刷卡2动态密码3远程 4视频开门5密码开门6电话开门7按钮开门8非法卡9过期卡)")
    @Column(name = "opentype")
    private String opentype;

    //抓拍相片
    @ApiModelProperty(value="抓拍相片")
    @Column(name = "picpath")
    private String picpath;

    //抓拍相片2(备用)
    @ApiModelProperty(value="抓拍相片2(备用)")
    @Column(name = "picpath2")
    private String picpath2;

    //抓拍相片3(备用)
    @ApiModelProperty(value="抓拍相片3(备用)")
    @Column(name = "picpath3")
    private String picpath3;

    //抓拍视频
    @ApiModelProperty(value="抓拍视频")
    @Column(name = "videopath")
    private String videopath;

    //开门卡号(动态密码/卡号)
    @ApiModelProperty(value="开门卡号(动态密码/卡号)")
    @Column(name = "cardno")
    private String cardno;

    //授权码（XD001 雄帝 BK001 奔凯 LZ001 丽泽 HN001 海能）
    @ApiModelProperty(value="授权码（XD001 雄帝 BK001 奔凯 LZ001 丽泽 HN001 海能）")
    @Column(name = "accesskey")
    private String accesskey;


    /**
     * 设置：主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：用户编号
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    /**
     * 获取：用户编号
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * 设置：用户附加信息
     */
    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    /**
     * 获取：用户附加信息
     */
    public String getUserdesc() {
        return userdesc;
    }

    /**
     * 设置：开门时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取：开门时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置：设备编号
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 获取：设备编号
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置：开门方式(1刷卡2动态密码3远程 4视频开门5密码开门6电话开门7按钮开门8非法卡9过期卡)
     */
    public void setOpentype(String opentype) {
        this.opentype = opentype;
    }

    /**
     * 获取：开门方式(1刷卡2动态密码3远程 4视频开门5密码开门6电话开门7按钮开门8非法卡9过期卡)
     */
    public String getOpentype() {
        return opentype;
    }

    /**
     * 设置：抓拍相片
     */
    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    /**
     * 获取：抓拍相片
     */
    public String getPicpath() {
        return picpath;
    }

    /**
     * 设置：抓拍相片2(备用)
     */
    public void setPicpath2(String picpath2) {
        this.picpath2 = picpath2;
    }

    /**
     * 获取：抓拍相片2(备用)
     */
    public String getPicpath2() {
        return picpath2;
    }

    /**
     * 设置：抓拍相片3(备用)
     */
    public void setPicpath3(String picpath3) {
        this.picpath3 = picpath3;
    }

    /**
     * 获取：抓拍相片3(备用)
     */
    public String getPicpath3() {
        return picpath3;
    }

    /**
     * 设置：抓拍视频
     */
    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    /**
     * 获取：抓拍视频
     */
    public String getVideopath() {
        return videopath;
    }

    /**
     * 设置：开门卡号(动态密码/卡号)
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * 获取：开门卡号(动态密码/卡号)
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * 设置：授权码（XD001 雄帝 BK001 奔凯 LZ001 丽泽 HN001 海能）
     */
    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    /**
     * 获取：授权码（XD001 雄帝 BK001 奔凯 LZ001 丽泽 HN001 海能）
     */
    public String getAccesskey() {
        return accesskey;
    }
}
