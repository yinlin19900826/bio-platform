package com.biocome.platform.inter.basemanager.vo.inoutRecord;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;

/**
 * @author hxy
 * @date 2019/5/20 15:09
 */
public class InoutRecordBulkResp extends BaseRpcResponse{
    /**
     * 进出记录唯一标识
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
