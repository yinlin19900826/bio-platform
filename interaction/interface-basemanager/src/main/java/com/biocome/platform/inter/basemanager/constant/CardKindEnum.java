package com.biocome.platform.inter.basemanager.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hxy
 * @date 2019/5/9 09:10
 */
public enum CardKindEnum {

    /**
     * 卡片类型
     */
    CPU("1", "CPU卡"), IC("2", "IC卡"), SHENZHENTONG("3", "深圳通卡");

    private String cardTypeCode;
    private String cardTypeName;
    private CardKindEnum(String cardTypeCode, String cardTypeName){
        this.cardTypeCode = cardTypeCode;
        this.cardTypeName = cardTypeName;
    }

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public static Map<String, String> getAllCardType() {
        Map<String, String> map = new HashMap<>();
        for (CardKindEnum cardKindEnum : CardKindEnum.values()) {
            map.put(cardKindEnum.getCardTypeCode(), cardKindEnum.getCardTypeName());
        }
        return map;
    }
}
