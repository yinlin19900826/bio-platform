package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.UserLog;
import com.biocome.platform.basemanager.mapper.UserLogMapper;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.util.ValidateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class UserLogBiz extends BaseBiz<UserLogMapper, UserLog> {

    @Override
    public void insert(UserLog entity) {
        mapper.insert(entity);
    }

    @Autowired
    private UserLogMapper userLogMapper;


    public TableResultResponse<UserLog> selectByAttribute(int pageSize, int pageNum, String physicalcardNo,String optName) {
        TableResultResponse<UserLog> res = null;
        try {
            Page<UserLog> result = PageHelper.startPage(pageNum, pageSize);
            if(ValidateUtils.isEmpty(physicalcardNo) && ValidateUtils.isEmpty(optName)){
                userLogMapper.selectAll();
            }else{
                userLogMapper.selectByAttribute(physicalcardNo, optName);
            }
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询用户日志失败，错误信息为：" + e.getMessage());
        }
        return res;
    }

    @Override
    @ApiOperation("门禁卡操作日志插入数据")
    public void insertSelective(UserLog entity) {
        mapper.insertSelective(entity);
    }
}
