package com.biocome.platform.wechatapplet.mapper;


import com.biocome.platform.inter.basemanager.entity.VisitorRecord;
import com.biocome.platform.wechatapplet.vo.visitor.GetRecordResp;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/31 09:25
 */
public interface VisitorMapper {

    int insert(VisitorRecord record);

    List<GetRecordResp> getActiveRecord(VisitorRecord record);

    List<GetRecordResp> getInactiveRecord(VisitorRecord record);
}
