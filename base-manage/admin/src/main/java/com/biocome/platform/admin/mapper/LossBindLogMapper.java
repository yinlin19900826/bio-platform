package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.LossBindLog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @ClassName: GateCardLogMapper
 * @Author: yinlin
 * @Date: 2019/5/9 15:36
 * @Description:
 */
public interface LossBindLogMapper extends Mapper<LossBindLog> {

    List<LossBindLog> selectByAttribute(@Param("physicalcardNo") String physicalcardNo,
                                        @Param("cancelcardNo") String cancelcardNo,
                                        @Param("optName") String optName);

}