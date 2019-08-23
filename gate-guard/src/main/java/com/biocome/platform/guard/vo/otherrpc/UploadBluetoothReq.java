package com.biocome.platform.guard.vo.otherrpc;

/**
 * @author hxy
 * @date 2019/8/21 14:23
 */
public class UploadBluetoothReq {
    private String accesskey;
    private UploadBluetoothReqDetail info;

    public UploadBluetoothReq() {
    }

    public UploadBluetoothReq(String accesskey, UploadBluetoothReqDetail info) {
        this.accesskey = accesskey;
        this.info = info;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public UploadBluetoothReqDetail getInfo() {
        return info;
    }

    public void setInfo(UploadBluetoothReqDetail info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UploadBluetoothReq{" +
                "accesskey='" + accesskey + '\'' +
                ", info=" + info +
                '}';
    }
}
