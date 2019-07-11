package com.biocome.platform.admin.vo.card;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/15 09:28
 */
public class OpenblukVo {
    private String token;
    private List<OpenblukInVo> list;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<OpenblukInVo> getList() {
        return list;
    }

    public void setList(List<OpenblukInVo> list) {
        this.list = list;
    }

    public static class OpenblukInVo {
        private String usercode;
        private String cardno;
        private List<CardSnVo> listsn;
        private String cardtype;

        public OpenblukInVo(){

        }
        public String getUsercode() {
            return usercode;
        }

        public void setUsercode(String usercode) {
            this.usercode = usercode;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public List<CardSnVo> getListsn() {
            return listsn;
        }

        public void setListsn(List<CardSnVo> listsn) {
            this.listsn = listsn;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }
    }
}
