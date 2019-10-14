package com.biocome.video.vo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.biocome.platform.common.util.ValidateUtils;

import java.util.ArrayList;
import java.util.List;

public class UINode<T> {
    //当前节点层级
    private int level;
    //节点名称
    private String name;
    //节点id
    private Integer id;
    //附加显示
    private String attach;
    //节点类型
    private Integer type;
    //父节点
    private UINode parent;
    //父节点id
    private Integer parentId;

    private Integer cameraId;

    private Integer pipeline_id;

    private Integer countlivevideo;

    private Integer countcamera;
    //子节点
    private List<T> childList;


    public UINode(){}


    public UINode(String name, Integer id, int level) {
        this.name = name;
        this.id = id;
        this.level = level;
    }

    public Integer getCountlivevideo() {
        return countlivevideo;
    }

    public void setCountlivevideo(Integer countlivevideo) {
        this.countlivevideo = countlivevideo;
    }

    public Integer getCountcamera() {
        return countcamera;
    }

    public void setCountcamera(Integer countcamera) {
        this.countcamera = countcamera;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UINode getParent() {
        return parent;
    }

    public void setParent(UINode parent) {
        this.parent = parent;
    }


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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



    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getPipeline_id() {
        return pipeline_id;
    }

    public void setPipeline_id(Integer pipeline_id) {
        this.pipeline_id = pipeline_id;
    }

    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        this.childList = childList;
        /*for(UINode node : childList){
            node.setParent(this);
        }*/
    }
    public void addChild(T child){
        synchronized (childList){
            if(ValidateUtils.isEmpty(childList)){
                childList = new ArrayList<T>();
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

//    public JSONObject node2Json(){
//        JSONObject jObj = new JSONObject();
//        jObj.put("name", name);
//        jObj.put("id", id);
//        jObj.put("level", level);
//        if(ValidateUtils.isNotEmpty(childList)){
//            jObj.put("child", child2Json());
//        }
//        return jObj;
//    }

//    public JSONArray child2Json(){
//        JSONArray jArr = new JSONArray();
//        if(ValidateUtils.isNotEmpty(childList)){
//            for (UINode node : childList){
//
//                jArr.add(node.node2Json());
//            }
//        }
//        return jArr;
//    }


}
