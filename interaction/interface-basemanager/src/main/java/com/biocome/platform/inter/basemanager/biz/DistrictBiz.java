package com.biocome.platform.inter.basemanager.biz;

import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.mapper.BuildMapper;
import com.biocome.platform.inter.basemanager.mapper.DistrictMapper;
import com.biocome.platform.inter.basemanager.mapper.EstateMapper;
import com.biocome.platform.inter.basemanager.mapper.UnitMapper;
import com.biocome.platform.inter.basemanager.vo.DistrictResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: DistrictBiz
 * @Author: shenlele
 * @Date: 2019/9/3 10:51
 * @Description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DistrictBiz {

    private final UnitMapper unitMapper;
    private final BuildMapper buildMapper;
    private final EstateMapper estateMapper;
    private final DistrictMapper districtMapper;

    @Autowired
    public DistrictBiz(UnitMapper unitMapper, BuildMapper buildMapper, EstateMapper estateMapper, DistrictMapper districtMapper) {
        this.unitMapper = unitMapper;
        this.buildMapper = buildMapper;
        this.estateMapper = estateMapper;
        this.districtMapper = districtMapper;
    }

    /**
     * 根据type与code查询行政区划信息
     *
     * @param type 类型(1:省,2:市,3:县/区,4:乡/街道,5:村/派出所,6:组/小区,7:楼栋)
     * @param code 编码
     * @return com.biocome.platform.inter.basemanager.vo.DistrictResp
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/9/3 10:56
     */
    public List<DistrictResp> selectDistrict(String type, String code) throws Exception {
        List<DistrictResp> resp;
        if (AdminCommonConstant.DISTRICT_ESTATE.equals(type)) {
            resp = estateMapper.selectDistrict();
        } else if (AdminCommonConstant.DISTRICT_BUILD.equals(type)) {
            resp = buildMapper.selectDistrict(code);
        } else if (AdminCommonConstant.DISTRICT_UNIT.equals(type)) {
            resp = unitMapper.selectDistrict(code);
        } else {
            if (AdminCommonConstant.DISTRICT_PROVINCE.equals(type)) {
                code = AdminCommonConstant.DEFAULT_ZERO;
            }
            resp = districtMapper.selectDistrict(code);
        }
        return resp;
    }
}
