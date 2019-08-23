package com.biocome.platform.guard.vo.otherrpc;

/**
 * @author hxy
 * @date 2019/8/21 14:26
 */
public class UploadBluetoothReqDetail {
    private String sn;
    private String macaddr;
    private String devdigest;
    private String createtime;

    public UploadBluetoothReqDetail() {
    }

    public UploadBluetoothReqDetail(String sn, String macaddr, String devdigest, String createtime) {
        this.sn = sn;
        this.macaddr = macaddr;
        this.devdigest = devdigest;
        this.createtime = createtime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMacaddr() {
        return macaddr;
    }

    public void setMacaddr(String macaddr) {
        this.macaddr = macaddr;
    }

    public String getDevdigest() {
        return devdigest;
    }

    public void setDevdigest(String devdigest) {
        this.devdigest = devdigest;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "UploadBluetoothReqDetail{" +
                "sn='" + sn + '\'' +
                ", macaddr='" + macaddr + '\'' +
                ", devdigest='" + devdigest + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
