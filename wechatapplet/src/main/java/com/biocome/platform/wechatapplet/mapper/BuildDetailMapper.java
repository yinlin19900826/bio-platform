package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.wechatapplet.vo.build.BuildDetailResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 10:44
 */
public interface BuildDetailMapper {
    List<BuildDetailResp> getBuildByUsercode(@Param("usercode") String usercode);

}
