package com.biocome.platform.inter.basemanager.vo.card;


import com.biocome.platform.common.msg.auth.BaseRpcResponse;

/**
 * @author hxy
 * @date 2019/5/15 15:29
 */
public class OpenblukResp extends BaseRpcResponse {
    private String cardno;

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    @Override
    public String toString() {
        return "OpenblukResp{" +
                "cardno='" + cardno + '\'' +
                '}';
    }
}
