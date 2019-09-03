package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.entity.Estate;
import com.biocome.platform.inter.basemanager.vo.DistrictResp;
import com.biocome.platform.inter.basemanager.vo.syncho.EstateVo;
import com.biocome.platform.inter.basemanager.vo.syncho.SynchoVo;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: EstateMapper
 * @Author: shenlele
 * @Date: 2019/5/8 13:58
 * @Description:
 */
public interface EstateMapper extends Mapper<Estate> {

    /**
     * 根据小区编号或小区名称查询所有小区信息，查询所有时传参都为null
     *
     * @param estatename 小区名称
     * @param estatecode 小区编号
     * @return java.util.List<Estate>
     * @Author shenlele
     * @Date 2019/5/8 13:59
     */
    List<Estate> selectByAttribute(@Param("estatename") String estatename, @Param("estatecode") String estatecode);


    /**
     * 根据ID删除
     *
     * @param list 主键列表
     * @return
     * @Author shenlele
     * @Date 2019/5/8 13:59
     */
    void deleteEstate(@Param("list") List<Integer> list);

    /**
     * 根据所属行政区划代码查询所有小区，构建树
     *
     * @param areacode 所属行政区划代码
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/20 11:40
     */
    List<TreeDistrictVO> selectByTree(@Param("areacode") String areacode);

    /**
     * 根据小区编号列表查询小区信息
     *
     * @param estateCodes 小区编号列表
     * @return java.util.List<Estate>
     * @Author shenlele
     * @Date 2019/7/12 9:14
     */
    List<Estate> queryEstateByEstatecode(@Param("list") List<String> estateCodes);

    /**
     * 根据SynchoVo返回所属小区信息特定实体类
     *
     * @param vo 楼栋编号
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.EstateVo>
     * @Author shenlele
     * @Date 2019/5/22 10:16
     */
    List<EstateVo> selectBySynchoVo(@Param("vo") SynchoVo vo);

    /**
     * 根据小区信息查询返回特定类
     *
     * @param estate 小区信息
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.EstateVo>
     * @Author shenlele
     * @Date 2019/5/14 18:33
     */
    List<EstateVo> selectByEstate(@Param("estate") Estate estate);


    /**
     * 查询所有小区，返回特定类
     *
     * @return com.biocome.platform.inter.basemanager.vo.DistrictResp
     * @Author shenlele
     * @Date 2019/9/3 10:54
     */
    List<DistrictResp> selectDistrict();

}
