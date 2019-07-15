package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.InstitutionUser;
import com.biocome.platform.common.vo.UINodeVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 机构用户
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 20:02:19
 */
public interface InstitutionUserMapper extends Mapper<InstitutionUser> {

    List<UINodeVo> selectList();
}
