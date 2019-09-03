package com.biocome.platform.common.tree;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.biocome.platform.common.util.ValidateUtils;

import java.util.ArrayList;
import java.util.List;

public class UINode {
    //当前节点层级
    private int level;
    //节点名称
    private String name;
    //节点id
    private String id;
    //附加显示
    private String attach;
    //节点类型
    private Integer type;
    //父节点
    private UINode parent;
    //父节点id
    private String parentId;
    //子节点
    private List<UINode> childList;

    public UINode(){}

    public UINode(String name, String id, int level) {
        this.name = name;
        this.id = id;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UINode getParent() {
        return parent;
    }

    public void setParent(UINode parent) {
        this.parent = parent;
    }

    public List<UINode> getChildList() {
        return childList;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setChildList(List<UINode> childList) {
        this.childList = childList;
        /*for(UINode node : childList){
            node.setParent(this);
        }*/
    }

    public void addChild(UINode child){
        synchronized (childList){
            if(ValidateUtils.isEmpty(childList)){
                childList = new ArrayList<UINode>();
            }
            childList.add(child);
        }
    }

    public void removeChild(int index){
        synchronized (childList){
            if(childList.size() >= (index+1)){
                childList.remove(index);
                if(ValidateUtils.isEmpty(childList)){
                    childList = null;
                }
            }
        }
    }

    public JSONObject node2Json(){
        JSONObject jObj = new JSONObject();
        jObj.put("name", name);
        jObj.put("id", id);
        jObj.put("level", level);
        if(ValidateUtils.isNotEmpty(childList)){
            jObj.put("child", child2Json());
        }
        return jObj;
    }

    public JSONArray child2Json(){
        JSONArray jArr = new JSONArray();
        if(ValidateUtils.isNotEmpty(childList)){
            for (UINode node : childList){
                jArr.add(node.node2Json());
            }
        }
        return jArr;
    }

    public List<String> getChildIds() {
        List<String> ids = new ArrayList<String>();
        if(ValidateUtils.isNotEmpty(childList)){
            for(UINode node : childList){
                ids.add(node.getId());
                ids.addAll(node.getChildIds());
            }
        }
        return ids;
    }

    public UINode getNodeById(String id, UINode currentNode) {
        if(this.id.equals(id)){
            currentNode = this;
        }else{
            if(ValidateUtils.isNotEmpty(getChildList())){
                for(UINode node : getChildList()){
                    if(id.equals(node.getId())){
                        currentNode = node;
                        break;
                    }else{
                        node.getNodeById(id, currentNode);
                    }
                }
            }
        }
        return currentNode;
    }

    public void getChildIdsByNodeId(String id, List<String> list){
        if(this.id.equals(id)){
            list.addAll(getChildIds());
        }else{
            if(ValidateUtils.isNotEmpty(getChildList())){
                for(UINode node : getChildList()){
                    node.getChildIdsByNodeId(id, list);
                }
            }
        }
    }

}
