package com.biocome.platform.inter.basemanager.vo.device;

import com.biocome.platform.inter.basemanager.vo.card.CardSnVo;
import com.biocome.platform.common.util.IdUtils;

import java.util.ArrayList;
import java.util.List;

public class CardDeviceVo {
    private String cardNo;
    private Integer cardtype;
    private String sn;
    private List<CardSnVo> snList;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
        setSnList(sn);
    }

    private void setSnList(String sn) {
        List<String> sns = IdUtils.seperate(sn, IdUtils.SEPERATOR_COMMA);
        List<CardSnVo> list = new ArrayList<CardSnVo>();
        for(String s : sns){
            CardSnVo snVo = new CardSnVo();
            snVo.setSn(s);
            list.add(snVo);
        }
        setSnList(list);
    }

    public List<CardSnVo> getSnList() {
        return snList;
    }

    public void setSnList(List<CardSnVo> snList) {
        this.snList = snList;
    }
}
