package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.vo.showmanage.SwitchVideoVo;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: SwitchVideoMapper
 * @Author: yinlin
 * @Date: 2019/8/28 14:39
 * @Description:
 */
public interface SwitchVideoMapper {

    /**
     * 根据楼栋编码查询对应的摄像机信息
     *
     * @param buildcode 楼栋编码
     * @return java.util.List<SwitchVideoVO>
     * @Author yinlin
     * @Date 2019/8/28 14:39
     */
    SwitchVideoVo switchLiveVideo(@Param("buildcode") String buildcode);

}
