package com.biocome.platform.inter.gateguard.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.gateguard.entity.AppUser;
import com.biocome.platform.inter.gateguard.mapper.AppUserMapper;
import com.biocome.platform.inter.gateguard.vo.user.AppUserVo;
import com.biocome.platform.inter.gateguard.vo.user.ResetPasswordParam;
import com.biocome.platform.inter.gateguard.vo.user.SimpleUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 *
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-07-31 18:22:58
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AppUserBiz extends BaseBiz<AppUserMapper, AppUser> {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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

    public ObjectRestResponse<AppUserVo> detail(String username) {
        AppUserVo vo = userDetail(username);
        if(ValidateUtils.isEmpty(vo)){
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "未查到用户信息！");
        }
        return new ObjectRestResponse<>().data(vo).success();
    }

    public BaseResponse resetPassword(ResetPasswordParam param) {
        String certNo = param.getCertNo();
        AppUserVo user = userDetail(certNo);
        if(ValidateUtils.isEmpty(user)){
            return new BaseResponse(CommonConstants.EX_APP_USER_NOT_EXIST, "未找到用户！请核对证件号码！");
        }
        String phoneNo = param.getPhoneNo();
        String sms = param.getSms();
        //TODO... 核对短信
        String reset = param.getResetPassword();
        String confirm = param.getConfirmPassword();
        if(ValidateUtils.isEmpty(reset) || ValidateUtils.isEmpty(confirm)){
            return new BaseResponse(CommonConstants.EX_APP_USER_NOT_EXIST, "密码和确认密码不能为空！");
        }
        if(!reset.equals(confirm)){
            return new BaseResponse(CommonConstants.EX_APP_USER_NOT_EXIST, "两次密码不一致！");
        }
        String encrypPassword = encoder.encode(reset);
        try {
            changePassword(certNo, encrypPassword);
        }catch (Exception e){
            log.info("修改密码错误！错误信息："+e.getMessage());
            e.printStackTrace();
            return new BaseResponse(CommonConstants.EX_APP_DB_ERR, "重置密码失败！错误信息：数据库错误");
        }
        return new BaseResponse();
    }

    @CacheClear(key = "AppUser:{1}")
    private void changePassword(String username, String password) {
        mapper.changePassword(username, password);
    }

    @Cache(key = "AppUser:{1}")
    private AppUserVo userDetail(String username){
        return mapper.detail(username);
    }

    /***
     * 用户简单信息(不考虑租户usercode对应多张卡的情况)
     * @param usercode
     * @return
     */
    public ObjectRestResponse<SimpleUserInfoVo> simpleUserInfo(String usercode) {
        if(ValidateUtils.isEmpty(usercode)){
            return new ObjectRestResponse(CommonConstants.EX_USERCODE_NULL,"用户usercode不能为空！");
        }
        List<SimpleUserInfoVo> list = mapper.simpleUserInfoVo(usercode);
        if(ValidateUtils.isEmpty(list)){
            return new ObjectRestResponse<>(CommonConstants.EX_APP_USER_NOT_EXIST, "用户不存在！");
        }
        SimpleUserInfoVo info = list.get(0);
        return new ObjectRestResponse().data(info).success();
    }

    public ObjectRestResponse<AppUserVo> detailByUsercode(String usercode) {
        return null;
    }
}