package com.biocome.platform.basemanager.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hxy
 * @date 2019/5/8 16:19
 */
public enum CardStatusEnum {
    /**
     * 卡状态
     */
    LOGOUT("0", "注销"), VALID("1", "有效"), PUBLISHING("2", "发卡"), FORBIDDEN("3", "禁用"), BLACKLIST("4", "黑名单");

    private String cardStatusCode;
    private String cardStatusName;

    private CardStatusEnum(String cardStatusCode, String cardStatusName) {
        this.cardStatusCode = cardStatusCode;
        this.cardStatusName = cardStatusName;
    }

    public String getCardStatusCode() {
        return cardStatusCode;
    }

    public void setCardStatusCode(String cardStatusCode) {
        this.cardStatusCode = cardStatusCode;
    }

    public String getCardStatusName() {
        return cardStatusName;
    }

    public void setCardStatusName(String cardStatusName) {
        this.cardStatusName = cardStatusName;
    }

    public static Map<String, String> getAllCardStatus() {
        Map<String, String> map = new HashMap<>();
        for (CardStatusEnum cardTypeEnum : CardStatusEnum.values()) {
            map.put(cardTypeEnum.getCardStatusCode(), cardTypeEnum.getCardStatusName());
        }
        return map;
    }
}
