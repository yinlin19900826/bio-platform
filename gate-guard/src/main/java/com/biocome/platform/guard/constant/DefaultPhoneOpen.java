package com.biocome.platform.guard.constant;

public enum DefaultPhoneOpen {
    ON(0, "设置"), OFF(1, "取消");

    private int status;
    private String name;

    DefaultPhoneOpen(int status, String name){
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
