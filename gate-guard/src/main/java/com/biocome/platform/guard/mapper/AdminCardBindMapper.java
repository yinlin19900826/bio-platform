package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.AdminCardBind;
import com.biocome.platform.guard.vo.admin.AdminCardBindVo;
import com.biocome.platform.guard.vo.admin.AdminCardVo;
import com.biocome.platform.inter.basemanager.vo.device.CardDeviceVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 管理员卡
 * 
 * @author zengqiang
 * @email 360713542@qq.com
 * @date 2019-05-17 10:06:14
 */
public interface AdminCardBindMapper extends Mapper<AdminCardBind> {

    List<AdminCardBindVo> cardBindBuildingList(@Param("usercode") String usercode, @Param("cardNo") String cardNo);

    void batchInsert(@Param("list") List list);

    void batchDeleteByIdsAndUsercode(@Param("ids") List<Integer> ids, @Param("usercode") String usercode);

    void updateCardStatus(@Param("isalive") int isalive, @Param("admincode") String admincode, @Param("cardNo") String cardNo);

    void removeCard(@Param("usercode") String usercode, @Param("cardNo") String cardNo);

    List<AdminCardVo> adminCardListOnBuilding(@Param("buildcode") String buildcode);

    List<CardDeviceVo> cardDeviceList(@Param("cardNoList") List<String> cardNoList);
}
