package com.biocome.platform.admin.biz;

import com.biocome.platform.admin.vo.camera.AddGroupBindVo;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.biocome.platform.admin.entity.UserCameraGroupBind;
import com.biocome.platform.admin.mapper.UserCameraGroupBindMapper;
import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户与镜头组关联
 *
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 20:02:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserCameraGroupBindBiz extends BaseBiz<UserCameraGroupBindMapper,UserCameraGroupBind> {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public void addGroupBind(AddGroupBindVo vo) {
        String ids = IdUtils.list2String(vo.getList());
        UserCameraGroupBind entity = new UserCameraGroupBind();
        entity.setUsername(vo.getUsername());
        entity.setGroupIds(ids);
        mapper.insertSelective(entity);
    }

    public TableResultResponse<Integer> bindList(String username) {
        try {
            List<Integer> list = new ArrayList<Integer>();
            List<String> idsList = mapper.selectBindIds(username);
            if(ValidateUtils.isNotEmpty(idsList)){
                list = IdUtils.getIds(idsList.get(0));
            }
            return new TableResultResponse<Integer>(list.size(), list);
        }catch (Exception e){
            e.printStackTrace();
            log.info("查询用户绑定组失败，错误信息信息："+e.getMessage());
            return new TableResultResponse<Integer>(CommonConstants.EX_OTHER_CODE, "查询用户绑定组失败，错误信息信息：数据库错误！");
        }
    }

}