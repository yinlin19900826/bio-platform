package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.mapper.DeviceMapper;
import com.biocome.platform.wechatapplet.rpc.service.OtherRpc;
import com.biocome.platform.wechatapplet.utils.RpcTokenUtil;
import com.biocome.platform.wechatapplet.utils.UriUtil;
import com.biocome.platform.wechatapplet.vo.opendoor.OpenDoorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

/**
 * @ClassName: OpenDoorBiz
 * @Author: shenlele
 * @Date: 2019/7/29 18:28
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OpenDoorBiz {

    private final UriUtil uriUtil;
    private final DeviceMapper mapper;
    private final OtherRpc rpc;
    private final RpcTokenUtil rpcTokenUtil;

    @Autowired
    public OpenDoorBiz(DeviceMapper mapper, UriUtil uriUtil, OtherRpc rpc, RpcTokenUtil rpcTokenUtil) {
        this.mapper = mapper;
        this.uriUtil = uriUtil;
        this.rpc = rpc;
        this.rpcTokenUtil = rpcTokenUtil;
    }

    public BaseRpcResponse openDoor(OpenDoorVo req) {
        BaseRpcResponse resp = new BaseRpcResponse();
        try {
            if (req.getSn() != null) {
                Device device = new Device();
                device.setSn(req.getSn());
                //根据sn获取对应设备信息和厂家编号
                List<Device> devices = mapper.select(device);
                if (devices.size() > 0) {
                    Device dev = devices.get(0);
                    //获取对应厂家token
                    req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
                    //根据厂家编号获取URI
                    URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
                    if (uriByBrand != null) {
                        return rpc.openDoor(uriByBrand, req);
                    } else {
                        resp.setResult("0");
                        resp.setErrorcode("110");
                        resp.setMessage("设备品牌对应URI获取异常");
                        return resp;
                    }
                } else {
                    resp.setResult("0");
                    resp.setErrorcode("106");
                    resp.setMessage("设备序号不存在");
                    return resp;
                }
            } else {
                resp.setResult("0");
                resp.setErrorcode("100");
                resp.setMessage("参数格式错误,sn为空");
                return resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setResult("0");
            resp.setErrorcode("105");
            resp.setMessage("服务调用异常");
            return resp;
        }
    }
}
