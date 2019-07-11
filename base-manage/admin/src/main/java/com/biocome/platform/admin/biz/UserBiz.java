package com.biocome.platform.admin.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.constant.EnableStatus;
import com.biocome.platform.admin.entity.User;
import com.biocome.platform.admin.mapper.MenuMapper;
import com.biocome.platform.admin.mapper.UserMapper;
import com.biocome.platform.admin.vo.UserVo;
import com.biocome.platform.auth.client.jwt.UserAuthUtil;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.constant.UserConstant;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 16:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBiz<UserMapper,User> {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserAuthUtil userAuthUtil;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertSelective(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    @Override
    @CacheClear(pre="user{1.username}")
    public void updateSelectiveById(User entity) {
        super.updateSelectiveById(entity);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Cache(key="user{1}")
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

    @Override
    @Cache(key = "userId:{1}")
    public User selectById(Object id) {
        return super.selectById(id);
    }

    /**
     * 逻辑删除用户
     * @param id
     */
    public void disable(int id) {
        User user = selectById(id);
        if(ValidateUtils.isEmpty(user)){
            user.setStatus(String.valueOf(EnableStatus.STATUS_DISABLE));
            updateSelectiveById(user);
        }
    }

    /**
     * 更新选择属性
     * @param user
     * @return
     */
    public ObjectRestResponse<User> updateSelective(User user) {
        try{
            if(ValidateUtils.isEmpty(user.getName())){
                String username = user.getUsername();
                user.setUsername(username);
            }
            filterProerties(user);
            updateSelectiveById(user);
            return new ObjectRestResponse<>().success();
        }catch (Exception e){
            return new ObjectRestResponse<>().failure();
        }
    }

    /**
     * 过滤掉不更新的字段
     * @param user
     */
    private void filterProerties(User user) {
        user.setUsername(null);
        user.setPassword(null);
    }

    /**
     * 新增用户
     * @param entity
     * @return
     */
    public BaseResponse addUser(User entity) {
        try{
            String username = entity.getUsername();
            if(ValidateUtils.isNotEmpty(username)){
                User user = getUserByUsername(username);
                if(ValidateUtils.isNotEmpty(user)){
                    return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "用户名已存在!");
                }
                entity.setStatus(String.valueOf(EnableStatus.STATUS_ENABLE));
                insertSelective(entity);
                return new ObjectRestResponse<>().success();
            }else {
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "用户名不能为空！");
            }
        }catch (Exception e){
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "添加用户信息失败，错误信息为：" + e.getMessage());
        }
    }

    /**
     * 查询用户列表
     * @param pageSize
     * @param pageNo
     * @return
     */
    public TableResultResponse<UserVo> selectUserListByCondition(int pageSize, int pageNo) {
        try {
            Page<UserVo> result = PageHelper.startPage(pageNo, pageSize);
            userMapper.selectUserList();
            return new TableResultResponse<UserVo>(result.getTotal(),result.getResult());
        }catch (Exception e){
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询用户信息失败");
        }

    }
}
