package com.biocome.platform.guard.vo.otherrpc;

import java.util.List;

/**
 * @author hxy
 * @date 2019/8/21 10:18
 */
public class UpdateBluetoothVo {
    private String token;
    private List<SnAndDevdigest> list;
    private String createtime;

    public UpdateBluetoothVo() {
    }

    public UpdateBluetoothVo(String token, List<SnAndDevdigest> list, String createtime) {
        this.token = token;
        this.list = list;
        this.createtime = createtime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<SnAndDevdigest> getList() {
        return list;
    }

    public void setList(List<SnAndDevdigest> list) {
        this.list = list;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

   public class SnAndDevdigest {
        private String sn;
        private String devdigest;

        public SnAndDevdigest() {
        }

        public SnAndDevdigest(String sn, String devdigest) {
            this.sn = sn;
            this.devdigest = devdigest;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getDevdigest() {
            return devdigest;
        }

        public void setDevdigest(String devdigest) {
            this.devdigest = devdigest;
        }
    }
}
