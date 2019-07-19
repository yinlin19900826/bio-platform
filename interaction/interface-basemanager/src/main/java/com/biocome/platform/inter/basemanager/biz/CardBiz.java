package com.biocome.platform.inter.basemanager.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.biocome.platform.inter.basemanager.LesseeCardMsgResp;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.mapper.CardMapper;
/*import com.bicome.platform.inter.basemanager.vo.admin.AdminCardVo;
import com.bicome.platform.inter.basemanager.vo.admin.AdminSimpleCardVo;
import com.bicome.platform.inter.basemanager.vo.card.*;*/
import com.biocome.platform.inter.basemanager.vo.card.CardInfoResp;
import com.biocome.platform.inter.basemanager.vo.card.OpenblukVo;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListReq;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListResp;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    /*@Autowired
    UriUtil uriUtil;*/
    /*@Autowired
    CardRpc cardRpc;*/
    /*@Autowired
    RpcTokenUtil rpcTokenUtil;*/
    @Autowired
    private BuildBiz buildBiz;
    @Autowired
    private LandlordBiz landlordBiz;
    /*@Autowired
    private AdminCardBindBiz adminCardBindBiz;*/

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
    /*public BaseRpcResponse openCard(OpenCardVo req) {
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
    }*/

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

    /*public BaseRpcResponse logoutCard(LogoutCardVo req) {
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
    }*/

    /*public BaseRpcResponse managerCard(ManagerCardVo req) {
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
    }*/

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

}
