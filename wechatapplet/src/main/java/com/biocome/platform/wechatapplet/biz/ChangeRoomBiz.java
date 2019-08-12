package com.biocome.platform.wechatapplet.biz;


import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.wechatapplet.mapper.CardManageMapper;
import com.biocome.platform.wechatapplet.mapper.ChangeRoomMapper;
import com.biocome.platform.wechatapplet.mapper.RefundRentMapper;
import com.biocome.platform.wechatapplet.vo.common.ChangeRoomVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ChangeRoomBiz
 * @Author: yinlin
 * @Date: 2019/8/8 15:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChangeRoomBiz extends BaseBiz<ChangeRoomMapper, Card> {

    private Logger log = LoggerFactory.getLogger(ChangeRoomBiz.class);

    @Autowired
    private ChangeRoomMapper changeRoomMapper;


    /**
     * 更改租户房间号
     *
     *
     *@param
     * @return java.lang.String
     * @Author yinlin
     * @Date 2019/8/1 11:19
     */
    public ObjectRestResponse changeRoom(ChangeRoomVo changeRoomVo) throws Exception {
        try {
            String usercode = changeRoomVo.getUsercode();
            String physicalcardno = changeRoomVo.getPhysicalcardno();
            String buildname = changeRoomVo.getBuildname();
            String buildcode = changeRoomVo.getBuildcode();
            String housecode = changeRoomVo.getHousecode();
            if("".equals(physicalcardno)||physicalcardno.isEmpty()
                    ||null == physicalcardno
                    ||"".equals(usercode)||usercode.isEmpty()
                    ||null == usercode
                ||"".equals(buildname)||buildname.isEmpty()
                    ||null == buildname
                    ||"".equals(buildcode)||buildcode.isEmpty()
                    ||null == buildcode
                ||"".equals(housecode)||housecode.isEmpty()
                    ||null == housecode){
                // throw new Exception("传入的参数存在空值");
                return new ObjectRestResponse().customError("传入的参数存在空值!");

            }
            String originbuildcode = changeRoomMapper.getOriginBuildCode(physicalcardno,usercode);
            String originhousecode = changeRoomMapper.getOriginHouseCode(physicalcardno,usercode);

            if(buildcode.equals(originbuildcode)){

                changeRoomMapper.changeRoom(originhousecode,housecode,usercode);
            }else{
                changeRoomMapper.changeBuildRoom(buildname,buildcode,housecode,usercode);
            }

            return new ObjectRestResponse().success();
        }catch(Exception e ) {
            throw new Exception("更改租户房间号失败");
        }
    }

}
