package com.biocome.platform.guard.vo.showmanage;

import com.biocome.platform.inter.basemanager.vo.device.DeviceInfoResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: SwitchVideoVo
 * @Author: yinlin
 * @Date: 2019/8/28 14:41
 * @Description:
 */
@ApiModel(value = "摄像机对应通道信息实体类")
public class SwitchVideoVo {
    /**
     * 摄像机所在通道编码
     */
    @ApiModelProperty(value = "摄像机所在通道编码")
    private String channelcode;

    /**
     * 摄像机ip
     */
    @ApiModelProperty(value = "摄像机ip")
    private String lanip;

    /**
     * 摄像机所在通道名字
     */
    @ApiModelProperty(value = "摄像机所在通道名字")
    private String channelname;

    /**
     * 摄像机在线状态
     */
    @ApiModelProperty(value = "摄像机在线状态")
    private Integer status;

    /**
     * 摄像机所处VCN的ip
     */
    @ApiModelProperty(value = "摄像机所处VCN的ip")
    private String vcnip;


    /**
     * 门禁摄像机录播前后多少秒
     */
    @ApiModelProperty(value = "门禁摄像机录播前后多少秒")
    private Integer seconds;

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVcnip() {
        return vcnip;
    }

    public void setVcnip(String vcnip) {
        this.vcnip = vcnip;
    }

    public String getLanip() {
        return lanip;
    }

    public void setLanip(String lanip) {
        this.lanip = lanip;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

}
