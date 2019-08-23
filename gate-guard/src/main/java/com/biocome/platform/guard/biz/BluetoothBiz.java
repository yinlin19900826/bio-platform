package com.biocome.platform.guard.biz;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.guard.rpc.service.OtherRpc;
import com.biocome.platform.guard.utils.RpcTokenUtil;
import com.biocome.platform.guard.utils.UriUtil;
import com.biocome.platform.guard.vo.otherrpc.UpdateBluetoothVo;
import com.biocome.platform.guard.vo.otherrpc.UploadBluetoothReq;
import com.biocome.platform.inter.basemanager.biz.DeviceBiz;
import com.biocome.platform.inter.basemanager.biz.DictBiz;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.entity.Dictionary;
import com.biocome.platform.inter.basemanager.mapper.DeviceMapper;
import com.biocome.platform.inter.basemanager.vo.device.DeviceSnBrandVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @date 2019/8/21 14:00
 */
@Service
@RefreshScope
@Transactional(rollbackFor = Exception.class)
public class BluetoothBiz {
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    OtherRpc rpc;
    @Autowired
    RpcTokenUtil rpcTokenUtil;
    @Autowired
    DictBiz dictBiz;
    @Autowired
    UriUtil uriUtil;

    public ObjectRestResponse updatebluetooth(DeviceSnBrandVo vo) throws Exception {
        URI uri = uriUtil.getUriByBrand(vo.getBrandcode());
        String devdigest = getDevdigest(vo.getBrandcode());
        UpdateBluetoothVo bluetoothVo = new UpdateBluetoothVo();
        UpdateBluetoothVo.SnAndDevdigest snAndDevdigest = bluetoothVo.new SnAndDevdigest(vo.getSn(), devdigest);
        List<UpdateBluetoothVo.SnAndDevdigest> list = new ArrayList<>();
        list.add(snAndDevdigest);

        bluetoothVo.setToken(rpcTokenUtil.getRpcToken(vo.getBrandcode()));
        bluetoothVo.setCreatetime(DateUtils.getCurrentTimeStr());
        bluetoothVo.setList(list);

        BaseRpcResponse resp = rpc.updatebluetooth(uri, bluetoothVo);
        if (resp.getResult().equals("0")) {
            return new ObjectRestResponse().customError("下发给小平台设备摘要失败");
        } else {
            return new ObjectRestResponse().success();
        }
    }

    private String getDevdigest(String brandcode) throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setDictCode("008");
        List<Dictionary> list = dictBiz.selectList(dictionary);
        String comment = null;
        for (Dictionary dict : list) {
            if (dict.getDictKey().equals(brandcode)) {
                comment = dict.getComment();
                break;
            }
        }
        if (StringUtils.isNotBlank(comment)) {
            String pre = comment.substring(0, 1);
            String code = String.valueOf(Math.random()).replace("0.", "").substring(0, 9);
            return pre + code;
        } else {
            throw new Exception("无法找到对应厂家");
        }
    }

    public BaseRpcResponse updatebluetooth(UploadBluetoothReq req) {
        if (StringUtils.isBlank(req.getAccesskey())
                && req.getInfo() == null
                && StringUtils.isBlank(req.getInfo().getSn())
                && StringUtils.isBlank(req.getInfo().getMacaddr())
                && StringUtils.isBlank(req.getInfo().getDevdigest())
                && StringUtils.isBlank(req.getInfo().getCreatetime())
                ) {
            return new BaseRpcResponse().failure();
        }
        Device device = new Device();
        device.setSn(req.getInfo().getSn());
        device.setBluetoothmac(req.getInfo().getMacaddr());
        device.setBluetoothUpdatetime(req.getInfo().getCreatetime());
        device.setDevdigest(req.getInfo().getDevdigest());

        int result = deviceMapper.updateBluetoothBySn(device);

        if (result > 0){
            return new BaseRpcResponse().success();
        }else{
            return new BaseRpcResponse().failure();
        }
    }
}
