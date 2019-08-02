package com.biocome.platform.wechatapplet.biz;

import com.ace.cache.annotation.Cache;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.vo.user.AppUserInfo;
import org.springframework.stereotype.Service;

import com.biocome.platform.wechatapplet.entity.AppUser;
import com.biocome.platform.wechatapplet.mapper.AppUserMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-07-31 18:22:58
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AppUserBiz extends BaseBiz<AppUserMapper,AppUser> {

    /**
     * 根据用户编号修改完善信息状态
     *
     * @param usercode 用户编号
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/8/2 10:40
     */
    public void updateIsComplete(String usercode) throws Exception{
        mapper.updateIsComplete(usercode);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Cache(key="appuser{1}")
    public AppUser getUserByUsername(String username) {
        AppUser user = new AppUser();
        user.setUsername(username);
        return mapper.selectOne(user);
    }
}