package com.biocome.platform.common.tree;

import com.alibaba.fastjson.JSONObject;
import com.biocome.platform.common.util.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UITree {
    //节点深度
    private int deepth;
    //根节点
    private UINode root;

    public UITree(){
    }

    /**
     * 创建制定深度的树
     * @param deepth
     */
    public UITree(int deepth){
        this.deepth = deepth;
    }

    /*public void createRoot(String name, String id){
        deepth = 1;
        int level = 1;
        root = new UINode(name, id, level);
    }*/

    //树转换成json
    public JSONObject tree2Json(){
        return root.node2Json();
    }

    public UINode getRoot() {
        return root;
    }

    public void setRoot(UINode root) {
        this.root = root;
    }

    /**
     * 添加节点
     * @param map
     */
    public void addNodes(Map<String, List<UINode>> map) {
        UITreeUtil.addNodes(root, 1, map);
    }

    public List<String> findBranchById(String id) {
        List<String> ids = new ArrayList<String>();
        ids.add(id);
        UINode target = null;
        if(root.getId().equals(id)){
            target = root;
        }else{
            if(ValidateUtils.isNotEmpty(root.getChildList())){
                for(UINode node : root.getChildList()){
                    node.getChildIdsByNodeId(id, ids);
                }
            }
        }
        return ids;
    }

}
