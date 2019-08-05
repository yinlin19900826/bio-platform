package com.biocome.platform.wechatapplet.biz;


import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.wechatapplet.entity.Fault;
import com.biocome.platform.wechatapplet.mapper.AuditOperateMapper;
import com.biocome.platform.wechatapplet.mapper.FaultMapper;
import com.biocome.platform.wechatapplet.vo.common.AuditOperateVo;
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
public class AuditOperateBiz extends BaseBiz<AuditOperateMapper, AuditOperateVo> {

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
    public TableResultResponse<AuditOperateVo> selectByAttribute(int pageSize, int pageNum,int isaudit) {
        TableResultResponse<AuditOperateVo> res;
        try {
            Page<AuditOperateVo> result = PageHelper.startPage(pageNum, pageSize);
            auditOperateMapper.selectByAudit(isaudit);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("获取待审核列表和已审核列表失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "获取待审核列表和已审核列表失败!");
        }
        return res;
    }

    /**
     *  根据用户名更改审核状态
     *
     * @param
     * @return java.lang.String
     * @throws Exception 异常信息
     * @Author yinlin
     * @Date 2019/8/5 10:58
     */
    public String updateIsAudit(String username ,int isaudit) throws Exception {
        //更新审核状态
        auditOperateMapper.updateIsAudit(username,isaudit);

        return AdminCommonConstant.BOOLEAN_NUMBER_TRUE;
    }
}
