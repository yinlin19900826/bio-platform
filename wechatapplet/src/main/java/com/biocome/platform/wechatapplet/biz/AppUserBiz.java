package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.biz.BaseBiz;
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
}