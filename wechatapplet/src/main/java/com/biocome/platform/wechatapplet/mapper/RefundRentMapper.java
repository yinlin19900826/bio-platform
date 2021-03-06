package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.inter.basemanager.entity.Card;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**退租相关操作
 * @ClassName: RefundRentMapper
 * @Author: yinlin
 * @Date: 2019/8/1 11:57
 * @Description:
 */
public interface RefundRentMapper extends Mapper<Card> {


    Card selectCardByUserCode(@Param("usercode") String usercode);

    void deleteByUserName(@Param("physicalcardno") String physicalcardno,@Param("usercode") String usercode);

    List<String> getHouseCode(@Param("physicalcardno") String physicalcardno,@Param("usercode") String usercode);

    List<String> getAllUserCode(@Param("housecode") String housecode);

    void deleteAllByUserName(@Param("usercode") String usercode);


}