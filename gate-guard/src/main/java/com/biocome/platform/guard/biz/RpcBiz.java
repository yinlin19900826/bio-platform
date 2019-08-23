package com.biocome.platform.guard.biz;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.CodeUtils;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.rpc.service.OtherRpc;
import com.biocome.platform.guard.utils.RpcTokenUtil;
import com.biocome.platform.guard.utils.UriUtil;
import com.biocome.platform.guard.vo.otherrpc.*;
import com.biocome.platform.inter.basemanager.biz.DeviceBiz;
import com.biocome.platform.inter.basemanager.biz.DictBiz;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.entity.Dictionary;
import com.biocome.platform.inter.basemanager.vo.device.DeviceSnBrandVo;
import com.biocome.platform.inter.basemanager.vo.device.DeviceSnVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/15 17:40
 */
@Service
@RefreshScope
@Transactional(rollbackFor = Exception.class)
public class RpcBiz {

    @Value("${filepath}")
    String filepath;
    @Autowired
    UriUtil uriUtil;
    @Autowired
    DeviceBiz deviceBiz;
    @Autowired
    OtherRpc rpc;
    @Autowired
    RpcTokenUtil rpcTokenUtil;
    @Autowired
    DictBiz dictBiz;

    public BaseRpcResponse openDoor(OpenDoorVo req) {
        BaseRpcResponse resp = new BaseRpcResponse();
        try {
            if (req.getSn() != null) {
                Device device = new Device();
                device.setSn(req.getSn());
                //根据sn获取对应设备信息和厂家编号
                List<Device> devices = deviceBiz.selectList(device);
                if (devices.size() > 0) {
                    Device dev = devices.get(0);
                    //获取对应厂家token
                    req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
                    //根据厂家编号获取URI
                    URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
                    if (uriByBrand != null) {
                        BaseRpcResponse baseRpcResponse = rpc.openDoor(uriByBrand, req);
                        return baseRpcResponse;
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

    public OpendoorpasswordResp opendoorpassword(OpendoorpasswordVo req) {
        OpendoorpasswordResp resp = new OpendoorpasswordResp();
        try {
            int password = (int) ((Math.random() * 9 + 1) * 100000);
            req.setPassword(String.valueOf(password));
            if (req.getSn() != null) {
                Device device = new Device();
                device.setSn(req.getSn());
                //根据sn获取对应设备信息和厂家编号
                List<Device> devices = deviceBiz.selectList(device);
                if (devices.size() > 0) {
                    Device dev = devices.get(0);
                    //获取对应厂家token
                    req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
                    //根据厂家编号获取URI
                    URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
                    if (uriByBrand != null) {
                        BaseRpcResponse baseRpcResponse = rpc.opendoorpassword(uriByBrand, req);
                        resp.setResult(baseRpcResponse.getResult());
                        resp.setErrorcode(baseRpcResponse.getErrorcode());
                        resp.setMessage(baseRpcResponse.getMessage());
                        resp.setPassword(String.valueOf(password));
                        return resp;
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

    public ObjectRestResponse address(AddressBrandVo vo) throws Exception {
        URI uri = uriUtil.getUriByBrand(vo.getBrandcode());
        AddressVo addressVo = new AddressVo(rpcTokenUtil.getRpcToken(vo.getBrandcode()), vo.getSn(), vo.getDeviceip());
        AddressResp resp = rpc.address(uri, addressVo);
        if (AdminCommonConstant.BOOLEAN_NUMBER_TRUE.equals(resp.getResult())) {
            if (ValidateUtils.isNotEmpty(resp.getMacaddr()) && ValidateUtils.isNotEmpty(resp.getSerialno())) {
                Device device = new Device();
                device.setSn(vo.getSn());
                device.setIp(vo.getDeviceip());
                device.setMac(resp.getMacaddr());
                device.setSeriesNumber(resp.getSerialno());
                deviceBiz.updateBySnAndIp(device);
                return new ObjectRestResponse<>().success().data(resp);
            } else {
                return new ObjectRestResponse<>().failure();
            }
        } else {
            return new ObjectRestResponse<>().failure();
        }
    }

    public BaseRpcResponse restart(DeviceSnBrandVo vo) throws Exception {
        URI uri = uriUtil.getUriByBrand(vo.getBrandcode());
        DeviceSnVo deviceSnVo = new DeviceSnVo(rpcTokenUtil.getRpcToken(vo.getBrandcode()), vo.getSn());
        return rpc.restart(uri, deviceSnVo);
    }

    public BaseRpcResponse remoteUpdate(RemoteUpdateBrandVo vo) throws Exception {
        URI uri = uriUtil.getUriByBrand(vo.getBrandcode());
        RemoteUpdateVo remoteUpdateVo = new RemoteUpdateVo(rpcTokenUtil.getRpcToken(vo.getBrandcode()), vo.getSn(), filepath, CodeUtils.getUUID());
        return rpc.remoteupdate(uri, remoteUpdateVo);
    }
}
