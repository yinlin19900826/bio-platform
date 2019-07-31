package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.inter.basemanager.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: CardMapper
 * @Author: shenlele
 * @Date: 2019/7/30 10:37
 * @Description:
 */
public interface CardVoMapper {

    /**
     * 根据用户编号查询所有卡
     *
     * @param usercode 用户编号
     * @return java.util.List<java.lang.String>
     * @Author shenlele
     * @Date 2019/7/30 10:51
     */
    List<String> selectCardsByCode(@Param("usercode") String usercode);


    /**
     * 根据
     *
     * @param isAlive
     * @param cardNo
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/30 14:49
     */
    void updateIsaliveByCardno(@Param("isAlive") int isAlive, @Param("cardNo") String cardNo);

    /**
     * 根据卡号查询卡信息
     *
     * @param cardNo 卡号
     * @return com.biocome.platform.inter.basemanager.entity.Card
     * @Author shenlele
     * @Date 2019/7/30 16:42
     */
    Card selectCardsByCardNo(@Param("cardNo") String cardNo);
}
