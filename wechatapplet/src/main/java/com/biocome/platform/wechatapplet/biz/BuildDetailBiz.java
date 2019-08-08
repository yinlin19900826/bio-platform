package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.wechatapplet.mapper.BuildDetailMapper;
import com.biocome.platform.wechatapplet.vo.build.BuildDetailResp;
import com.biocome.platform.wechatapplet.vo.build.HouseResp;
import com.biocome.platform.wechatapplet.vo.build.LesseeResp;
import com.biocome.platform.wechatapplet.vo.build.LesseeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    /**
     * 根据楼栋编号获取所有房屋信息，并获得每个房屋中住户数量
     *
     * @param buildCode 楼栋编号
     * @return java.util.List<com.biocome.platform.wechatapplet.vo.build.HouseResp>
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/8/7 14:14
     */
    public List<HouseResp> selectHouseResp(String buildCode) throws Exception {
        return mapper.selectHouseResp(buildCode);
    }

    /**
     * 根据房屋编号查询特定人员类列表
     *
     * @param houseCode 房屋编号
     * @return com.biocome.platform.wechatapplet.vo.build.LesseeResp
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/8/7 18:36
     */
    public LesseeResp selectLesseeResp(String houseCode) throws Exception {
        List<LesseeVo> list = mapper.selectLesseeResp(houseCode);
        List<LesseeVo> principals = new ArrayList<>();
        List<LesseeVo> lessees = new ArrayList<>();
        for (LesseeVo vo : list) {
            //如果为5则是负责人
            if (vo.getFlag() == 5) {
                principals.add(vo);
            } else {
                lessees.add(vo);
            }
        }
        return new LesseeResp(principals, lessees);
    }
}
