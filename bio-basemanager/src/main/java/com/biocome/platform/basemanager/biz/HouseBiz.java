package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.House;
import com.biocome.platform.basemanager.mapper.HouseMapper;
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
 * @ClassName: HouseBiz
 * @Author: shenlele
 * @Date: 2019/5/7 10:01
 * @Description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class HouseBiz extends BaseBiz<HouseMapper, House> {

    @Autowired
    private HouseMapper houseMapper;

    /**
     * 根据房屋名称，房屋地址查询房屋信息，查询所有时传参都为null
     *
     * @param pageSize  分页数量
     * @param pageNum   分页页码
     * @param housename 房屋名称
     * @param housesite 房屋地址
     * @return com.github.wxiaoqi.security.common.msg.TableResultResponse<House>
     * @Author shenlele
     * @Date 2019/5/8 14:10
     */
    public TableResultResponse<House> selectByAttribute(int pageSize, int pageNum, String housename, String housesite) {
        TableResultResponse<House> res;
        try {
            Page<House> result = PageHelper.startPage(pageNum, pageSize);
            houseMapper.selectByAttribute(housename, housesite);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询房屋信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询房屋信息失败!");
        }
        return res;
    }

    /**
     * 根据ID删除
     *
     * @param idList 主键
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse<House>
     * @Author shenlele
     * @Date 2019/5/8 14:11
     */
    public ObjectRestResponse<House> deleteHouse(String idList) throws Exception {
        houseMapper.deleteHouse(IdUtils.getIds(idList));
        return new ObjectRestResponse<>(true);
    }

    /**
     * 根据所传房屋信息查询
     *
     * @param pageSize 分页大小
     * @param pageNum  分页页码
     * @param house    房屋信息
     * @return com.github.wxiaoqi.security.common.msg.TableResultResponse<House>
     * @Author shenlele
     * @Date 2019/5/10 16:18
     */
    public TableResultResponse<House> selectByHouse(int pageSize, int pageNum, House house) {
        TableResultResponse<House> res;
        try {
            //查询status为有效，del未删除的
            house.setStatus(0);
            house.setDel(0);
            Page<House> result = PageHelper.startPage(pageNum, pageSize);
            houseMapper.select(house);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询房屋信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询房屋信息失败!");
        }
        return res;
    }

    /**
     * 插入房屋操作（房屋编号唯一）
     *
     * @param house 房屋信息
     * @return com.github.wxiaoqi.security.common.msg.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/22 19:41
     */
    public ObjectRestResponse insertHouse(House house) throws Exception {
        House house1 = new House();
        house1.setHousecode(house.getHousecode());
        List<House> houses = houseMapper.select(house1);
        if (ValidateUtils.isEmpty(houses)) {
            houseMapper.insertSelective(house);
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("保存失败！房屋编号已存在！");
        }
    }
}
