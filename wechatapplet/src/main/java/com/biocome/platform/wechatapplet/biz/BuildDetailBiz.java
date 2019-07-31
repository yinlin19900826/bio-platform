package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.wechatapplet.mapper.BuildDetailMapper;
import com.biocome.platform.wechatapplet.vo.build.BuildDetailResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 10:43
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BuildDetailBiz {

    @Autowired
    private BuildDetailMapper mapper;

    public List<BuildDetailResp> getBuildByUsercode(String usercode) {
        return mapper.getBuildByUsercode(usercode);
    }
}
