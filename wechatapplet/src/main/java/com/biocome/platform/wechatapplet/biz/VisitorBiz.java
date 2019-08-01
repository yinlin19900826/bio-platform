package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.inter.basemanager.entity.VisitorRecord;
import com.biocome.platform.wechatapplet.mapper.DeviceDetailMapper;
import com.biocome.platform.wechatapplet.mapper.VisitorMapper;
import com.biocome.platform.wechatapplet.rpc.service.OpenDoorPasswordRpc;
import com.biocome.platform.wechatapplet.vo.visitor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 17:22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VisitorBiz {

    @Autowired
    private DeviceDetailMapper deviceDetailMapper;
    @Autowired
    private OpenDoorPasswordRpc openDoorPasswordRpc;
    @Autowired
    private VisitorMapper visitorMapper;

    public int insertRecord(OpendoorpasswordResp opendoorpassword, VisitorPasswordReq req) throws Exception {
        Date currentDate = DateUtils.getCurrentTime();
        VisitorRecord record = new VisitorRecord();
        record.setSn(req.getSn());
        record.setDeviceName(req.getDeviceName());
        record.setPassword(opendoorpassword.getPassword());
        record.setUsercode(req.getUsercode());
        record.setVisitorPhone(req.getPhone());
        record.setCreatetime(currentDate);
        record.setOverduetime(DateUtils.getAddHourTime(currentDate, 2));
        record.setStatus("1");
        return visitorMapper.insert(record);
    }

    public OpendoorpasswordResp opendoorpassword(VisitorPasswordReq req) throws Exception {
        VisitorPasswordVo vo = new VisitorPasswordVo();
        vo.setSn(req.getSn());
        vo.setUsercode(req.getUsercode());
        vo.setUsertype(req.getUsertype());
        vo.setUserdesc(req.getRemark());
        OpendoorpasswordResp opendoorpassword = openDoorPasswordRpc.opendoorpassword(vo);
        return opendoorpassword;
    }

    public List<GetRecordResp> getRecord(GetRecordReq req) {
        VisitorRecord record = new VisitorRecord();
        record.setCreatetime(DateUtils.getCurrentTime());
        record.setUsercode(req.getUsercode());
        if ("1".equals(req.getStatus())){
            //获取待使用记录
            record.setStatus("1");
            return visitorMapper.getActiveRecord(record);
        }else {
            return visitorMapper.getInactiveRecord(record);
        }
    }
}
