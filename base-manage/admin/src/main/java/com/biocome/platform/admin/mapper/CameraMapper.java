package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.Camera;
import com.biocome.platform.admin.vo.camera.CameraVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 视频设备表
 * 
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-31 14:37:40
 */
public interface CameraMapper extends Mapper<Camera> {

    List<CameraVo> selectCameraList(@Param("institutioncode") String institutioncode, @Param("name") String name, @Param("ip") String ip);

    List<CameraVo> selectByTree(@Param("parentcode") String parentcode);
}
