package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.constant.UserConstant;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.biz.CardBiz;
import com.biocome.platform.inter.basemanager.biz.LesseeBiz;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.wechatapplet.entity.AppUser;
import com.biocome.platform.wechatapplet.mapper.UserDetailMapper;
import com.biocome.platform.wechatapplet.vo.userdetail.CompleteVo;
import com.biocome.platform.wechatapplet.vo.userdetail.UserDetailReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/8/1 13:53
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailBiz {

    private final LesseeBiz lesseeBiz;
    private final CardBiz cardBiz;
    private final AppUserBiz appUserBiz;
    private final UserDetailMapper mapper;

    @Autowired
    public UserDetailBiz(LesseeBiz lesseeBiz, CardBiz cardBiz, AppUserBiz appUserBiz, UserDetailMapper mapper) {
        this.lesseeBiz = lesseeBiz;
        this.cardBiz = cardBiz;
        this.appUserBiz = appUserBiz;
        this.mapper = mapper;
    }

    public ObjectRestResponse insertUserDetail(UserDetailReq req) throws Exception {
        //①添加租户信息
        Lessee lessee = insertLessee(req);
        //②添加租户与卡关联
        if (ValidateUtils.isNotEmpty(req.getCardno())) {
            updateCard(req, lessee);
        }
        //③增加租户登录app
        insertAppUser(req, lessee);
        return new ObjectRestResponse();
    }

    private void insertAppUser(UserDetailReq req, Lessee lessee) {
        String papersnum = req.getPapersnum();
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(papersnum.substring(papersnum.length() - 6));
        AppUser appUser = new AppUser();
        appUser.setUsername(req.getUsername());
        appUser.setUsercode(lessee.getUsercode());
        appUser.setCreateTime(DateUtils.getCurrentTime());
        appUser.setPassword(password);
        appUser.setCreateUser(req.getRegistrant());
        appUserBiz.insertSelective(appUser);
    }

    private void updateCard(UserDetailReq req, Lessee lessee) throws Exception {
        Card card = new Card();
        card.setUsercode(lessee.getUsercode());
        card.setUsername(req.getUsername());
        card.setPhysicalCardno(req.getCardno());
        cardBiz.updateUserByCardNo(card);

    }

    private Lessee insertLessee(UserDetailReq req) throws Exception {
        String usercode = UUIDUtils.generateShortUuid();
        Date date = DateUtils.getCurrentTime();
        Lessee lessee = new Lessee();
        lessee.setUsercode(usercode);
        lessee.setPapersphoto(req.getPapersphoto());
        lessee.setPhoto(req.getPhoto());
        lessee.setUsername(req.getUsername());
        lessee.setUsersex(Integer.valueOf(req.getSex()));
        lessee.setNation(req.getNation());
        lessee.setBirthtime(req.getBirthday());
        lessee.setDomicileaddress(req.getDomicileaddress());
        lessee.setPaperstype(Integer.valueOf(req.getPaperstype()));
        lessee.setPapersnum(req.getPapersnum());
        lessee.setHousecode(req.getHousecode());
        lessee.setBuildcode(req.getBuildcode());
        lessee.setEstatecode(req.getEstatecode());
        lessee.setRegistertime(date);
        lessee.setCreatetime(date);
        lessee.setRegisterpeople(req.getRegistrant());
        lesseeBiz.insertLessee(lessee);
        return lessee;
    }

    /**
     * 完善信息
     *
     * @param vo 参数
     * @return java.lang.String
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/8/2 10:58
     */
    public String updateSelectiveById(CompleteVo vo) throws Exception {
        //更新完善信息
        mapper.updateSelectiveById(vo);
        //设置为已完善信息
        appUserBiz.updateIsComplete(vo.getUsercode());
        return AdminCommonConstant.BOOLEAN_NUMBER_TRUE;
    }

}
