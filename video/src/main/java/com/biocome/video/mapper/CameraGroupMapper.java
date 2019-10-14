package com.biocome.video.mapper;

import com.biocome.platform.common.vo.UINodeVo;
import com.biocome.video.entity.CameraGroup;
import com.biocome.video.vo.AddGroupVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 * 
 * @author yinlin
 * @
 * @date 2019-09-17 14:57:40
 */
public interface CameraGroupMapper extends Mapper<CameraGroup> {

    List<Integer> getAllChildIds(@Param("id") Integer id);

    int getCameraAmount(@Param("id") Integer id);

    int getOnLineCameraAmount(@Param("id") Integer id);


}
