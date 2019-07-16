package com.biocome.platform.common.msg;

import java.util.List;
import java.util.Map;

/**
 * @author  hxy
 * @date 2019/5/8 11:13
 */
public class MapMsgAndTableResultResponse<T> extends TableResultResponse<T>{
    private Map<String,Object> mapMsg;

    public MapMsgAndTableResultResponse(Map<String,Object> mapMsg,long total, List<T> rows){
        super(total,rows);
        this.mapMsg = mapMsg;
    }
    public MapMsgAndTableResultResponse(){

    }
    public Map<String, Object> getMapMsg() {
        return mapMsg;
    }

    public void setMapMsg(Map<String, Object> mapMsg) {
        this.mapMsg = mapMsg;
    }
}
