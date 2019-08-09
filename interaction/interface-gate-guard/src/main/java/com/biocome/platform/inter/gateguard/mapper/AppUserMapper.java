package com.biocome.platform.inter.gateguard.mapper;

import com.biocome.platform.inter.gateguard.entity.AppUser;
import com.biocome.platform.inter.gateguard.vo.user.AppUserVo;
import com.biocome.platform.inter.gateguard.vo.user.SimpleUserInfoVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-07-31 18:22:58
 */
public interface AppUserMapper extends Mapper<AppUser> {

    /**
     * 根据用户编号修改完善信息状态
     *
     * @param usercode 用户编号
     * @return void
     * @Author shenlele
     * @Date 2019/8/2 10:38
     */
    void updateIsComplete(@Param("usercode") String usercode);

    AppUserVo detail(@Param("usercode") String usercode);

    void changePassword(@Param("username") String username,@Param("password")  String password);

    List<SimpleUserInfoVo> simpleUserInfoVo(@Param("usercode") String usercode);
}
