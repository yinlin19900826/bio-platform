package com.biocome.video.mapper;

import com.biocome.video.entity.Camera;
import com.biocome.video.vo.CameraVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 视频设备表
 * 
 * @author yinlin
 * @date 2019-05-31 14:37:40
 */
public interface CameraMapper extends Mapper<Camera> {

    List<CameraVo> selectCameraList(@Param("institutioncode") String institutioncode, @Param("name") String name, @Param("ip") String ip);

    List<CameraVo> selectByTree(@Param("parentcode") String parentcode);

    List<CameraVo> getVideoDevice(@Param("cameraId") Integer cameraId);
}
