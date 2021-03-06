package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.entity.Build;
import com.biocome.platform.inter.basemanager.vo.DistrictResp;
import com.biocome.platform.inter.basemanager.vo.syncho.BuildAndBrandVo;
import com.biocome.platform.inter.basemanager.vo.syncho.BuildVo;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: BuildMapper
 * @Author: shenlele
 * @Date: 2019/5/8 11:57
 * @Description:
 */
public interface BuildMapper extends Mapper<Build> {

    /**
     * 根据楼栋名称或楼栋地址查询所有楼栋信息，查询所有时传参都为null
     *
     * @param build 查询参数
     * @return java.util.List<Build>
     * @Author shenlele
     * @Date 2019/5/8 13:53
     */
    List<Build> selectByAttribute(@Param("model") Build build);


    /**
     * 根据ID删除
     *
     * @param list 主键编号列表
     * @return int
     * @Author shenlele
     * @Date 2019/5/8 13:56
     */
    int deleteBuild(@Param("list") List<Integer> list);

    /**
     * 根据楼栋编号查询返回特定类
     *
     * @param list 楼栋编号列表
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.BuildVo>
     * @Author shenlele
     * @Date 2019/5/15 9:29
     */
    List<BuildVo> selectByCode(@Param("list") List<String> list);

    /**
     * 根据所属小区代码查询所有小区，构建树
     *
     * @param estatecode 小区编号
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/20 11:43
     */
    List<TreeDistrictVO> selectByTree(@Param("estatecode") String estatecode);

    /**
     * 根据楼栋信息查询相关楼栋参数
     *
     * @param vo 参数
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.BuildVo>
     * @Author shenlele
     * @Date 2019/5/22 11:26
     */
    List<BuildVo> selectByBuild(@Param("vo") BuildAndBrandVo vo);

    List<Build> queryBuildByBuildcode(@Param("list") List<String> needBuildcodeList);

    /**
     * 根据小区编号查询楼栋信息，返回特定实体类
     *
     * @param code 编码
     * @return com.biocome.platform.inter.basemanager.vo.DistrictResp
     * @Author shenlele
     * @Date 2019/9/3 10:54
     */
    List<DistrictResp> selectDistrict(@Param("code") String code);
}