package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.vo.UserVo;
import com.biocome.platform.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public List<User> selectMemberByGroupId(@Param("groupId") int groupId);
    public List<User> selectLeaderByGroupId(@Param("groupId") int groupId);

    List<UserVo> selectUserList();
}