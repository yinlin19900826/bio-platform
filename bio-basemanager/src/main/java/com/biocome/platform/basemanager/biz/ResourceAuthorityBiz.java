package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.ResourceAuthority;
import com.biocome.platform.basemanager.mapper.ResourceAuthorityMapper;
import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ace on 2017/6/19.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper,ResourceAuthority> {
}
