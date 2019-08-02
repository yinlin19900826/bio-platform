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
import com.biocome.platform.wechatapplet.rpc.service.CardRpc;
import com.biocome.platform.wechatapplet.utils.RpcTokenUtil;
import com.biocome.platform.wechatapplet.utils.UriUtil;
import com.biocome.platform.wechatapplet.vo.common.CardManageVo;
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
 * @ClassName: CardManageBiz
 * @Author: yinlin
 * @Date: 2019/7/31 19:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardManageBiz extends BaseBiz<CardManageMapper, CardManageVo> {

    private Logger log = LoggerFactory.getLogger(CardManageBiz.class);

    @Autowired
    private CardManageMapper cardManageMapper;
    private final CardRpc rpc;
    private final UriUtil uriUtil;
    private final DeviceBiz deviceBiz;
    private final CardMapper cardMapper;
    private final CardVoMapper cardVoMapper;
    private final RpcTokenUtil rpcTokenUtil;

    public CardManageBiz(CardRpc rpc, UriUtil uriUtil, DeviceBiz deviceBiz, CardMapper cardMapper, CardVoMapper cardVoMapper, RpcTokenUtil rpcTokenUtil) {
        this.rpc = rpc;
        this.uriUtil = uriUtil;
        this.deviceBiz = deviceBiz;
        this.cardMapper = cardMapper;
        this.cardVoMapper = cardVoMapper;
        this.rpcTokenUtil = rpcTokenUtil;
    }

    /**
     * 获取不同权限用户下的所有门禁卡
     *
     * @return
     * @Author yinlin
     * @Date 2019/7/31 14:08
     */
    public TableResultResponse<CardManageVo> selectByAttribute(int pageSize, int pageNum, String username) {
        TableResultResponse<CardManageVo> res;
        try {
            Page<CardManageVo> result = PageHelper.startPage(pageNum, pageSize);
            cardManageMapper.selectByUserName(username);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("获取不同权限用户下的所有门禁卡失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "获取不同权限用户下的所有门禁卡失败!");
        }
        return res;
    }

    /**
     * 挂失卡操作
     *
     * @param userCode  用户编号
     * @param cardNo    卡号
     * @param buildCode 楼栋编号
     * @return java.lang.String
     * @Author yinlin
     * @Date 2019/7/31 11:19
     */
    public String cardLossOperation(String userCode, String cardNo, String buildCode) throws Exception {
        Card model = cardManageMapper.selectCardByCardNo(cardNo);
        if (model.getCardtype().equals("1")) {
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
                    logoutCardVo.setToken(token);
                    logoutCardVo.setCardno(model.getPhysicalCardno());
                    logoutCardVo.setCardtype(model.getCardtype());
                    List<String> sns = new ArrayList<>();
                    sns.add(model.getSn());
                    BaseRpcResponse res = rpc.logoutCard(uriByBrand, logoutCardVo);
                    if (CommonConstants.RESP_RESULT_SUCCESS.equals(res.getResult())) {
                        //修改表该卡注销
                        model.setIsalive(AdminCommonConstant.DEFAULT_ZERO);
                        cardMapper.updateByPrimaryKey(model);


                    } else {
                        throw new Exception("请求小平台注销接口失败");
                    }
                }

            }

        }
        return AdminCommonConstant.BOOLEAN_NUMBER_TRUE;
    }
}
