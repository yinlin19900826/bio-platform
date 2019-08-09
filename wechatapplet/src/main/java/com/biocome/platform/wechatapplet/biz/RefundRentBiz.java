package com.biocome.platform.wechatapplet.biz;


import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.wechatapplet.mapper.RefundRentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RefundRentBiz
 * @Author: yinlin
 * @Date: 2019/8/1 19:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RefundRentBiz extends BaseBiz<RefundRentMapper, Card> {

    private Logger log = LoggerFactory.getLogger(RefundRentBiz.class);

    @Autowired
    private RefundRentMapper refundRentMapper;


    /**
     * 单个租户退租
     *
     *
     *@param usercode  租户身份编码
     * @return java.lang.String
     * @Author yinlin
     * @Date 2019/8/1 11:19
     */
    public ObjectRestResponse refundRent(String physicalcardno, String usercode) throws Exception {
        try {
            refundRentMapper.deleteByUserName(physicalcardno,usercode);
            return new ObjectRestResponse().success();
        }catch(Exception e ) {
            throw new Exception("单个租户退租失败");
        }
    }

    /**
     * 全部租户退租
     *
     *
     * @param
     *@param usercode   租户身份编码
     * @return java.lang.String
     * @Author yinlin
     * @Date 2019/7/31 11:19
     */
    public ObjectRestResponse refundAllRent(String physicalcardno, String usercode) throws Exception {
        try {
            List<String> housecodes = refundRentMapper.getHouseCode(physicalcardno,usercode);
            List<String> allUserCodeList = new ArrayList<>();
            for(String housecode:housecodes){
                List<String> usercodeList = refundRentMapper.getAllUserCode(housecode);
                allUserCodeList.addAll(usercodeList);

            }

            for(String userCode:allUserCodeList){
                refundRentMapper.deleteAllByUserName(userCode);
            }
            return new ObjectRestResponse().success();
        }catch(Exception e ) {
            throw new Exception("全部租户退租失败");
        }
    }
}
