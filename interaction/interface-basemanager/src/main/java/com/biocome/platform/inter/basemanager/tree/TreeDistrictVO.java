package com.biocome.platform.inter.basemanager.tree;

import java.util.List;

/**
 * @ClassName: TreeDistrictVO
 * @Author: shenlele
 * @Date: 2019/5/17 10:22
 * @Description:
 */
public class TreeDistrictVO<T> extends DistrictVO{

    private List<T> children;

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
