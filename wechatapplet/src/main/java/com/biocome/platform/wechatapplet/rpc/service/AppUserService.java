package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.common.vo.user.AppUserInfo;
import com.biocome.platform.wechatapplet.biz.AppUserBiz;
import com.biocome.platform.wechatapplet.constant.AppConstant;
import com.biocome.platform.wechatapplet.entity.AppUser;
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

    public ObjectRestResponse<AppUserInfo> validate(String username, String password, String type) {
        ObjectRestResponse<AppUserInfo> res = new ObjectRestResponse<AppUserInfo>();
        AppUserInfo info = new AppUserInfo();
        try {
            if(ValidateUtils.isEmpty(type)){
                res = new ObjectRestResponse<AppUserInfo>(CommonConstants.EX_APP_USERTYPE_NOT_NULL,"请选择用户类型！");
                return res;
            }
            AppUser user = appUserBiz.getUserByUsername(username);
            if(ValidateUtils.isEmpty(user)){
                res = new ObjectRestResponse<AppUserInfo>(CommonConstants.EX_APP_USERTYPE_NOT_NULL,"用户不存在！");
                return res;
            }else{
                if(user.getType() == AppConstant.APP_USER_TYPE_ADMIN && user.getType() != Integer.parseInt(type)){
                    res = new ObjectRestResponse<AppUserInfo>(CommonConstants.EX_APP_USERTYPE_NOT_NULL,"该账号类型为租户！");
                    return res;
                }
                if(user.getType() == AppConstant.APP_USER_TYPE_LESSEE && user.getType() != Integer.parseInt(type)){
                    res = new ObjectRestResponse<AppUserInfo>(CommonConstants.EX_APP_USERTYPE_NOT_NULL,"该账号类型为管理员！");
                    return res;
                }
                if (encoder.matches(password, user.getPassword())) {
                    BeanUtils.copyProperties(user, info);
                    info.setId(user.getId().toString());
                    info.setEffectiveCode(DateUtils.getCurrentTimeStr()+"_"+ UUIDUtils.generateShortUuid());
                    jedisCluster.del(CommonConstants.JWT_ACCESS_TOKEN_EFFECTIVE_CODE+"_"+info.getUsername());
                    res.setData(info);
                }else{
                    res = new ObjectRestResponse<AppUserInfo>(CommonConstants.EX_APP_ERR_PWD,"密码错误！");
                    return res;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return res;
    }

}
