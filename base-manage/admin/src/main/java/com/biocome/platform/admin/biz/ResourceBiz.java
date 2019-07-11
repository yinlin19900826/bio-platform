package com.biocome.platform.admin.biz;

import com.biocome.platform.admin.entity.Resource;
import com.biocome.platform.admin.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资源表
 *
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-21 11:55:54
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceBiz extends BaseBiz<ResourceMapper, Resource> {
}