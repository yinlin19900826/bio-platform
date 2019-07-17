package com.biocome.platform.inter.basemanager.vo.inoutRecord;


import com.biocome.platform.inter.basemanager.entity.InoutRecord;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/20 15:38
 */
public class InoutRecordBulkReq {
    private String accesskey;
    private List<InoutRecord> list;

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public List<InoutRecord> getList() {
        return list;
    }

    public void setList(List<InoutRecord> list) {
        this.list = list;
    }
}
