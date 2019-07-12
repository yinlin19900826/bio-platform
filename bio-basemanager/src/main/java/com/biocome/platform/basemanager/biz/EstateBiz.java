package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.Estate;
import com.biocome.platform.basemanager.mapper.EstateMapper;
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
 * @ClassName: EstateBiz
 * @Author: shenlele
 * @Date: 2019/5/7 09:35
 * @Description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class EstateBiz extends BaseBiz<EstateMapper, Estate> {

    @Autowired
    private EstateMapper estateMapper;

    /**
     * 根据小区编号或小区名称查询所有小区信息，查询所有时传参都为null
     *
     * @param pageSize   分页数量
     * @param pageNum    分页页码
     * @param estatename 小区名称
     * @param estatecode 小区编号
     * @return com.github.wxiaoqi.security.common.msg.TableResultResponse<Estate>
     * @Author shenlele
     * @Date 2019/5/8 14:09
     */
    public TableResultResponse<Estate> selectByAttribute(int pageSize, int pageNum, String estatename, String estatecode) {
        TableResultResponse<Estate> res;
        try {
            Page<Estate> result = PageHelper.startPage(pageNum, pageSize);
            estateMapper.selectByAttribute(estatename, estatecode);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询小区信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询小区信息失败!");
        }
        return res;
    }

    /**
     * 根据ID删除
     *
     * @param idList 主键列表
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<Estate>
     * @Author shenlele
     * @Date 2019/5/8 14:10
     */
    public ObjectRestResponse<Estate> deleteEstate(String idList) throws Exception {
        estateMapper.deleteEstate(IdUtils.getIds(idList));
        return new ObjectRestResponse<>(true);
    }

    /**
     * 根据所给条件查询小区列表
     *
     * @param pageSize 分页数量
     * @param pageNum  分页页码
     * @param estate   小区信息
     * @return com.github.wxiaoqi.security.common.msg.TableResultResponse<Estate>
     * @Author shenlele
     * @Date 2019/5/8 15:25
     */
    public TableResultResponse<Estate> selectByEstate(int pageSize, int pageNum, Estate estate) {
        TableResultResponse<Estate> res;
        try {
            //查询status为有效，del未删除的
            estate.setStatus(0);
            estate.setDel(0);
            Page<Estate> result = PageHelper.startPage(pageNum, pageSize);
            estateMapper.select(estate);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询小区信息失败!");
        }
        return res;
    }

    /**
     * 保存小区信息（小区编号唯一）
     *
     * @param estate 小区信息
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/22 19:38
     */
    public ObjectRestResponse insertEstate(Estate estate) throws Exception {
        Estate estate1 = new Estate();
        estate1.setEstatecode(estate.getEstatecode());
        List<Estate> estates = estateMapper.select(estate1);
        if (ValidateUtils.isEmpty(estates)) {
            estateMapper.insertSelective(estate);
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("保存失败！小区编号已存在！");
        }
    }
}
