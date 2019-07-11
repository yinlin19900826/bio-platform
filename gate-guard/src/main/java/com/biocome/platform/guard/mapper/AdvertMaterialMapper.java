package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.AdvertMaterial;
import com.biocome.platform.guard.vo.advert.AdvertPreviewResp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: AdvertMaterialMapper
 * @Author: shenlele
 * @Date: 2019/5/30 16:20
 * @Description:
 */
public interface AdvertMaterialMapper extends Mapper<AdvertMaterial> {

    /**
     * 根据所传参数查询素材列表
     *
     * @param model 所传参数
     * @return java.util.List<AdvertMaterial>
     * @Author shenlele
     * @Date 2019/5/30 17:10
     */
    List<AdvertMaterial> selectByAttribute(@Param("model") AdvertMaterial model);

    /**
     * 根据所传idList批量删除数据
     *
     * @param list id列表
     * @return int
     * @Author shenlele
     * @Date 2019/5/30 16:40
     */
    int deleteByIdList(@Param("list") List<Integer> list);

    /**
     * 根据ID列表查询所有广告素材
     *
     * @param list id列表
     * @return java.util.List<AdvertMaterial>
     * @Author shenlele
     * @Date 2019/5/30 18:27
     */
    List<AdvertMaterial> selectByList(@Param("list") List<Integer> list);

    /**
     * 根据计划id查询素材
     * @param id
     * @return
     */
    List<AdvertMaterial> selectMaterialByPlanId(@Param("planid") Integer id);

    /**
     * 根据计划id查询AdvertPreviewResp
     * @param id
     * @return
     */
    List<AdvertPreviewResp> selectAdvertPreviewRespByPlanId(@Param("planid") Integer id);
}
