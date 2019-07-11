package com.biocome.platform.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.entity.LossBindLog;
import com.biocome.platform.admin.mapper.LossBindLogMapper;
import com.biocome.platform.auth.common.constatns.CommonConstants;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @ClassName: GateCardLogBiz
 * @Author: yinlin
 * @Date: 2019/5/9 15:36
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LossBindLogBiz extends BaseBiz<LossBindLogMapper, LossBindLog> {

    @Override
    public void insert(LossBindLog entity) {
        mapper.insert(entity);
    }

    @Autowired
    private LossBindLogMapper lossBindLogMapper;


    public TableResultResponse<LossBindLog> selectByAttribute(int pageSize, int pageNum, String physicalcardNo,String cancelcardNo,String optName) {
        TableResultResponse<LossBindLog> res = null;
        try {
            Page<LossBindLog> result = PageHelper.startPage(pageNum, pageSize);
            if(ValidateUtils.isEmpty(physicalcardNo) && ValidateUtils.isEmpty(cancelcardNo)&& ValidateUtils.isEmpty(optName)){
                lossBindLogMapper.selectAll();
            }else{
                lossBindLogMapper.selectByAttribute(physicalcardNo,cancelcardNo, optName);
            }
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询门禁卡挂失/绑定日志失败，错误信息为：" + e.getMessage());
        }
        return res;
    }

    @Override
    @ApiOperation("")
    public void insertSelective(LossBindLog entity) {
        mapper.insertSelective(entity);
    }
}
