package com.biocome.platform.guard.biz;

import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.mapper.SwitchVideoMapper;
import com.biocome.platform.guard.mapper.TreeDistrictMapper;
import com.biocome.platform.guard.vo.showmanage.SwitchVideoVo;
import com.biocome.platform.inter.basemanager.mapper.BuildMapper;
import com.biocome.platform.inter.basemanager.mapper.EstateMapper;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SwitchVideoBiz
 * @Author: yinlin
 * @Date: 2019/8/28 13:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SwitchVideoBiz {

    @Autowired
    private SwitchVideoMapper switchVideoMapper;

    /**
     * 根据楼栋编码查询对应的摄像机信息
     *
     * @param buildcode 楼栋编码
     * @return java.util.List<SwitchVideoVO>
     * @Author yinlin
     * @Date 2019/8/28 13:42
     */
    public SwitchVideoVo switchLiveVideo(String buildcode) throws Exception {

       // SwitchVideoVo switchVideoVo = new SwitchVideoVo();

        SwitchVideoVo switchVideoVo = switchVideoMapper.switchLiveVideo(buildcode);

        return switchVideoVo;
    }


}
