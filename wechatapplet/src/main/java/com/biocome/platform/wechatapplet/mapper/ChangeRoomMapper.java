package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.inter.basemanager.entity.Card;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**更改租户房间号
 * @ClassName: ChangeRoomMapper
 * @Author: yinlin
 * @Date: 2019/8/8 11:57
 * @Description:
 */
public interface ChangeRoomMapper extends Mapper<Card> {


    String getOriginBuildCode(@Param("physicalcardno") String physicalcardno,@Param("usercode") String usercode);

    String getOriginHouseCode(@Param("physicalcardno") String physicalcardno,@Param("usercode") String usercode);

    void changeRoom(@Param("originhousecode") String originhousecode, @Param("housecode") String housecode,
                    @Param("usercode") String username);
//buildname,buildcode,housecode,usercode
    void changeBuildRoom(@Param("buildname") String buildname,
                    @Param("buildcode") String buildcode,@Param("housecode") String housecode,
                         @Param("usercode") String usercode);

}