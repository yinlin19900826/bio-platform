package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.vo.tree.TreeVideoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @Description:
 */
public interface TreeVideoMapper {

    List<TreeVideoVO> selectTreeVideo(@Param("type") Integer type, @Param("parentcode") String parentcode);

    List<TreeVideoVO> selectAnotherTreeVideo(@Param("type") Integer type, @Param("code") String code);

}
