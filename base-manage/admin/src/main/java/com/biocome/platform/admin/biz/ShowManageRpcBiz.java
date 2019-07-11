package com.biocome.platform.admin.biz;

import com.alibaba.fastjson.JSON;
import com.biocome.platform.admin.entity.Build;
import com.biocome.platform.admin.entity.House;
import com.biocome.platform.admin.mapper.BuildMapper;
import com.biocome.platform.admin.mapper.DeviceMapper;
import com.biocome.platform.admin.mapper.HouseMapper;
import com.biocome.platform.admin.utils.RpcTokenUtil;
import com.biocome.platform.admin.utils.UriUtil;
import com.biocome.platform.admin.vo.device.DeviceSnVo;
import com.biocome.platform.admin.vo.device.DeviceStatusRes;
import com.biocome.platform.admin.vo.device.DoorStatusRes;
import com.biocome.platform.admin.vo.showmanage.BuildAndHouseVo;
import com.biocome.platform.admin.vo.showmanage.DeviceAndStatusVo;
import com.biocome.platform.admin.vo.showmanage.HouseVo;
import com.biocome.platform.admin.vo.showmanage.ShowManageVo;
import com.biocome.platform.admin.mapper.AdminBuildingBindMapper;
import com.biocome.platform.admin.rpc.service.DeviceRpc;
import com.biocome.platform.admin.vo.device.DeviceInfoResp;
import com.biocome.platform.common.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ShowManageRpcBiz
 * @Author: shenlele
 * @Date: 2019/5/15 18:07
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShowManageRpcBiz {

    Logger log = LoggerFactory.getLogger(ShowManageRpcBiz.class);

    @Value("${dictCode}")
    String dictCode;
    @Autowired
    protected UriUtil uriUtil;
    @Autowired
    private DeviceRpc deviceRpc;
    @Autowired
    private BuildMapper buildMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private RpcTokenUtil rpcTokenUtil;
    @Autowired
    private AdminBuildingBindMapper bindMapper;

    /**
     * 查询封装楼栋，房屋，设备信息
     *
     * @param buildcode 楼栋编号
     * @return ShowManageVo
     * @Author shenlele
     * @Date 2019/5/27 11:54
     */
    public ShowManageVo buildAndHouseAnddevice(String buildcode) {
        ShowManageVo showManageVo = new ShowManageVo();
        BuildAndHouseVo buildAndHouse = new BuildAndHouseVo();
        try {
            //查询楼栋信息放入实体类
            Build build = new Build();
            build.setBuildcode(buildcode);
            List<Build> buildList = buildMapper.select(build);
            if (ValidateUtils.isNotEmpty(buildList)) {
                buildAndHouse.setBuild(buildList.get(0));
            }
            //查询房屋信息放入实体类
            House house = new House();
            house.setBuildcode(buildcode);
            //HouseVo中的countlessee字段决定房屋展示时是否有租户居住
            List<HouseVo> houses = houseMapper.selectHouseVo(buildcode);
            buildAndHouse.setHouses(houses);
            showManageVo.setBuildAndHouse(buildAndHouse);
            //查询房东信息
            List<String> list = bindMapper.selectNamesByCode(buildcode);
            if (ValidateUtils.isNotEmpty(list)) {
                StringBuilder sb = new StringBuilder();
                for (String name : list) {
                    sb.append(name).append(",");
                }
                String str = sb.toString();
                showManageVo.setLandlords(str.substring(0, str.length() - 1));
            }
        } catch (Exception e) {
            log.error("查询楼栋房屋房东信息失败，楼栋编号为：" + buildcode + "，错误信息为：" + e.getMessage());
        }
        try {
            //查询设备信息放入实体类中
            DeviceInfoResp device = new DeviceInfoResp();
            device.setBuildcode(buildcode);
            device.setDictCode(dictCode);
            List<DeviceInfoResp> deviceList = deviceMapper.selectByDevice(device);
            showManageVo.setDevices(deviceList);
        } catch (Exception e) {
            log.error("查询楼栋房屋信息失败，楼栋编号为：" + buildcode + "，错误信息为：" + e.getMessage());
        }
        return showManageVo;
    }

    /**
     * 查询设备信息与设备状态信息
     *
     * @param buildcode 楼栋编号
     * @return DeviceAndStatusVo
     * @Author shenlele
     * @Date 2019/5/27 11:54
     */
    public List<DeviceAndStatusVo> showDevice(String buildcode) {
        List<DeviceAndStatusVo> deviceAndStatusVos = new ArrayList<>();
        DeviceAndStatusVo deviceAndStatus;
        try {
            //查询设备信息放入实体类中
            DeviceInfoResp device = new DeviceInfoResp();
            device.setBuildcode(buildcode);
            device.setDictCode(dictCode);
            List<DeviceInfoResp> deviceList = deviceMapper.selectByDevice(device);
            //查询门禁机状态放入map中
            if (ValidateUtils.isNotEmpty(deviceList)) {
                List<DeviceAndStatusVo> list = new ArrayList<>();
                for (DeviceInfoResp model : deviceList) {
                    if (ValidateUtils.isNotEmpty(model.getBrandcode()) && ValidateUtils.isNotEmpty(model.getSn())) {
                        URI uri = uriUtil.getUriByBrand(model.getBrandcode());
                        deviceAndStatus = new DeviceAndStatusVo();
                        //将设备信息放入实体类
                        deviceAndStatus.setDevice(model);
                        try {
                            DeviceSnVo deviceSnVo = new DeviceSnVo(rpcTokenUtil.getRpcToken(model.getBrandcode()), model.getSn());
                            //远程调用查询设备状态信息并放入实体类
                            DeviceStatusRes deviceStatusRes = deviceRpc.deviceStatus(uri, deviceSnVo);
                            if (ValidateUtils.isNotEmpty(deviceStatusRes.getFacilitystatus()) && ValidateUtils.isNotEmpty(deviceStatusRes.getCamerastatus())) {
                                deviceAndStatus.setDeviceStatusRes(deviceStatusRes);
                            }
                            //远程调用查询门状态信息并放入实体类
                            DoorStatusRes doorStatusRes = deviceRpc.doorStatus(uri, deviceSnVo);
                            if (ValidateUtils.isNotEmpty(doorStatusRes.getDoorstatus())) {
                                deviceAndStatus.setDoorStatusRes(doorStatusRes);
                            }
                            list.add(deviceAndStatus);
                        } catch (Exception e) {
                            log.error("远程调用设备信息失败，楼栋编号为：" + buildcode + "，设备编码为：" + model.getSn() + "，错误信息为：" + e.getMessage());
                            deviceAndStatusVos.add(deviceAndStatus);
                            continue;
                        }
                        deviceAndStatusVos.add(deviceAndStatus);
                    } else {
                        log.error("设备数据信息不全，楼栋编号为：" + buildcode + "，所查设备信息为：" + JSON.toJSONString(model));
                    }
                }
            }
        } catch (Exception e) {
            log.error("查询设备信息与状态信息错误，错误信息为：" + e.getMessage());
        }
        return deviceAndStatusVos;
    }
}
