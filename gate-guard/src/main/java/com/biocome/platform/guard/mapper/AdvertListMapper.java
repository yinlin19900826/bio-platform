package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.Advert;
import com.biocome.platform.guard.vo.advert.AdvertAddListRpcResp;
import com.biocome.platform.guard.vo.advert.AdvertVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: AdvertListMapper
 * @Author: shenlele
 * @Date: 2019/6/3 15:06
 * @Description:
 */
public interface AdvertListMapper extends Mapper<Advert> {

    /**
     * 根据所传参数查询广告列表
     *
     * @param model 广告参数
     * @return java.util.List<Advert>
     * @Author shenlele
     * @Date 2019/6/3 15:37
     */
    List<Advert> selectByAttribute(@Param("model") AdvertVo model);

    /**
     * 根据所传参数删除数据
     *
     * @param adno 广告编号
     * @param sn 设备编号
     * @return int
     * @Author shenlele
     * @Date 2019/6/3 16:16
     */
    int deleteByParam(@Param("adno") String adno, @Param("sn") String sn);

    /**
     * 批量插入
     * @param advertList
     * @return
     */
    int insertList(@Param("list") List<Advert> advertList);

    /**
     * 批量更新状态
     * @param advertAddListRpcResps
     * @return
     */
    int updateList(@Param("list") List<AdvertAddListRpcResp> advertAddListRpcResps);
}
