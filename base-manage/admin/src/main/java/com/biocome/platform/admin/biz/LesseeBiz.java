package com.biocome.platform.admin.biz;

import com.biocome.platform.admin.vo.LesseeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.constant.AdminCommonConstant;
import com.biocome.platform.admin.constant.CardTypeEnum;
import com.biocome.platform.admin.entity.Card;
import com.biocome.platform.admin.entity.Lessee;
import com.biocome.platform.admin.fastdfs.FastDFSClientUtil;
import com.biocome.platform.admin.mapper.CardMapper;
import com.biocome.platform.admin.mapper.DeviceMapper;
import com.biocome.platform.admin.mapper.LesseeMapper;
import com.biocome.platform.admin.vo.card.CardSnVo;
import com.biocome.platform.admin.vo.card.OpenCardVo;
import com.biocome.platform.admin.vo.lesseecard.LesseeCardVo;
import com.biocome.platform.admin.vo.upload.ChangeLesseePicReq;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: LesseeBiz
 * @Author: shenlele
 * @Date: 2019/5/7 10:21
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LesseeBiz extends BaseBiz<LesseeMapper, Lessee> {

    private Logger log = LoggerFactory.getLogger(LesseeBiz.class);

    @Autowired
    private LesseeMapper lesseeMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private CardBiz cardBiz;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    FastDFSClientUtil fastDFSClientUtil;

    /**
     * 查询租户信息(可传值有estatecode，islogout，username，papersnum，usersex，occupation，politicsstatus，
     * birthbegintime，birthendtime，registerbegintime，registerendtime)
     *
     * @param pageSize 分页数量
     * @param pageNum  分页页码
     * @param lesseeVo 可传值有estatecode，islogout，username，papersnum，usersex，occupation，politicsstatus，birthbegintime，birthendtime，registerbegintime，registerendtime
     * @return com.biocome.platform.common.msg.TableResultResponse<Lessee>
     * @Author shenlele
     * @Date 2019/5/8 14:12
     */
    public TableResultResponse<Lessee> selectByAttribute(int pageSize, int pageNum, LesseeVo lesseeVo) {
        TableResultResponse<Lessee> res;
        try {
            if (ValidateUtils.isNotEmpty(lesseeVo.getAgetype())) {
                //处理时间
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int type = lesseeVo.getAgetype();
                if (type == 0) {
                    //0-16岁
                    lesseeVo.setBirthbegintime(date);
                    cal.add(Calendar.YEAR, -16);
                    lesseeVo.setBirthendtime(cal.getTime());
                } else if (type == 1) {
                    //16-65岁
                    cal.add(Calendar.YEAR, -16);
                    lesseeVo.setBirthbegintime(cal.getTime());
                    cal.setTime(date);
                    cal.add(Calendar.YEAR, -65);
                    lesseeVo.setBirthendtime(cal.getTime());
                } else {
                    //65岁以上
                    cal.add(Calendar.YEAR, -65);
                    lesseeVo.setBirthbegintime(cal.getTime());
                }
            }
            Page<Lessee> result = PageHelper.startPage(pageNum, pageSize);
            lesseeMapper.selectByAttribute(lesseeVo);
            res = new TableResultResponse<>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.error("查询租户信息失败，错误信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询租户信息失败!");
        }
        return res;
    }

    /**
     * 根据ID删除
     *
     * @param idList 主键
     * @return com.biocome.platform.common.msg.ObjectRestResponse<Lessee>
     * @Author shenlele
     * @Date 2019/5/8 14:13
     */
    public ObjectRestResponse<Lessee> deleteLessee(String idList) throws Exception {
        lesseeMapper.deleteLessee(IdUtils.getIds(idList));
        return new ObjectRestResponse<>(true);
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
        //加入卡和租户
        int cardResult = cardMapper.insert(card);
        int lesseeResult = mapper.insert(lessee);

        if (cardResult == 1 && lesseeResult == 1) {
            //判断是否需要激活，并下发卡
            return ifaliveAndOpenCard(vo, sns);
        } else {
            //存库失败，事务回滚
            throw new Exception("存库失败");
        }
    }

    private BaseRpcResponse ifaliveAndOpenCard(LesseeCardVo vo, List<CardSnVo> sns) throws Exception {
        if (AdminCommonConstant.BOOLEAN_NUMBER_TRUE.equals(vo.getIfactivate())) {
            OpenCardVo openCard = new OpenCardVo();
            openCard.setCardno(vo.getLogicCardno());
            openCard.setCardtype(vo.getCardtype());
            openCard.setUsercode(vo.getUsercode());
            openCard.setList(sns);
            BaseRpcResponse baseRpcResponse = cardBiz.openCard(openCard);
            log.info("激活门禁卡结果{}", baseRpcResponse.toString());
            return baseRpcResponse;
        }
        return new BaseRpcResponse().success();
    }

    /**
     * 保存租户信息（人员编号唯一）
     *
     * @param lessee
     * @return com.biocome.platform.common.msg.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/5/22 19:51
     */
    public ObjectRestResponse insertLessee(Lessee lessee) throws Exception {
        Lessee lessee1 = new Lessee();
        lessee1.setUsercode(lessee.getUsercode());
        List<Lessee> lessees = lesseeMapper.select(lessee1);
        if (ValidateUtils.isEmpty(lessees)) {
            lesseeMapper.insertSelective(lessee);
            return new ObjectRestResponse().success();
        } else {
            throw new Exception("保存失败！人员编号已存在！");
        }
    }

    public BaseRpcResponse changeLesseePic(ChangeLesseePicReq req) throws Exception {
        int result = mapper.changeLesseePic(req);
        if (result == 0) {
            return new BaseRpcResponse().failure();
        } else {
            try {
                fastDFSClientUtil.deleteFile(req.getHeadphoto());
                fastDFSClientUtil.deleteFile(req.getPhoto());
                fastDFSClientUtil.deleteFile(req.getPapersphoto());
            }catch (Exception e){
                log.info("找不到文件路径，删除失败");
            }
            return new BaseRpcResponse().success();
        }
    }
}
