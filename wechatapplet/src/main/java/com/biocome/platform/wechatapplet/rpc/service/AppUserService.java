package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.auth.common.util.constatns.CommonConstants;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.inter.gateguard.entity.AppUser;
import com.biocome.platform.wechatapplet.biz.AppUserBiz;
import com.biocome.platform.wechatapplet.mapper.AppUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @ClassName: AppUserService
 * @Author: zengqiang
 * @Date: 2019/8/2
 * @Description:
 */
@Service
@Slf4j
public class AppUserService extends BaseBiz<AppUserMapper, AppUser> {
    @Autowired
    AppUserBiz appUserBiz;
    @Autowired
    JedisCluster jedisCluster;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AppUserInfo validate(String username, String password) {
        AppUserInfo info = new AppUserInfo();
        try {
            AppUser user = appUserBiz.getUserByUsername(username);
            if(ValidateUtils.isEmpty(user)){
                throw new Exception("用户不存在");
            }else{
                if (encoder.matches(password, user.getPassword())) {
                    BeanUtils.copyProperties(user, info);
                    info.setId(user.getId().toString());
                    info.setEffectiveCode(DateUtils.getCurrentTimeStr()+"_"+ UUIDUtils.generateShortUuid());
                    jedisCluster.del(CommonConstants.JWT_ACCESS_TOKEN_EFFECTIVE_CODE+"_"+info.getUsername());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return info;
    }

    public AppUser detail(String username) {
        return mapper.detail(username);
    }
}
