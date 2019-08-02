package com.biocome.platform.wechatapplet.biz;


import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.wechatapplet.entity.Fault;
import com.biocome.platform.wechatapplet.mapper.FaultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: AuditOperateBiz
 * @Author: yinlin
 * @Date: 2019/7/30 19:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuditOperateBiz extends BaseBiz<FaultMapper, Fault> {

    private Logger log = LoggerFactory.getLogger(AuditOperateBiz.class);

    @Autowired
    private FaultMapper faultMapper;

    /**
     *
     *
     * @param
     * @return
     * @Author yinlin
     * @Date 2019/7/30 19:33
     */
    public ObjectRestResponse insertFaultDeclare(Fault fault) throws Exception {

        if (ValidateUtils.isNotEmpty(fault)) {
            faultMapper.insertSelective(fault);
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("故障申报失败!");
        }
    }
}
