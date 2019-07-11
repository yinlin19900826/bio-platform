package com.biocome.platform.admin.biz;

import org.springframework.stereotype.Service;

import com.biocome.platform.admin.entity.GroupType;
import com.biocome.platform.admin.mapper.GroupTypeMapper;
import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-12 8:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper,GroupType> {
}
