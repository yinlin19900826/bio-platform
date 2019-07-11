package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.DictDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-06 09:39:45
 */
public interface DictDetailMapper extends Mapper<DictDetail> {

    List<DictDetail> selectListByName(@Param("name") String name);
}
