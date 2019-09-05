package com.biocome.platform.inter.basemanager.biz;

import com.biocome.platform.inter.basemanager.entity.Unit;
import com.biocome.platform.inter.basemanager.mapper.UnitMapper;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: UnitBiz
 * @Author: shenlele
 * @Date: 2019/5/7 10:39
 * @Description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UnitBiz extends BaseBiz<UnitMapper, Unit> {

    @Autowired
    private UnitMapper unitMapper;

    /**
     * 根据小区名称，楼栋名称，单元名称查询所有单元信息，查询所有时传参都为null
     *
     * @param pageSize   分页数量
     * @param pageNum    分页页码
     * @param unitname   单元名称
     * @param buildname  楼栋名称
     * @param estatename 小区名称
     * @return com.github.wxiaoqi.security.TableResultResponse<Unit>
     * @Author shenlele
     * @Date 2019/5/8 14:13
     */
    public TableResultResponse<Unit> selectByAttribute(int pageSize, int pageNum, String unitname, String buildname, String estatename) {
        TableResultResponse<Unit> res;
        try {
            Page<Unit> result = PageHelper.startPage(pageNum, pageSize);
            unitMapper.selectByAttribute(unitname, buildname, estatename);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询单元信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询单元信息失败!");
        }
        return res;
    }

    /**
     * 根据ID删除
     *
     * @param idList 主键
     * @return com.github.wxiaoqi.security.ObjectRestResponse<Unit>
     * @Author shenlele
     * @Date 2019/5/8 14:14
     */
    public ObjectRestResponse<Unit> deleteUnit(String idList) throws Exception {
        unitMapper.deleteUnit(IdUtils.getIds(idList));
        return new ObjectRestResponse<>(true);
    }

    /**
     * 根据所传单元信息查询
     *
     * @param pageSize 分页大小
     * @param pageNum  分页页码
     * @param unit     单元信息
     * @return com.github.wxiaoqi.security.TableResultResponse<Unit>
     * @Author shenlele
     * @Date 2019/5/10 16:19
     */
    public TableResultResponse<Unit> selectByUnit(int pageSize, int pageNum, Unit unit) {
        TableResultResponse<Unit> res;
        try {
            //查询status为有效，del未删除的
            unit.setStatus(0);
            unit.setDel(0);
            Page<Unit> result = PageHelper.startPage(pageNum, pageSize);
            unitMapper.select(unit);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询单元信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询单元信息失败!");
        }
        return res;
    }

    /**
     * 保存单元信息（单元编号唯一）
     *
     * @param unit 单元信息
     * @return com.github.wxiaoqi.security.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/22 19:53
     */
    public ObjectRestResponse insertUnit(Unit unit) throws Exception {
        Unit unit1 = new Unit();
        unit1.setUnitcode(unit.getUnitcode());
        List<Unit> units = unitMapper.select(unit1);
        if (ValidateUtils.isEmpty(units)) {
            unitMapper.insertSelective(unit);
            return new ObjectRestResponse().success();
        } else {
            return new ObjectRestResponse(204, "保存失败！单元编号已存在！");
        }
    }
}
