package com.biocome.platform.record.mapper;

import com.biocome.platform.inter.basemanager.entity.InoutRecord;
import com.biocome.platform.inter.basemanager.entity.VisitorRecord;
import com.biocome.platform.inter.basemanager.vo.inoutRecord.InoutRecordForListResp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-20 10:40:09
 */
public interface InoutRecordMapper extends Mapper<InoutRecord> {
    /**
     * 查询出入记录
     *
     * @param id        id
     * @param opentype  开门方式
     * @param cardno    卡号/动态密码
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public List<InoutRecordForListResp> selectInoutRecordForList(@Param("id") Integer id, @Param("opentype") String opentype, @Param("cardno") String cardno,
                                                                 @Param("starttime") Date starttime, @Param("endtime") Date endtime, @Param("buildcode") String buildcode);

    public void addInoutRecord(InoutRecord inoutRecord);

    public int updateVisitRecord(VisitorRecord record);
}
