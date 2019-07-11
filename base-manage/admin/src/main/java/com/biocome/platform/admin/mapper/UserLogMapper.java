package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.UserLog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @ClassName: GateCardLogMapper
 * @Author: yinlin
 * @Date: 2019/5/9 15:36
 * @Description:
 */
public interface UserLogMapper extends Mapper<UserLog> {

    List<UserLog> selectByAttribute(@Param("optBusiness") String optBusiness, @Param("optName") String optName);

}