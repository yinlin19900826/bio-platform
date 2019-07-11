package com.biocome.platform.admin.vo.inoutRecord;

import com.biocome.platform.admin.entity.InoutRecord;

/**
 * @author hxy
 * @date 2019/5/20 11:22
 */
public class InoutRecordReq {
    private String accesskey;
    private InoutRecord info;

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public InoutRecord getInfo() {
        return info;
    }

    public void setInfo(InoutRecord info) {
        this.info = info;
    }
}
