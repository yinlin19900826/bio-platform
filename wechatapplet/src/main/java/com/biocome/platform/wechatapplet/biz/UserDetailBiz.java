package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.constant.UserConstant;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.UUIDUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.biz.CardBiz;
import com.biocome.platform.inter.basemanager.biz.LesseeBiz;
import com.biocome.platform.inter.basemanager.entity.Card;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.wechatapplet.entity.AppUser;
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

    @Autowired
    private LesseeBiz lesseeBiz;
    @Autowired
    private CardBiz cardBiz;
    @Autowired
    private AppUserBiz appUserBiz;

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

}
