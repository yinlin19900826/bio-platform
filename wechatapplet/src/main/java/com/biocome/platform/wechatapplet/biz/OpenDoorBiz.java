package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.inter.basemanager.constant.AdminCommonConstant;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.inter.basemanager.entity.Lessee;
import com.biocome.platform.inter.basemanager.mapper.DeviceMapper;
import com.biocome.platform.inter.basemanager.mapper.LesseeMapper;
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

    private final OtherRpc rpc;
    private final UriUtil uriUtil;
    private final DeviceMapper mapper;
    private final LesseeMapper lesseeMapper;
    private final RpcTokenUtil rpcTokenUtil;

    @Autowired
    public OpenDoorBiz(DeviceMapper mapper, UriUtil uriUtil, OtherRpc rpc, LesseeMapper lesseeMapper, RpcTokenUtil rpcTokenUtil) {
        this.rpc = rpc;
        this.mapper = mapper;
        this.uriUtil = uriUtil;
        this.lesseeMapper = lesseeMapper;
        this.rpcTokenUtil = rpcTokenUtil;
    }

    public String openDoor(String sn, String userCode) throws Exception {
        OpenDoorVo req = new OpenDoorVo();
        Lessee model = new Lessee();
        model.setUsercode(userCode);
        model = lesseeMapper.selectOne(model);
        req.setSn(sn);
        req.setUsercode(userCode);
        req.setUserdesc(model.getUsername());
        req.setUsertype(String.valueOf(model.getUsertype()));
        Device device = new Device();
        device.setSn(sn);
        //根据sn获取对应设备信息和厂家编号
        List<Device> devices = mapper.select(device);
        if (devices.size() > 0) {
            Device dev = devices.get(0);
            //获取对应厂家token
            req.setToken(rpcTokenUtil.getRpcToken(dev.getBrand()));
            //根据厂家编号获取URI
            URI uriByBrand = uriUtil.getUriByBrand(dev.getBrand());
            if (uriByBrand != null) {
                BaseRpcResponse resp = rpc.openDoor(uriByBrand, req);
                if (AdminCommonConstant.BOOLEAN_NUMBER_TRUE.equals(resp.getResult())) {
                    return AdminCommonConstant.BOOLEAN_NUMBER_TRUE;
                } else {
                    throw new Exception("请求小平台远程开门失败，" + JsonUtils.beanToJson(resp));
                }
            } else {
                throw new Exception("设备品牌对应URI获取异常");
            }
        } else {
            throw new Exception("设备序号不存在");
        }
    }
}
