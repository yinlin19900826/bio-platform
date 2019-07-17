package com.biocome.platform.guard.mapper;

import com.biocome.platform.inter.basemanager.LesseeCardMsgResp;
import com.biocome.platform.inter.basemanager.vo.card.OpenblukResp;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListReq;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/11 18:03
 */
public interface DoorDeviceCardMapper {
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

    void updateIsaliveByCardno(@Param("isalive") int isalive, @Param("cardNo") String cardNo);

    public List<LesseecardListResp> selectLesseecardList(@Param("req") LesseecardListReq req);

    void updateIsaliveByCardnoList(@Param("isalive") int isalive, @Param("list") List<OpenblukResp> list);
}
