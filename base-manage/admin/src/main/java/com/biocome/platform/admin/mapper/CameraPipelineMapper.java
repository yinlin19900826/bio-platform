package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.CameraPipeline;
import com.biocome.platform.admin.vo.camera.CameraPipelineVo;
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

    List<CameraPipelineVo> selectCameraPiplineList(@Param("cameraId") String cameraId, @Param("serialNo") String serialNo, @Param("name") String name);
}
