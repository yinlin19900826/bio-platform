package com.biocome.platform.record.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.inter.basemanager.entity.InoutRecord;
import com.biocome.platform.inter.basemanager.vo.inoutRecord.InoutRecordForListResp;
import com.biocome.platform.record.mapper.InoutRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-20 10:40:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InoutRecordBiz extends BaseBiz<InoutRecordMapper, InoutRecord> {
    public List<InoutRecordForListResp> selectInoutRecordForList(Integer id,
                                                                 String opentype,
                                                                 String cardno,
                                                                 Date starttime,
                                                                 Date endtime, String buildcode) {
        return mapper.selectInoutRecordForList(id, opentype, cardno, starttime, endtime, buildcode);
    }
    public void addInoutRecord(InoutRecord record) throws Exception{
        mapper.addInoutRecord(record);
    }
}