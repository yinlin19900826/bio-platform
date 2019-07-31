package com.biocome.platform.wechatapplet.vo.build;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;

/**
 * @author hxy
 * @date 2019/7/30 10:32
 */
public class BuildDetailResp{

    private String buildName;
    private String buildCode;

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }
}
