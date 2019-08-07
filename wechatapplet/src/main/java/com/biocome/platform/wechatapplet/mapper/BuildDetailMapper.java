package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.wechatapplet.vo.build.BuildDetailResp;
import com.biocome.platform.wechatapplet.vo.build.HouseResp;
import com.biocome.platform.wechatapplet.vo.build.LesseeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 10:44
 */
public interface BuildDetailMapper {
    List<BuildDetailResp> getBuildByUsercode(@Param("usercode") String usercode);

    /**
     * 根据楼栋编号获取所有房屋信息，并获得每个房屋中住户数量
     *
     * @param buildCode 楼栋编号
     * @return java.util.List<com.biocome.platform.wechatapplet.vo.build.HouseResp>
     * @Author shenlele
     * @Date 2019/8/7 14:11
     */
    List<HouseResp> selectHouseResp(@Param("buildCode") String buildCode);

    /**
     * 根据房屋编号查询特定人员类列表
     *
     * @param houseCode 房屋编号
     * @return java.util.List<com.biocome.platform.wechatapplet.vo.build.LesseeResp>
     * @Author shenlele
     * @Date 2019/8/7 17:30
     */
    List<LesseeVo> selectLesseeResp(@Param("houseCode") String houseCode);
}
