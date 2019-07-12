package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.ServiceConfig;
import com.biocome.platform.basemanager.vo.camera.ServiceConfigVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 服务配置表（视频设备管理）
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-03 17:39:34
 */
public interface ServiceConfigMapper extends Mapper<ServiceConfig> {

    List<ServiceConfigVo> selectServiceConfigList(@Param("name") String name, @Param("ip") String ip);
}
