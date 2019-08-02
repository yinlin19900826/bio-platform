package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.wechatapplet.vo.card.CardManageVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**退租相关操作
 * @ClassName: RefundRentMapper
 * @Author: yinlin
 * @Date: 2019/8/1 11:57
 * @Description:
 */
public interface RefundRentMapper extends Mapper<CardManageVo> {

    void deleteByUserName(@Param("username") String username);

    List<String> getHouseCode(@Param("username") String username);

    List<String> getAllUserName(@Param("housecode") String housecode);

    void deleteAllByUserName(@Param("username") String username);


}