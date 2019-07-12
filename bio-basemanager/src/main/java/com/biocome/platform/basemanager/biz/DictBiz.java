package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.Dictionary;
import com.biocome.platform.basemanager.mapper.DictMapper;
import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hxy
 * @date 2019/5/16 14:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictBiz extends BaseBiz<DictMapper, Dictionary> {
}
