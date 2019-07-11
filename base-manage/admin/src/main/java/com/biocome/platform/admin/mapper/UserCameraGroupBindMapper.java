package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.UserCameraGroupBind;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户与镜头组关联
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 20:02:19
 */
public interface UserCameraGroupBindMapper extends Mapper<UserCameraGroupBind> {

    List<String> selectBindIds(@Param("username") String username);
}