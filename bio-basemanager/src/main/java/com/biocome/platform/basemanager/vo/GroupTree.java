package com.biocome.platform.basemanager.vo;

import com.biocome.platform.common.vo.TreeNode;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-17 15:21
 */
public class GroupTree extends TreeNode {
    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
