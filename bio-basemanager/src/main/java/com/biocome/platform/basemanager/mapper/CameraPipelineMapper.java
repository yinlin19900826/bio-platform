package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.CameraPipeline;
import com.biocome.platform.basemanager.vo.camera.CameraPipelineVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 视频通道表
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-03 17:39:34
 */
public interface CameraPipelineMapper extends Mapper<CameraPipeline> {

    List<CameraPipelineVo> selectCameraPiplineList(@Param("cameraId") int cameraId, @Param("serialNo") String serialNo, @Param("name") String name);
}
