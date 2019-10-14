package com.biocome.platform.wechatapplet.biz;


import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
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


    private final CardRpc rpc;
    private final UriUtil uriUtil;
    private final DeviceBiz deviceBiz;
    private final CardMapper cardMapper;
    private final RpcTokenUtil rpcTokenUtil;

    public RefundRentBiz(CardRpc rpc, UriUtil uriUtil, DeviceBiz deviceBiz, CardMapper cardMapper, RpcTokenUtil rpcTokenUtil) {
        this.rpc = rpc;
        this.uriUtil = uriUtil;
        this.deviceBiz = deviceBiz;
        this.cardMapper = cardMapper;
        this.rpcTokenUtil = rpcTokenUtil;
    }

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
            if("".equals(physicalcardno)||physicalcardno.isEmpty()
                    ||null == physicalcardno
                    ||"".equals(usercode)||usercode.isEmpty()
                    ||null == usercode
            ){
               // throw new Exception("传入的参数存在空值");
                return new ObjectRestResponse().customError("传入的参数存在空值!");
               // return new ObjectRestResponse().customError("单个租户退租失败!");

            }

            Card model = refundRentMapper.selectCardByUserCode(usercode);
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
                    logoutCardVo.setCardno(model.getLogicCardno());
                    logoutCardVo.setCardtype(model.getCardtype());
                    List<String> sns = new ArrayList<>();
                    sns.add(model.getSn());
                    BaseRpcResponse res = rpc.logoutCard(uriByBrand, logoutCardVo);
                    if (CommonConstants.RESP_RESULT_SUCCESS.equals(res.getResult())) {
                        //修改表该卡注销
                        model.setIsalive(AdminCommonConstant.DEFAULT_ZERO);
                        cardMapper.updateByPrimaryKey(model);
                    }else{
                        throw new Exception("请求小平台注销接口失败");
                    }
                }else{
                    throw new Exception("设备品牌对应URI获取异常");
                }
            }else{
                throw new Exception("设备不存在");
            }

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
            if("".equals(physicalcardno)||physicalcardno.isEmpty()
                    ||null == physicalcardno
                    ||"".equals(usercode)||usercode.isEmpty()
                    ||null == usercode){
               // throw new Exception("传入的参数存在空值");
                 return new ObjectRestResponse().customError("传入的参数存在空值!");

            }
            List<String> housecodes = refundRentMapper.getHouseCode(physicalcardno,usercode);
            List<String> allUserCodeList = new ArrayList<>();
            for(String housecode:housecodes){
                List<String> usercodeList = refundRentMapper.getAllUserCode(housecode);
                allUserCodeList.addAll(usercodeList);

            }

            for(String userCode:allUserCodeList){
                Card model = refundRentMapper.selectCardByUserCode(usercode);
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
                        logoutCardVo.setCardno(model.getLogicCardno());
                        logoutCardVo.setCardtype(model.getCardtype());
                        List<String> sns = new ArrayList<>();
                        sns.add(model.getSn());
                        BaseRpcResponse res = rpc.logoutCard(uriByBrand, logoutCardVo);
                        if (CommonConstants.RESP_RESULT_SUCCESS.equals(res.getResult())) {
                            //修改表该卡注销
                            model.setIsalive(AdminCommonConstant.DEFAULT_ZERO);
                            cardMapper.updateByPrimaryKey(model);
                        }else{
                            throw new Exception("请求小平台注销接口失败");
                        }
                    }else{
                        throw new Exception("设备品牌对应URI获取异常");
                    }
                }else{
                    throw new Exception("设备不存在");
                }
                refundRentMapper.deleteAllByUserName(userCode);
            }
            return new ObjectRestResponse().success();
        }catch(Exception e ) {
            throw new Exception("全部租户退租失败");
        }
    }
}
