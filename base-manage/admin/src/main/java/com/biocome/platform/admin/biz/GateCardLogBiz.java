package com.biocome.platform.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.entity.GateCardLog;
import com.biocome.platform.admin.mapper.GateCardLogMapper;
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
public class GateCardLogBiz extends BaseBiz<GateCardLogMapper, GateCardLog> {

    @Override
    public void insert(GateCardLog entity) {
        mapper.insert(entity);
    }

    @Autowired
    private GateCardLogMapper gateCardLogMapper;


    public TableResultResponse<GateCardLog> selectByAttribute(int pageSize, int pageNum, String physicalcardNo,String optName) {
        TableResultResponse<GateCardLog> res = null;
        try {
            Page<GateCardLog> result = PageHelper.startPage(pageNum, pageSize);
            if(ValidateUtils.isEmpty(physicalcardNo) && ValidateUtils.isEmpty(optName)){
                gateCardLogMapper.selectAll();
            }else{
                gateCardLogMapper.selectByAttribute(physicalcardNo, optName);
            }
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询门禁卡操作日志失败，错误信息为：" + e.getMessage());
        }
        return res;
    }

    @Override
    @ApiOperation("门禁卡操作日志插入数据")
    public void insertSelective(GateCardLog entity) {
        mapper.insertSelective(entity);
    }
}
