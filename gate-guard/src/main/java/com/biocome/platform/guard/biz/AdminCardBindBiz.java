package com.biocome.platform.guard.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.entity.AdminCardBind;
import com.biocome.platform.guard.mapper.AdminCardBindMapper;
import com.biocome.platform.guard.vo.admin.AdminCardBindVo;
import com.biocome.platform.guard.vo.admin.AdminCardVo;
import com.biocome.platform.guard.vo.admin.AdminSimpleCardVo;
import com.biocome.platform.inter.basemanager.biz.BuildBiz;
import com.biocome.platform.inter.basemanager.biz.CardBiz;
import com.biocome.platform.inter.basemanager.biz.DeviceBiz;
import com.biocome.platform.inter.basemanager.biz.LandlordBiz;
import com.biocome.platform.inter.basemanager.constant.CardStatusEnum;
import com.biocome.platform.inter.basemanager.constant.CardTypeEnum;
import com.biocome.platform.inter.basemanager.entity.Build;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.biocome.platform.inter.basemanager.vo.card.*;
import com.biocome.platform.inter.basemanager.vo.device.CardDeviceVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * 管理员卡
 *
 * @author zengqiang
 * @email 360713542@qq.com
 * @date 2019-05-17 10:06:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminCardBindBiz extends BaseBiz<AdminCardBindMapper,AdminCardBind> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CardBiz cardBiz;
    @Autowired
    BuildBiz buildBiz;
    @Autowired
    DeviceBiz deviceBiz;
    @Autowired
    LandlordBiz landlordBiz;
    @Autowired
    DoorDeviceCardBiz doorDeviceCardBiz;

    /***
     * 查找管理员卡和楼栋绑定列表
     * @param usercode 管理员编码
     * @param cardNo 门禁卡物理编号
     * @param pageSize
     * @param pageNo
     * @return
     */
    public TableResultResponse<AdminCardBindVo> cardBindBuildingList(String usercode, String cardNo, int pageSize, int pageNo) {
        try {
            Page<AdminCardBindVo> res = PageHelper.startPage(pageNo, pageSize);
            mapper.cardBindBuildingList(usercode, cardNo);
            return new TableResultResponse<>(res.getTotal(), res.getResult());
        } catch (Exception e) {
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查找门禁卡楼栋列表失败，错误信息：数据库错误！");
        }
    }

    /***
     * 下发门禁卡
     * @param usercode 用户编码
     * @param cardNo 门禁卡物理卡号
     * @param cardVos 更新列表
     * @return
     */
    public ObjectRestResponse bindBuildingCard(String usercode, String cardNo, List<AdminCardBindVo> cardVos) {
        List<AdminCardBindVo> bindVos = new ArrayList<AdminCardBindVo>();
        List<AdminCardBindVo> removeVos = new ArrayList<AdminCardBindVo>();
        Card card = cardBiz.selectByPhysicalCardNo(cardNo);
        try {
            if(ValidateUtils.isNotEmpty(cardVos)){
                for(AdminCardBindVo vo : cardVos){
                    if(vo.isBind() && ValidateUtils.isNotEmpty(vo.getId())){
                        removeVos.add(vo);
                    }else{
                        bindVos.add(vo);
                    }
                }
            }
            if (ValidateUtils.isEmpty(card)) {
                new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "未知的门禁卡!门禁卡号：" + cardNo);
            }
            //1.通知
            try{
                BaseRpcResponse rpcResp = null;
                if(ValidateUtils.isNotEmpty(bindVos)){
                    List<String> codes = new ArrayList<String>();
                    for(AdminCardBindVo bindVo : bindVos){
                        if (!bindVo.isBind()) {
                            codes.add(bindVo.getBuildCode());
                        }
                    }
                    if(ValidateUtils.isNotEmpty(codes)){
                        rpcResp = deliverAdminCardNotify(card, codes);
                    }
                }
                if(ValidateUtils.isNotEmpty(removeVos)){
                    List<String> codes = new ArrayList<String>();
                    for(AdminCardBindVo bindVo : bindVos){
                        if (bindVo.isBind()) {
                            codes.add(bindVo.getBuildCode());
                        }
                    }
                    if(ValidateUtils.isNotEmpty(codes)){
                        rpcResp = unregisterAdminCardNotify(card, codes);
                    }
                }
                if(ValidateUtils.isNotEmpty(rpcResp)){
                    if(rpcResp.getErrorcode() != CommonConstants.RESP_RESULT_SUCCESS){
                        return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, rpcResp.getMessage());
                    }
                }
            }catch (Exception e){
                log.info(e.getMessage());
                return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "门禁卡下发、注销失败，失败原因："+e.getMessage());
            }
            //2.数据库通知
            if (ValidateUtils.isNotEmpty(bindVos)) {
                List<AdminCardBind> bindList = new ArrayList<AdminCardBind>();
                for (AdminCardBindVo bindVo : bindVos) {
                    if (!bindVo.isBind()) {
                        AdminCardBind entity = new AdminCardBind();
                        entity.setLogicCardno(card.getLogicCardno());
                        entity.setPhysicalCardno(cardNo);
                        entity.setUsercode(usercode);
                        entity.setCreatetime(card.getCreatetime());
                        entity.setIsalive(Integer.parseInt(card.getIsalive()));
                        entity.setCardkind(Integer.parseInt(card.getCardkind()));
                        entity.setCardtype(card.getCardtype());
                        entity.setBuildCode(bindVo.getBuildCode());
                        entity.setBuildName(bindVo.getBuildName());
                        bindList.add(entity);
                    }
                }
                mapper.batchInsert(bindList);
            }
            if (ValidateUtils.isNotEmpty(removeVos)) {
                List<Integer> ids = new ArrayList<Integer>();
                for (AdminCardBindVo removeVo : removeVos) {
                    if (removeVo.isBind()) {
                        ids.add(removeVo.getId());
                    }
                }
                mapper.batchDeleteByIdsAndUsercode(ids, usercode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "更新门禁卡失败，错误信息：数据库错误！");
        }
        return new ObjectRestResponse<>().success();
    }

    /**
     * 注销卡
     *
     * @param vo
     * @return
     */
    public ObjectRestResponse<AdminCardBind> unregisterCard(AdminCardVo vo) {
        try {
            //注销卡通知
            BaseRpcResponse rpcResp = unregisterAdminCardNotify(vo.getUsercode(), vo.getCardNo());
            if(rpcResp.getErrorcode() != CommonConstants.RESP_RESULT_SUCCESS){
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "注销卡失败！失败原因："+ rpcResp.getMessage());
            }
            doUnregister(vo.getUsercode(), vo.getCardNo());
        } catch (Exception e) {
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "注销卡失败！错误信息：数据库错误！");
        }
        return new ObjectRestResponse<>().success();
    }

    /**
     *
     * 注销管理员门禁卡
     *
     * @param admincode
     * @param physicalNo
     */
    private void doUnregister(String admincode, String physicalNo) {
        cardBiz.unregisteredCard(Integer.parseInt(CardStatusEnum.LOGOUT.getCardStatusCode()),admincode, physicalNo);
        mapper.updateCardStatus(Integer.parseInt(CardStatusEnum.LOGOUT.getCardStatusCode()),admincode, physicalNo);
    }

    /**
     * 移除卡
     *
     * @param vo
     * @return
     */
    public ObjectRestResponse<AdminCardBind> removeCard(AdminCardVo vo) {
        try {
            //注销卡通知
            BaseRpcResponse rpcResp = unregisterAdminCardNotify(vo.getUsercode(), vo.getCardNo());
            if(rpcResp.getErrorcode() != CommonConstants.RESP_RESULT_SUCCESS){
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "移除卡失败！失败原因："+ rpcResp.getMessage());
            }
            doRemove(vo.getUsercode(), vo.getCardNo());
        } catch (Exception e) {
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "移除卡失败！错误信息：数据库错误！");
        }
        return new ObjectRestResponse<>().success();
    }

    /**
     * 移除卡
     *
     * @param usercode
     * @param cardNo
     */
    private void doRemove(String usercode, String cardNo) {
        cardBiz.removeCard(usercode, cardNo);
        mapper.removeCard(usercode, cardNo);
    }

    /**
     * 楼栋所有管理员门禁卡
     * @param buildcode
     * @param pageSize
     * @param pageNo
     * @return
     */
    public TableResultResponse<AdminCardVo> adminCardListOnBuilding(String buildcode, int pageSize, int pageNo) {
        try {
            Page<AdminCardVo> res = PageHelper.startPage(pageNo, pageSize);
            mapper.adminCardListOnBuilding(buildcode);
            return new TableResultResponse<>(res.getTotal(), res.getResult());
        }catch (Exception e){
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询所有管理员卡失败，错误信息：数据库错误！");
        }
    }

    /***
     * 管理员卡批量下发
     * @param list
     * @return
     */
    public ObjectRestResponse batchDeliverAdminCardNotify(List<AdminSimpleCardVo> list) {
        if(ValidateUtils.isNotEmpty(list)){
            List<String> cardNoList = new ArrayList<String>();
            for(AdminSimpleCardVo cardVo : list){
                cardNoList.add(cardVo.getCardNo());
            }
            List<CardDeviceVo> voList = cardDeviceList(cardNoList);
            Map<String, CardDeviceVo> map = new HashMap<String, CardDeviceVo>();
            for(CardDeviceVo vo : voList){
                map.put(vo.getCardNo(), vo);
            }
            List<OpenblukVo.OpenblukInVo> bulkInVos = new ArrayList<OpenblukVo.OpenblukInVo>();
            for(AdminSimpleCardVo cardVo : list){
                OpenblukVo.OpenblukInVo vo = new OpenblukVo.OpenblukInVo();
                CardDeviceVo cardDeviceVo = map.get(cardVo.getCardNo());
                if(ValidateUtils.isNotEmpty(cardDeviceVo)){
                    vo.setCardno(cardVo.getCardNo());
                    vo.setUsercode(cardVo.getUsercode());
                    vo.setCardtype(String.valueOf(cardDeviceVo.getCardtype()));
                    vo.setListsn(cardDeviceVo.getSnList());
                    bulkInVos.add(vo);
                }else{
                    return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "未知的卡号及设备信息！卡号："+cardVo.getCardNo());
                }
            }
            OpenblukVo openblukVo = new OpenblukVo();
            openblukVo.setList(bulkInVos);
            try{
                doorDeviceCardBiz.openbulk(openblukVo);
            }catch (Exception e){
                log.info(e.getMessage());
                return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "开卡失败！");
            }
        }
        return new ObjectRestResponse().success();
    }

    /**
     * 批量获取管理员管理卡的数量
     *
     * @param userCodes
     * @return
     */
    public List<Map<String, Object>> getAdminCardCount(List<String> userCodes) {
        return mapper.selectAdminCardCount(userCodes);
    }

    /**
     * 批量获取管理员自用门禁卡
     *
     * @param userCodes
     * @return
     */
    public List<Map<String, Object>> getAdminOwnCards(List<String> userCodes) {
        return mapper.selectAdminOwnCards(userCodes);
    }
    /**
     * 查找管理员卡的设备关联号列表
     * @param cardNoList
     * @return
     */
    public List<CardDeviceVo> cardDeviceList(List<String> cardNoList) {
        return mapper.cardDeviceList(cardNoList);
    }

    /***
     * 管理员卡批量注销(暂时不做)
     * @param list
     * @return
     */
    public BaseRpcResponse batchUnregisterAdminCard(List<AdminSimpleCardVo> list) {
        return null;
    }

    /**
     * 管理员卡列表
     *
     * @param admincode
     * @param cardNo
     * @param isalive
     * @param pageSize
     * @param pageNo
     * @return
     */
    public TableResultResponse<AdminSimpleCardVo> adminCardList(String admincode, String cardNo, Integer isalive,
                                                                int pageSize, int pageNo) {
        try {
            Page<AdminSimpleCardVo> res = PageHelper.startPage(pageNo, pageSize);
            mapper.selectAdminCardList(admincode, cardNo, isalive);
            return new TableResultResponse<>(res.getTotal(), res.getResult());
        } catch (Exception e) {
            log.info(e.getStackTrace().toString());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询管理员门禁卡失败，错误信息：数据库错误！");
        }
    }

    /***
     * 管理员管理的卡
     * @param admincode
     * @param cardNo
     * @param isalive
     * @param pageSize
     * @param pageNo
     * @return
     */
    public TableResultResponse<AdminSimpleCardVo> adminManageCardList(String admincode, String cardNo, Integer isalive, int pageSize, int pageNo) {
        try {
            Page<AdminSimpleCardVo> res = PageHelper.startPage(pageNo, pageSize);
            mapper.adminManageCardList(admincode, cardNo, isalive);
            return new TableResultResponse<>(res.getTotal(), res.getResult());
        } catch (Exception e) {
            log.info(e.getStackTrace().toString());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询管理员门禁卡失败，错误信息：数据库错误！");
        }
    }

    /**
     * 所有管理卡（通卡）
     *
     *
     * @param username
     * @param certNo
     * @param communityname
     * @param pageSize
     * @param pageNo
     * @return
     */
    public TableResultResponse<AdminCardVo> superCardList(String username, String certNo, String communityname, int pageSize, int pageNo) {
        try {
            Page<AdminCardVo> res = PageHelper.startPage(pageNo, pageSize);
            mapper.superCardList(username, certNo, communityname);
            return new TableResultResponse<>(res.getTotal(), res.getResult());
        } catch (Exception e) {
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询所有管理卡失败，错误信息：数据库错误！");
        }
    }

    /**
     * 增加卡
     * @param param
     * @return
     */
    public ObjectRestResponse addCard(AddCardParam param) throws Exception{
        if(ValidateUtils.isEmpty(param)){
            log.info("请求参数不能为空！");
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE,"请求参数不能为空！");
        }
        Card card = cardBiz.selectByPhysicalCardNo(param.getCardNo());
        if(ValidateUtils.isNotEmpty(card)){
            deleteById(card.getId());
        }
        card = new Card();
        BeanUtils.copyProperties(param,card);
        card.setPhysicalCardno(param.getCardNo());
        card.setCreatetime(new Date());
        card.setIsalive(com.biocome.platform.inter.basemanager.constant.CardStatusEnum.PUBLISHING.getCardStatusCode());
        card.setLogicCardno(param.getLogicCardno());
        if(ValidateUtils.isNotEmpty(param.getBuildcode())){
            Build build = buildBiz.selectByBuildcode(param.getBuildcode());
            if(ValidateUtils.isEmpty(build)){
                log.info("未知的楼栋，楼栋编码："+param.getBuildcode());
                return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE,"未知的楼栋，楼栋编码："+param.getBuildcode());
            }
            card.setBuildname(build.getBuildname());
        }
        if(param.getCardtype() != CardTypeEnum.NORMAL.getValue()){
            //设置管理员卡信息
            Landlord landlord = landlordBiz.selectByUserCode(param.getUsercode());
            if(ValidateUtils.isEmpty(landlord)){
                log.info("未知的管理员，管理员编码："+param.getAdmincode());
                return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE,"未知的管理员，管理员编码："+param.getAdmincode());
            }
            card.setUsername(landlord.getUsername());
            card.setPhone(landlord.getTel());
            card.setRemark(landlord.getRemark());
        }
        cardBiz.insert(card);
        return new ObjectRestResponse().success();
    }

    /***
     * 管理员发卡通知(一张卡关联多个楼栋)
     * @param card
     * @return
     */
    public BaseRpcResponse deliverAdminCardNotify(Card card, List<String> codes) {
        OpenCardVo openVo = new OpenCardVo();
        //openVo.setToken();
        openVo.setUsercode(card.getUsercode());
        openVo.setCardno(card.getPhysicalCardno());
        openVo.setCardtype(card.getCardtype());
        List<CardSnVo> list = deviceBiz.selectSnByBuildCodes(codes);
        openVo.setList(list);
        return doorDeviceCardBiz.openCard(openVo);
    }

    /***
     * 管理员注销卡通知
     * @param card
     * @return
     */
    public BaseRpcResponse unregisterAdminCardNotify(Card card, List<String> codes) {
        LogoutCardVo logoutVo = new LogoutCardVo();
        //openVo.setToken();
        logoutVo.setUsercode(card.getUsercode());
        logoutVo.setCardno(card.getPhysicalCardno());
        logoutVo.setCardtype(card.getCardtype());
        List<CardSnVo> list = deviceBiz.selectSnByBuildCodes(codes);
        logoutVo.setSnList(list);
        return doorDeviceCardBiz.logoutCard(logoutVo);
    }

    public BaseRpcResponse unregisterAdminCardNotify(String usercode, String cardNo) {
        LogoutCardVo logoutCardVo = new LogoutCardVo();
        Card card = cardBiz.selectByPhysicalCardNo(cardNo);
        List<String> cardNoList = new ArrayList<>();
        cardNoList.add(cardNo);
        List<CardDeviceVo> voList = cardDeviceList(cardNoList);
        if(ValidateUtils.isNotEmpty(voList)){
            Map<String, CardDeviceVo> map = new HashMap<String, CardDeviceVo>();
            for(CardDeviceVo vo : voList){
                map.put(vo.getCardNo(), vo);
            }
            logoutCardVo.setUsercode(usercode);
            logoutCardVo.setCardno(cardNo);
            logoutCardVo.setCardtype(String.valueOf(map.get(cardNo).getCardtype()));
            logoutCardVo.setSnList(map.get(cardNo).getSnList());
        }else{
            return new BaseRpcResponse("此楼栋没有门禁机信息", "0", "204");
        }
        return doorDeviceCardBiz.logoutCard(logoutCardVo);
    }

}