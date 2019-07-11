package com.biocome.platform.admin.biz;

import com.biocome.platform.admin.entity.Card;
import com.biocome.platform.admin.vo.admin.AdminCardVo;
import com.biocome.platform.admin.vo.admin.AdminSimpleCardVo;
import com.biocome.platform.admin.vo.card.OpenblukVo;
import com.biocome.platform.admin.vo.device.CardDeviceVo;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.constant.CardStatusEnum;
import com.biocome.platform.admin.vo.admin.AdminCardBindVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biocome.platform.admin.entity.AdminCardBind;
import com.biocome.platform.admin.mapper.AdminCardBindMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        entity.setCardkind(card.getCardkind());
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
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "更新门禁卡失败，错误信息：数据库错误！");
        }
        //TODO(待调整) 下发卡、注销卡
        /*try{
            BaseRpcResponse rpcResp = null;
            if(ValidateUtils.isNotEmpty(bindVos)){
                List<String> codes = new ArrayList<String>();
                for(AdminCardBindVo bindVo : bindVos){
                    if (!bindVo.isBind()) {
                        codes.add(bindVo.getBuildCode());
                    }
                }
                rpcResp = cardBiz.deliverAdminCardNotify(card, codes);
            }
            if(ValidateUtils.isNotEmpty(removeVos)){
                List<String> codes = new ArrayList<String>();
                for(AdminCardBindVo bindVo : bindVos){
                    if (bindVo.isBind()) {
                        codes.add(bindVo.getBuildCode());
                    }
                }
                rpcResp = cardBiz.unregisterAdminCardNotify(card, codes);
            }
            if(ValidateUtils.isNotEmpty(rpcResp)){
                if(rpcResp.getErrorcode() != CommonConstants.RESP_RESULT_SUCCESS){
                    return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, rpcResp.getMessage());
                }
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "门禁卡下发、注销失败，失败原因："+e.getMessage());
        }*/
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
            doUnregister(vo.getUsercode(), vo.getCardNo());
        } catch (Exception e) {
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "注销卡失败！错误信息：数据库错误！");
        }
        //注销卡通知
        /*BaseRpcResponse rpcResp = cardBiz.unregisterAdminCardNotify(vo.getUsercode(), vo.getCardNo());
        if(rpcResp.getErrorcode() != CommonConstants.RESP_RESULT_SUCCESS){
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "通知注销卡失败！");
        }*/
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
            doRemove(vo.getUsercode(), vo.getCardNo());
            return new ObjectRestResponse<>().success();
        } catch (Exception e) {
            log.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "移除卡失败！错误信息：数据库错误！");
        }
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
                cardBiz.openbulk(openblukVo);
            }catch (Exception e){
                log.info(e.getMessage());
                return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "开卡失败！");
            }
        }
        return new ObjectRestResponse().success();
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
}