package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.AdminCardBind;
import com.biocome.platform.guard.vo.admin.AdminCardBindVo;
import com.biocome.platform.guard.vo.admin.AdminCardVo;
import com.biocome.platform.guard.vo.admin.AdminSimpleCardVo;
import com.biocome.platform.inter.basemanager.vo.card.CardDetailVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import com.biocome.platform.inter.basemanager.vo.device.CardDeviceVo;

import java.util.List;
import java.util.Map;

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

    /*以下为迁移部分*/

    List<AdminSimpleCardVo> selectAdminCardList(@Param("admincode") String admincode, @Param("cardNo") String cardNo, @Param("isalive") Integer isalive);

    List<AdminCardVo> superCardList(@Param("username") String username, @Param("certNo") String certNo, @Param("communityname") String communityname);

    List<AdminSimpleCardVo> adminManageCardList(@Param("admincode") String admincode, @Param("cardNo") String cardNo, @Param("isalive") Integer isalive);

    List<Map<String, Object>> selectAdminCardCount(@Param("codes") List<String> codes);

    List<Map<String, Object>> selectAdminOwnCards(@Param("codes") List<String> userCodes);
}
