package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.common.constant.UserConstant;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.wechatapplet.biz.AppUserBiz;
import com.biocome.platform.wechatapplet.entity.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.Date;

/**
 * @ClassName: AppUserService
 * @Author: zengqiang
 * @Date: 2019/8/2
 * @Description:
 */
@Service
public class AppUserService {
    @Autowired
    AppUserBiz appUserBiz;
    @Autowired
    JedisCluster jedisCluster;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AppUserInfo validate(String username, String password) {
        AppUserInfo info = new AppUserInfo();
        AppUser user = appUserBiz.getUserByUsername(username);
        if (encoder.matches(password, user.getPassword())) {
            BeanUtils.copyProperties(user, info);
            info.setId(user.getId().toString());
            info.setEffectiveCode(DateUtils.getFormatDate(new Date())+"_"+ UUIDUtils.generateShortUuid());
        }
        return info;
    }

    public AppUser search(String username) {
        return appUserBiz.getUserByUsername(username);
    }
}
