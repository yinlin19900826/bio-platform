package com.biocome.platform.guard.biz;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.constant.UserConstant;
import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.ThreadManager;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.guard.constant.APPConstants;
import com.biocome.platform.guard.feign.AppAccountService;
import com.biocome.platform.guard.mapper.DoorDeviceCardMapper;
import com.biocome.platform.guard.rpc.service.CardRpc;
import com.biocome.platform.inter.basemanager.rpc.service.FileRpc;
import com.biocome.platform.guard.utils.RpcTokenUtil;
import com.biocome.platform.guard.utils.UriUtil;
import com.biocome.platform.inter.basemanager.vo.upload.ChangeLesseePicReq;
import com.biocome.platform.inter.basemanager.LesseeCardMsgResp;
import com.biocome.platform.inter.basemanager.biz.CardBiz;
import com.biocome.platform.inter.basemanager.biz.DeviceBiz;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.constant.CardTypeEnum;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.mapper.CardMapper;
import com.biocome.platform.inter.basemanager.mapper.DeviceMapper;
import com.biocome.platform.inter.basemanager.mapper.LesseeMapper;
import com.biocome.platform.inter.basemanager.vo.card.*;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseeCardVo;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListReq;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListResp;
import com.biocome.platform.inter.gateguard.vo.user.AppAccountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hxy
 * @date 2019/7/11 18:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DoorDeviceCardBiz {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DoorDeviceCardMapper doorDeviceCardMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private LesseeMapper lesseeMapper;

    @Autowired
    private CardBiz cardBiz;

    @Autowired
    private DeviceBiz deviceBiz;

    @Autowired
    private RpcTokenUtil rpcTokenUtil;

    @Autowired
    private UriUtil uriUtil;

    @Autowired
    private CardRpc cardRpc;

    @Autowired
    private FileRpc fileRpc;

    @Autowired
    private AppAccountService appAccountService;

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
        return doorDeviceCardMapper.selectByAdditionForLesseeCardMsgPage(username, idNumber, sex, buildName, estateName);
    }

    public BaseRpcResponse addLesseeCardList(LesseeCardVo vo) throws Exception {
        if (String.valueOf(CardTypeEnum.NORMAL.getValue()).equals(vo.getCardtype())) {
            //普通卡，租户开卡流程
            return lesseeOpenCard(vo);
        }
        return new BaseRpcResponse().success();
    }

    public BaseRpcResponse lesseeOpenCard(LesseeCardVo vo) throws Exception {
        //根据楼栋编号获取设备编号
        List<CardSnVo> sns = deviceMapper.selectSnByBuildCode(vo.getBuildcode());
        if (sns == null && sns.size() == 0) {
            return new BaseRpcResponse("此楼栋没有门禁机信息", "0", "204");
        }
        String usercode = UUIDUtils.generateShortUuid();
        Lessee lessee = new Lessee();
        lessee.setBirthtime(vo.getBirthtime());
        lessee.setBuildcode(vo.getBuildcode());
        lessee.setBuildname(vo.getBuildname());
        lessee.setEstatecode(vo.getEstatecode());
        lessee.setEstatename(vo.getEstatename());
        lessee.setUsername(vo.getUsername());
        lessee.setUsercode(usercode);
        lessee.setUsersex(vo.getUsersex());
        lessee.setPaperstype(vo.getPaperstype());
        lessee.setPapersnum(vo.getPapersnum());
        lessee.setDomicileaddress(vo.getDomicileaddress());
        lessee.setPhoto(vo.getPhoto());
        lessee.setHeadphoto(vo.getHeadphoto());
        lessee.setPapersphoto(vo.getPapersphoto());
        lessee.setCreatetime(DateUtils.getCurrentTime());
        lessee.setRegistertime(DateUtils.getCurrentTime());

        Card card = new Card();
        card.setBuildcode(vo.getBuildcode());
        card.setCardkind(vo.getCardkind());
        card.setCardtype(vo.getCardtype());
        card.setPhysicalCardno(vo.getCardno());
        card.setLogicCardno(vo.getLogicCardno());
        card.setCreatetime(DateUtils.getCurrentTime());
        //设置初始状态为发卡
        card.setIsalive("2");
        card.setHousecode(vo.getHousecode());
        card.setAdmincode(vo.getAdmincode());
        card.setUsercode(usercode);

        Card card1 = new Card();
        card1.setPhysicalCardno(vo.getCardno());
        List<Card> cardList = cardMapper.select(card1);
        if (cardList != null && cardList.size() > 0) {
            //如果之前有卡信息，则删除对应的卡，重新绑定
            cardMapper.deleteByPrimaryKey(cardList.get(0).getId());
        }


        Lessee lessee1 = new Lessee();
        lessee1.setPapersnum(vo.getPapersnum());
        List<Lessee> lesseeList = lesseeMapper.select(lessee1);
        int lesseeResult = 0;
        if (lesseeList != null && lesseeList.size() > 0) {
            //如果之前有租户信息，则不变
            card.setUsercode(lesseeList.get(0).getUsercode());
        } else {
            //没有则添加
            lesseeResult = lesseeMapper.insert(lessee);

            //加入APP登录默认信息
            String papersnum = vo.getPapersnum();
            AppAccountVo accountVo = new AppAccountVo();
            accountVo.setUsername(papersnum);
            accountVo.setUsercode(usercode);
            accountVo.setCreateTime(DateUtils.getCurrentTime());
            accountVo.setCreateUser(BaseContextHandler.getUsercode());
            accountVo.setType(APPConstants.USER_TYPE_LESSEE);
            appAccountService.createAppAccount(accountVo);
        }
        //判断并下发卡
        BaseRpcResponse baseRpcResponse = ifaliveAndOpenCard(vo, sns);
        if (baseRpcResponse != null && CommonConstants.RESP_RESULT_SUCCESS.equals(baseRpcResponse.getResult())) {
            card.setIsalive("1");
        } else if (baseRpcResponse == null) {
            baseRpcResponse = new BaseRpcResponse().success();
        }
        cardMapper.insert(card);

        return baseRpcResponse;

    }

    private BaseRpcResponse ifaliveAndOpenCard(LesseeCardVo vo, List<CardSnVo> sns) throws Exception {
        if (AdminCommonConstant.BOOLEAN_NUMBER_TRUE.equals(vo.getIfactivate())) {
            OpenCardVo openCard = new OpenCardVo();
            openCard.setCardno(vo.getLogicCardno());
            openCard.setCardtype(vo.getCardtype());
            openCard.setUsercode(vo.getUsercode());
            openCard.setList(sns);
            BaseRpcResponse baseRpcResponse = openCard(openCard);
            log.info("激活门禁卡结果{}", baseRpcResponse.toString());
            return baseRpcResponse;
        }
        return null;
    }

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
                            doorDeviceCardMapper.updateIsaliveByCardno(1, req.getCardno());
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

    public BaseRpcResponse changeLesseePic(ChangeLesseePicReq req) throws Exception {
        int result = doorDeviceCardMapper.changeLesseePic(req);
        if (result == 0) {
            return new BaseRpcResponse().failure();
        } else {
//            List<FileVo> fileVos = FileUtils.getFileDetailByUrls("1", req.getHeadphoto(), req.getPhoto(), req.getPapersphoto());
//            ObjectRestResponse objectRestResponse = fileRpc.fileDel(fileVos);
//            if (objectRestResponse.getStatus() != 200) {
//                throw new Exception("远程删除文件失败");
//            }
            return new BaseRpcResponse().success();
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
                        doorDeviceCardMapper.updateIsaliveByCardnoList(1, successlist);
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
                req.setList(req.getSnList());
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
                            doorDeviceCardMapper.updateIsaliveByCardno(0, req.getCardno());
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
                req.setList(req.getSnList());
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
                            if (Integer.valueOf(req.getOperation()) == 1) {
                                doorDeviceCardMapper.updateIsaliveByCardno(3, req.getCardno());
                            } else if (Integer.valueOf(req.getOperation()) == 2) {
                                doorDeviceCardMapper.updateIsaliveByCardno(1, req.getCardno());
                            }
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
            List<LesseecardListResp> res = doorDeviceCardMapper.selectLesseecardList(req);
            return new TableResultResponse<>(res.size(), res);
        } catch (Exception e) {
            e.printStackTrace();
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询租户开卡信息失败，错误信息：数据库错误！");
        }
    }
}
