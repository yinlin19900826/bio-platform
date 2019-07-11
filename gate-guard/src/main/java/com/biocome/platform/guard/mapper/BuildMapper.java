package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.Build;
import com.biocome.platform.guard.vo.tree.TreeDistrictVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: BuildMapper
 * @Author: shenlele
 * @Date: 2019/5/8 11:57
 * @Description:
 */
public interface BuildMapper extends Mapper<Build> {

    /**
     * 根据所属小区代码查询所有小区，构建树
     *
     * @param estatecode 小区编号
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/20 11:43
     */
    List<TreeDistrictVO> selectByTree(@Param("estatecode") String estatecode);

}