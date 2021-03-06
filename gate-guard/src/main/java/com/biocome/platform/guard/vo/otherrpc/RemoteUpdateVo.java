package com.biocome.platform.guard.vo.otherrpc;

import com.biocome.platform.inter.basemanager.vo.common.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: RemoteUpdateVo
 * @Author: shenlele
 * @Date: 2019/5/20 16:34
 * @Description:
 */
@ApiModel(value = "请求远程升级实体类")
public class RemoteUpdateVo extends CommonVo {

    @ApiModelProperty(value = "设备编码（唯一性）")
    private String sn;
    @ApiModelProperty(value = "filepath升级文件地址（http地址）")
    private String filepath;
    @ApiModelProperty(value = "文件MD5值")
    private String filemd5;

    public RemoteUpdateVo() {
    }

    public RemoteUpdateVo(String token, String sn, String filepath, String filemd5) {
        this.setToken(token);
        this.sn = sn;
        this.filepath = filepath;
        this.filemd5 = filemd5;
    }
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilemd5() {
        return filemd5;
    }

    public void setFilemd5(String filemd5) {
        this.filemd5 = filemd5;
    }
}
