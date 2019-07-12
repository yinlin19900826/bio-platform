package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.StreamMedia;
import com.biocome.platform.basemanager.vo.camera.StreamMediaVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 流媒体服务
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-03 17:39:34
 */
public interface StreamMediaMapper extends Mapper<StreamMedia> {

    List<StreamMediaVo> selectStraemMediaList(@Param("name") String name, @Param("ip") String ip, @Param("cameraId") Integer cameraId);

    void bindCamera(@Param("cameraId") int cameraId, @Param("ids") List<Integer> ids);
}
