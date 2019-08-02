package com.biocome.platform.wechatapplet.biz;


import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.wechatapplet.entity.Fault;
import com.biocome.platform.wechatapplet.mapper.AuditOperateMapper;
import com.biocome.platform.wechatapplet.mapper.FaultMapper;
import com.biocome.platform.wechatapplet.vo.common.CardManageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: AuditOperateBiz
 * @Author: yinlin
 * @Date: 2019/8/2 9:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuditOperateBiz extends BaseBiz<AuditOperateMapper, Lessee> {

    private Logger log = LoggerFactory.getLogger(AuditOperateBiz.class);

    @Autowired
    private AuditOperateMapper auditOperateMapper;

    /**
     * 获取待审核列表和已审核列表
     *
     * @return
     * @Author yinlin
     * @Date 2019/8/2 14:08
     */
    public TableResultResponse<Lessee> selectByAttribute(int pageSize, int pageNum,int isaudit) {
        TableResultResponse<Lessee> res;
        try {
            Page<Lessee> result = PageHelper.startPage(pageNum, pageSize);
            auditOperateMapper.selectByAudit(isaudit);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("获取待审核列表和已审核列表失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "获取待审核列表和已审核列表失败!");
        }
        return res;
    }
}
