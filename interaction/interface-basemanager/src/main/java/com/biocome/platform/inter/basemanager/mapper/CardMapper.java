package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.LesseeCardMsgResp;
import com.biocome.platform.inter.basemanager.entity.Card;
/*import com.bicome.platform.inter.basemanager.vo.admin.AdminCardVo;
import com.bicome.platform.inter.basemanager.vo.admin.AdminSimpleCardVo;*/
import com.biocome.platform.inter.basemanager.vo.card.CardDetailVo;
import com.biocome.platform.inter.basemanager.vo.card.CardInfoResp;
import com.biocome.platform.inter.basemanager.vo.card.OpenblukResp;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListReq;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListResp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @auther: hxy
 * @create:2019/5/7 15:23
 */
public interface CardMapper extends Mapper<Card> {
    /**
     * @param cardno     物理卡号或逻辑卡号
     * @param cardType   卡类型
     * @param cardStatus 卡状态
     * @return
     */
    public List<CardInfoResp> selectByAdditionForCardList(@Param("cardno") String cardno, @Param("cardType") String cardType, @Param("cardStatus") String cardStatus, @Param("id") Integer id);

    /**
     * @param list 主键list
     * @return
     */
    public int deleteCard(@Param("list") List<Integer> list);

    /**
     * @param username   姓名
     * @param idNumber   证件编号
     * @param sex        性别
     * @param buildName  楼栋名称
     * @param estateName 小区名称
     * @return
     */
    public List<LesseeCardMsgResp> selectByAdditionForLesseeCardMsgPage(@Param("username") String username,
                                                                        @Param("idNumber") String idNumber,
                                                                        @Param("sex") Integer sex,
                                                                        @Param("buildName") String buildName,
                                                                        @Param("estateName") String estateName);

    /**
     *
     */
    public List<LesseecardListResp> selectLesseecardList(@Param("req") LesseecardListReq req);

    void updateCardStatus(@Param("isalive") int isalive, @Param("usercode") String usercode, @Param("cardNo") String cardNo);

    void removeCard(@Param("usercode") String usercode, @Param("cardNo") String cardNo);

    List<Map<String, Object>> selectAdminCardCount(@Param("codes") List<String> codes);

    List<Map<String, Object>> selectAdminOwnCards(@Param("codes") List<String> userCodes);

/*
    List<AdminSimpleCardVo> selectAdminCardList(@Param("admincode") String admincode, @Param("cardNo") String cardNo, @Param("isalive") Integer isalive);

    List<AdminCardVo> superCardList(@Param("username") String username, @Param("certNo") String certNo, @Param("communityname") String communityname);
*/

    List<CardDetailVo> selectCardDetail(@Param("cardNo") String cardNo);

    void updateIsaliveByCardno(@Param("isalive") int isalive, @Param("cardNo") String cardNo);

    void updateIsaliveByCardnoList(@Param("isalive") int isalive, @Param("list") List<OpenblukResp> list);

/*
    List<AdminSimpleCardVo> adminManageCardList(@Param("admincode") String admincode, @Param("cardNo") String cardNo, @Param("isalive") Integer isalive);
*/
}
