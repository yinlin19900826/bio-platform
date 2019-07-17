package com.biocome.platform.guard.vo.upload;


import com.biocome.platform.common.msg.auth.BaseRpcResponse;

/**
 * @author hxy
 * @date 2019/5/16 16:05
 */
public class UploadResp extends BaseRpcResponse {
    /**
     * 组名
     */
    private String groupname;
    /**
     * 文件坐标
     */
    private String path;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
