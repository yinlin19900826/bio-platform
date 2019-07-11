package com.biocome.video.mapper;

import com.biocome.video.vo.TreeVideoVO;
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
