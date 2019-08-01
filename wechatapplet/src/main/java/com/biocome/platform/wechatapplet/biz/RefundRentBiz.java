package com.biocome.platform.wechatapplet.biz;


import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.inter.basemanager.biz.DeviceBiz;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.mapper.CardMapper;
import com.biocome.platform.inter.basemanager.vo.card.LogoutCardVo;
import com.biocome.platform.wechatapplet.mapper.CardManageMapper;
import com.biocome.platform.wechatapplet.mapper.CardVoMapper;
import com.biocome.platform.wechatapplet.mapper.RefundRentMapper;
import com.biocome.platform.wechatapplet.rpc.service.CardRpc;
import com.biocome.platform.wechatapplet.utils.RpcTokenUtil;
import com.biocome.platform.wechatapplet.utils.UriUtil;
import com.biocome.platform.wechatapplet.vo.card.CardManageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class RefundRentBiz extends BaseBiz<CardManageMapper, CardManageVo> {

    private Logger log = LoggerFactory.getLogger(RefundRentBiz.class);

    @Autowired
    private RefundRentMapper refundRentMapper;


    /**
     * 单个租户退租
     *
     * @param cardNo    卡号
     *@param username   租户名
     * @return java.lang.String
     * @Author yinlin
     * @Date 2019/8/1 11:19
     */
    public String refundRent(String cardNo, String username) throws Exception {
        try {
            refundRentMapper.deleteByUserName(username);
        }catch(Exception e ) {
            throw new Exception("单个租户退租失败");
        }
        return null;
    }

    /**
     * 全部租户退租
     *
     *
     * @param cardNo    卡号
     *@param username   租户名
     * @return java.lang.String
     * @Author yinlin
     * @Date 2019/7/31 11:19
     */
    public String refundAllRent(String cardNo, String username) throws Exception {

        return null;
    }
}
