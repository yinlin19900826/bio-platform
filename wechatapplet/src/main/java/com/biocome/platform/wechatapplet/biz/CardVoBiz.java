package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.biz.DeviceBiz;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.mapper.CardMapper;
import com.biocome.platform.inter.basemanager.mapper.LesseeMapper;
import com.biocome.platform.inter.basemanager.vo.card.CardSnVo;
import com.biocome.platform.inter.basemanager.vo.card.LogoutCardVo;
import com.biocome.platform.inter.basemanager.vo.card.OpenCardVo;
import com.biocome.platform.wechatapplet.mapper.CardVoMapper;
import com.biocome.platform.wechatapplet.rpc.service.CardRpc;
import com.biocome.platform.wechatapplet.utils.RpcTokenUtil;
import com.biocome.platform.wechatapplet.utils.UriUtil;
import com.biocome.platform.wechatapplet.vo.common.CommonRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CardVoBiz
 * @Author: shenlele
 * @Date: 2019/7/30 10:36
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardVoBiz {

    private final CardRpc rpc;
    private final UriUtil uriUtil;
    private final DeviceBiz deviceBiz;
    private final CardMapper cardMapper;
    private final LesseeMapper lesseeMapper;
    private final CardVoMapper cardVoMapper;
    private final RpcTokenUtil rpcTokenUtil;

    @Autowired
    public CardVoBiz(CardMapper cardMapper, CardVoMapper cardVoMapper, CardRpc rpc, LesseeMapper lesseeMapper,
                     UriUtil uriUtil, DeviceBiz deviceBiz, RpcTokenUtil rpcTokenUtil) {
        this.rpc = rpc;
        this.uriUtil = uriUtil;
        this.deviceBiz = deviceBiz;
        this.cardMapper = cardMapper;
        this.cardVoMapper = cardVoMapper;
        this.lesseeMapper = lesseeMapper;
        this.rpcTokenUtil = rpcTokenUtil;
    }

    /**
     * 根据用户编号查询所有卡
     *
     * @param usercode 用户编号
     * @return com.biocome.platform.wechatapplet.vo.card.CardVo
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/30 11:11
     */
    public CommonRes selectCardsByCode(String usercode) throws Exception {
        List<String> list = cardVoMapper.selectCardsByCode(usercode);
        return new CommonRes().success(list);
    }

    /**
     * 管理员下发用户卡，下发时先查询用户是否在该楼栋有卡，如有则先注销当前卡，再下发，下发是从管理员处将卡转移到用户下
     *
     * @param userCode  用户编号
     * @param cardNo    卡号
     * @param buildCode 楼栋编号
     * @return java.lang.String
     * @Author shenlele
     * @Date 2019/7/30 11:19
     */
    public String cardOperation(String userCode, String cardNo, String buildCode) throws Exception {
        //查询卡信息，因为这张卡页面展示是后台查出来的，所以数据库绝对有这条数据，不用判断
        Card model = cardVoMapper.selectCardsByCardNo(cardNo);
        Device device = new Device();
        device.setSn(model.getSn());
        //根据sn获取对应设备信息和厂家编号
        List<Device> devices = deviceBiz.selectList(device);
        if (devices.size() > 0) {
            LogoutCardVo logoutCardVo = new LogoutCardVo();
            //根据厂家编号获取URI
            Device dev = devices.get(0);
            URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
            if (uriByBrand != null) {
                //获取token
                String token = rpcTokenUtil.getRpcToken(dev.getBrand());
                //查询当前楼栋下有无租户卡信息，如有则注销
                Card card = new Card();
                card.setUsercode(userCode);
                card.setBuildcode(buildCode);
                card.setIsalive("1");
                List<Card> list = cardMapper.select(card);
                if (ValidateUtils.isNotEmpty(list)) {
                    for (Card card1 : list) {
                        //获取对应厂家token
                        logoutCardVo.setToken(token);
                        logoutCardVo.setCardno(card1.getPhysicalCardno());
                        logoutCardVo.setCardtype(card1.getCardtype());
                        logoutCardVo.setUsercode(userCode);
                        List<CardSnVo> sns = new ArrayList<>();
                        CardSnVo cardSnVo = new CardSnVo();
                        cardSnVo.setSn(card1.getSn());
                        logoutCardVo.setSnList(sns);
                        BaseRpcResponse res = rpc.logoutCard(uriByBrand, logoutCardVo);
                        if (CommonConstants.RESP_RESULT_SUCCESS.equals(res.getResult())) {
                            //修改表该卡注销
                            card1.setIsalive(AdminCommonConstant.DEFAULT_ZERO);
                            cardMapper.updateByPrimaryKey(model);
                        } else {
                            throw new Exception("请求小平台注销接口失败," + JsonUtils.beanToJson(res));
                        }
                    }
                }
                Lessee lessee = new Lessee();
                lessee.setUsercode(userCode);
                lessee = lesseeMapper.selectOne(lessee);
                model.setUsercode(userCode);
                model.setUsername(lessee.getUsername());
                model.setPhone(lessee.getTel());
                model.setHousecode(lessee.getHousecode());
                //将卡类型设为租户卡
                model.setCardtype(AdminCommonConstant.DEFAULT_ONE);
                model.setIsalive(AdminCommonConstant.DEFAULT_ONE);
                //将该卡信息更新为住户的信息，设置为发卡状态
                cardMapper.updateByPrimaryKey(model);
                OpenCardVo vo = new OpenCardVo();
                vo.setToken(token);
                vo.setUsercode(userCode);
                vo.setCardno(model.getLogicCardno());
                vo.setCardtype(model.getCardtype());
                List<String> sns = new ArrayList<>();
                sns.add(model.getSn());
                //下发卡
                BaseRpcResponse res = rpc.openCard(uriByBrand, vo);
                if (CommonConstants.RESP_RESULT_SUCCESS.equals(res.getResult())) {
                    //修改表该卡有效
                    model.setIsalive(AdminCommonConstant.DEFAULT_TWO);
                    cardMapper.updateByPrimaryKey(model);
                    return AdminCommonConstant.BOOLEAN_NUMBER_TRUE;
                } else {
                    throw new Exception("请求小平台发卡接口失败,"+ JsonUtils.beanToJson(res));
                }
            } else {
                throw new Exception("设备品牌对应URI获取异常");
            }
        } else {
            throw new Exception("设备不存在");
        }
    }
}
