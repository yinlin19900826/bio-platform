package com.biocome.platform.common.tree;

public enum UINodeType {
    BRANCH("分支", 1), LEAF("叶子", 2);

    private String name;
    private int value;

    UINodeType(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
