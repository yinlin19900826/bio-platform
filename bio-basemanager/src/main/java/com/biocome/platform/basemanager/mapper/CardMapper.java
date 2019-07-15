package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.Card;
import com.biocome.platform.basemanager.vo.resp.card.CardInfoResp;
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

}
