package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.wechatapplet.entity.AppUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

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

    AppUser detail(@Param("username") String username);
}
