package com.biocome.platform.admin.constant;

public enum CardTypeEnum {
    NORMAL("普通卡",1), ADMIN("管理员卡",2), LANDLORD("房东卡",3), SUPER("管理员通卡",4);

    private String code;
    private int value;

    CardTypeEnum(String code, int value){
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
