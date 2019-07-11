package com.biocome.platform.admin.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.biocome.platform.admin.vo.card.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.constant.CardStatusEnum;
import com.biocome.platform.admin.constant.CardTypeEnum;
import com.biocome.platform.admin.entity.Build;
import com.biocome.platform.admin.entity.Card;
import com.biocome.platform.admin.entity.Device;
import com.biocome.platform.admin.entity.Landlord;
import com.biocome.platform.admin.mapper.CardMapper;
import com.biocome.platform.admin.rpc.service.CardRpc;
import com.biocome.platform.admin.utils.RpcTokenUtil;
import com.biocome.platform.admin.utils.UriUtil;
import com.biocome.platform.admin.vo.admin.AdminCardVo;
import com.biocome.platform.admin.vo.admin.AdminSimpleCardVo;
import com.biocome.platform.admin.vo.card.*;
import com.biocome.platform.admin.vo.LesseeCardMsgResp;
import com.biocome.platform.admin.vo.device.CardDeviceVo;
import com.biocome.platform.admin.vo.lesseecard.LesseecardListReq;
import com.biocome.platform.admin.vo.lesseecard.LesseecardListResp;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.ValidateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: hxy
 * @create:2019/5/7 15:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardBiz extends BaseBiz<CardMapper, Card> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceBiz deviceBiz;
    @Autowired
    UriUtil uriUtil;
    @Autowired
    CardRpc cardRpc;
    @Autowired
    RpcTokenUtil rpcTokenUtil;
    @Autowired
    private BuildBiz buildBiz;
    @Autowired
    private LandlordBiz landlordBiz;
    @Autowired
    private AdminCardBindBiz adminCardBindBiz;

    /**
     * 列表查询
     *
     * @param cardno
     * @param cardType
     * @param cardStatus
     * @param id
     * @return
     * @throws Exception
     */
    public List<CardInfoResp> selectByAdditionForCardList(String cardno, String cardType, String cardStatus, Integer id) throws Exception {
        return mapper.selectByAdditionForCardList(cardno, cardType, cardStatus, id);
    }

    /**
     * 批量删卡
     *
     * @param card
     * @return
     * @throws Exception
     */
    public int deleteCard(List<Integer> card) throws Exception {
        return mapper.deleteCard(card);
    }

    /**
     * @param username
     * @param idNumber
     * @param sex
     * @param buildName
     * @param estateName
     * @return
     */
    public List<LesseeCardMsgResp> selectByAdditionForLesseeCardMsgPage(String username,
                                                                        String idNumber,
                                                                        Integer sex,
                                                                        String buildName,
                                                                        String estateName) {
        return mapper.selectByAdditionForLesseeCardMsgPage(username, idNumber, sex, buildName, estateName);
    }

    /**
     * 开卡
     *
     * @param req
     * @return
     */
    public BaseRpcResponse openCard(OpenCardVo req) {
        BaseRpcResponse resp = new BaseRpcResponse();
        try {
            List<CardSnVo> snList = req.getList();
            if (snList.size() > 0) {
                String sn = snList.get(0).getSn();
                Device device = new Device();
                device.setSn(sn);
                //根据sn获取对应设备信息和厂家编号
                List<Device> devices = deviceBiz.selectList(device);
                if (devices.size() > 0) {
                    Device dev = devices.get(0);
                    //获取对应厂家token
                    req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
                    //根据厂家编号获取URI
                    URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
                    if (uriByBrand != null) {
                        BaseRpcResponse baseRpcResponse = cardRpc.openCard(uriByBrand, req);
                        if (CommonConstants.RESP_RESULT_SUCCESS.equals(baseRpcResponse.getResult())) {
                            mapper.updateIsaliveByCardno(1, req.getCardno());
                        }
                        return baseRpcResponse;
                    } else {
                        resp.setResult("0");
                        resp.setErrorcode("110");
                        resp.setMessage("设备品牌对应URI获取异常");
                        return resp;
                    }
                } else {
                    resp.setResult("0");
                    resp.setErrorcode("106");
                    resp.setMessage("设备序号不存在");
                    return resp;
                }
            } else {
                resp.setResult("0");
                resp.setErrorcode("100");
                resp.setMessage("参数格式错误,sn为空");
                return resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setResult("0");
            resp.setErrorcode("105");
            resp.setMessage("服务调用异常");
            return resp;
        }
    }

    public List<OpenblukResp> openbulk(OpenblukVo req) {
        List<OpenblukResp> list = new ArrayList<>();
        OpenblukResp resp = new OpenblukResp();
        try {
            if (OpenblukVoHandler(req)) {
                String sn = req.getList().get(0).getListsn().get(0).getSn();
                Device device = new Device();
                device.setSn(sn);
                //根据sn获取对应设备信息和厂家编号
                List<Device> devices = deviceBiz.selectList(device);
                if (devices.size() > 0) {
                    Device dev = devices.get(0);
                    //获取对应厂家token
                    req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
                    //根据厂家编号获取URI
                    URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
                    if (uriByBrand != null) {
                        List<OpenblukResp> resultList = cardRpc.openbulk(uriByBrand, req);
                        log.info("批量开卡远程调用响应:{}", resultList.toString());
                        List<OpenblukResp> successlist = resultList.stream().filter(OpenblukResp -> CommonConstants.RESP_RESULT_SUCCESS.equals(OpenblukResp.getResult()) && OpenblukResp.getCardno() != null).collect(Collectors.toList());
                        log.info("修改卡为生效列表:{}", successlist.toString());
                        mapper.updateIsaliveByCardnoList(1, successlist);
                        return resultList;
                    } else {
                        resp.setResult("0");
                        resp.setErrorcode("110");
                        resp.setMessage("设备品牌对应URI获取异常");
                        list.add(resp);
                        return list;
                    }
                } else {
                    resp.setResult("0");
                    resp.setErrorcode("106");
                    resp.setMessage("设备序号不存在");
                    list.add(resp);
                    return list;
                }
            } else {
                resp.setResult("0");
                resp.setErrorcode("100");
                resp.setMessage("参数格式错误,sn为空");
                list.add(resp);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setResult("0");
            resp.setErrorcode("105");
            resp.setMessage("服务调用异常");
            list.add(resp);
            return list;
        }
    }

    private boolean OpenblukVoHandler(OpenblukVo req) {
        boolean b = false;
        if (req != null && req.getList() != null && req.getList().size() > 0) {
            if (req.getList().get(0) != null && req.getList().get(0).getListsn() != null && req.getList().get(0).getListsn().size() > 0) {
                if (req.getList().get(0).getListsn().get(0).getSn() != null) {
                    b = true;
                }
            }
        }
        return b;
    }

    public BaseRpcResponse logoutCard(LogoutCardVo req) {
        BaseRpcResponse resp = new BaseRpcResponse();
        try {
            List<CardSnVo> snList = req.getSnList();
            if (snList.size() > 0) {
                String sn = snList.get(0).getSn();
                Device device = new Device();
                device.setSn(sn);
                //根据sn获取对应设备信息和厂家编号
                List<Device> devices = deviceBiz.selectList(device);
                if (devices.size() > 0) {
                    Device dev = devices.get(0);
                    //获取对应厂家token
                    req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
                    //根据厂家编号获取URI
                    URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
                    if (uriByBrand != null) {
                        BaseRpcResponse baseRpcResponse = cardRpc.logoutCard(uriByBrand, req);
                        if (CommonConstants.RESP_RESULT_SUCCESS.equals(baseRpcResponse.getResult())) {
                            mapper.updateIsaliveByCardno(0, req.getCardno());
                        }
                        return baseRpcResponse;
                    } else {
                        resp.setResult("0");
                        resp.setErrorcode("110");
                        resp.setMessage("设备品牌对应URI获取异常");
                        return resp;
                    }
                } else {
                    resp.setResult("0");
                    resp.setErrorcode("106");
                    resp.setMessage("设备序号不存在");
                    return resp;
                }
            } else {
                resp.setResult("0");
                resp.setErrorcode("100");
                resp.setMessage("参数格式错误,sn为空");
                return resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setResult("0");
            resp.setErrorcode("105");
            resp.setMessage("服务调用异常");
            return resp;
        }
    }

    public BaseRpcResponse managerCard(ManagerCardVo req) {
        BaseRpcResponse resp = new BaseRpcResponse();
        try {
            List<CardSnVo> snList = req.getSnList();
            if (snList.size() > 0) {
                String sn = snList.get(0).getSn();
                Device device = new Device();
                device.setSn(sn);
                //根据sn获取对应设备信息和厂家编号
                List<Device> devices = deviceBiz.selectList(device);
                if (devices.size() > 0) {
                    Device dev = devices.get(0);
                    //获取对应厂家token
                    req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
                    //根据厂家编号获取URI
                    URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
                    if (uriByBrand != null) {
                        BaseRpcResponse baseRpcResponse = cardRpc.manageCard(uriByBrand, req);
                        if (CommonConstants.RESP_RESULT_SUCCESS.equals(baseRpcResponse.getResult())) {
                            mapper.updateIsaliveByCardno(Integer.valueOf(req.getOperation()), req.getCardno());
                        }
                        return baseRpcResponse;
                    } else {
                        resp.setResult("0");
                        resp.setErrorcode("110");
                        resp.setMessage("设备品牌对应URI获取异常");
                        return resp;
                    }
                } else {
                    resp.setResult("0");
                    resp.setErrorcode("106");
                    resp.setMessage("设备序号不存在");
                    return resp;
                }
            } else {
                resp.setResult("0");
                resp.setErrorcode("100");
                resp.setMessage("参数格式错误,sn为空");
                return resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setResult("0");
            resp.setErrorcode("105");
            resp.setMessage("服务调用异常");
            return resp;
        }
    }

    public TableResultResponse<LesseecardListResp> selectLesseecardList(LesseecardListReq req) throws Exception {
        try {
            List<LesseecardListResp> res = mapper.selectLesseecardList(req);
            return new TableResultResponse<>(res.size(), res);
        } catch (Exception e) {
            e.printStackTrace();
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询租户开卡信息失败，错误信息：数据库错误！");
        }
    }

    @Cache(key = "physicalCard:{1}")
    public Card selectByPhysicalCardNo(String cardNo) {
        Card card = new Card();
        card.setPhysicalCardno(cardNo);
        return selectOne(card);
    }

    /**
     * 注销卡
     *
     * @param isalive
     * @param admincode
     * @param cardNo
     */
    @CacheClear(key = "physicalCard:{2}")
    public void unregisteredCard(int isalive, String admincode, String cardNo) {
        mapper.updateCardStatus(isalive, admincode, cardNo);
    }

    /**
     * 移除卡
     *
     * @param usercode
     * @param cardNo
     */
    @CacheClear(key = "physicalCard:{2}")
    public void removeCard(String usercode, String cardNo) {
        mapper.removeCard(usercode, cardNo);
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
        Card card = selectByPhysicalCardNo(param.getCardNo());
        if(ValidateUtils.isNotEmpty(card)){
            deleteById(card.getId());
        }
        card = new Card();
        BeanUtils.copyProperties(card, param);
        card.setPhysicalCardno(param.getCardNo());
        card.setCreatetime(new Date());
        card.setIsalive(CardStatusEnum.PUBLISHING.getCardStatusCode());
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
        insert(card);
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
        return openCard(openVo);
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
        return logoutCard(logoutVo);
    }

    public BaseRpcResponse unregisterAdminCardNotify(String usercode, String cardNo) {
        LogoutCardVo logoutCardVo = new LogoutCardVo();
        Card card = selectByPhysicalCardNo(cardNo);
        List<String> cardNoList = new ArrayList<>();
        cardNoList.add(cardNo);
        List<CardDeviceVo> voList = adminCardBindBiz.cardDeviceList(cardNoList);
        if(ValidateUtils.isNotEmpty(voList)){
            Map<String, CardDeviceVo> map = new HashMap<String, CardDeviceVo>();
            for(CardDeviceVo vo : voList){
                map.put(vo.getCardNo(), vo);
            }
            logoutCardVo.setUsercode(usercode);
            logoutCardVo.setCardno(cardNo);
            logoutCardVo.setCardtype(card.getCardtype());
            logoutCardVo.setSnList(map.get(cardNo).getSnList());
        }
        return logoutCard(logoutCardVo);
    }

}
