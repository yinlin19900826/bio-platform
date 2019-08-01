package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.inter.basemanager.entity.Build;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.wechatapplet.entity.Fault;
import com.biocome.platform.wechatapplet.vo.card.CardManageVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: CardManageMapper
 * @Author: yinlin
 * @Date: 2019/7/31 11:57
 * @Description:
 */
public interface CardManageMapper extends Mapper<CardManageVo> {
    List<Card> selectByUserName(@Param("username") String username);


}