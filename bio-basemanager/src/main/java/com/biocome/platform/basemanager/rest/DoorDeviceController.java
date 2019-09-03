package com.biocome.platform.basemanager.rest;

import com.biocome.platform.inter.basemanager.biz.BuildBiz;
import com.biocome.platform.inter.basemanager.biz.DeviceBiz;
import com.biocome.platform.inter.basemanager.biz.EstateBiz;
import com.biocome.platform.inter.basemanager.entity.Device;
import com.biocome.platform.basemanager.constant.BrandEnum;
import com.biocome.platform.basemanager.vo.resp.device.DeviceInfoResp;
import com.biocome.platform.common.handler.ResultHandler;
import com.biocome.platform.common.msg.MapMsgAndTableResultResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hxy
 * @create 2019/5/7 09:11
 */
@Controller
@RequestMapping("/doorDevice")
@Api(value = "门禁设备", tags = {"门禁设备操作"})
public class DoorDeviceController extends BaseController<DeviceBiz, Device> {
    Logger log = LoggerFactory.getLogger(DoorDeviceController.class);
    @Autowired
    EstateBiz estateBiz;
    @Autowired
    BuildBiz buildBiz;

    @ApiOperation("获取设备列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键ID", paramType = "query"),
            @ApiImplicitParam(name = "deviceName", value = "设备名称", paramType = "query"),
            @ApiImplicitParam(name = "sn", value = "设备编号", paramType = "query"),
            @ApiImplicitParam(name = "brand", value = "品牌编号", paramType = "query"),
            @ApiImplicitParam(name = "estatecode", value = "社区编号", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public MapMsgAndTableResultResponse<DeviceInfoResp> page(@RequestParam(defaultValue = "20") int pageSize,
                                                             @RequestParam(defaultValue = "1") int pageNum,
                                                             Integer id,
                                                             String deviceName,
                                                             String sn,
                                                             String brand,
                                                             String estatecode) {

        Map<String, Object> map = new HashMap<>(3);
        try {
            if (id == null) {
                //如果id不传，说明是获取页面，则要封装楼栋信息、小区信息、品牌信息
                map.put("estates", estateBiz.selectListAll());
                map.put("builds", buildBiz.selectListAll());
                map.put("brands", BrandEnum.getAllBrand());
            }
            Page<DeviceInfoResp> result = PageHelper.startPage(pageNum, pageSize);
            baseBiz.selectByAdditionForList(deviceName, sn, brand, estatecode, id);
            return new MapMsgAndTableResultResponse<DeviceInfoResp>(map, result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.info("------获取设备列表时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            e.printStackTrace();
            MapMsgAndTableResultResponse<DeviceInfoResp> resp = new MapMsgAndTableResultResponse<>();
            resp.setStatus(401);
            resp.setMessage("无法获取设备列表，请联系管理员");
            return resp;
        }
    }

    @ApiOperation("新增设备")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse addDevice(@RequestBody Device device) {
        ObjectRestResponse resp = new ObjectRestResponse();
        try {
            int result = baseBiz.insertDevice(device);
            return ResultHandler.objectRestResponseHandle(resp, result);
        } catch (Exception e) {
            log.info("------新增时发生异常------");
            log.info(e.getMessage());
            return resp.error();
        }
    }

    @ApiOperation("根据主键更新设备")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse updateDevice(@RequestBody Device device) {
        ObjectRestResponse resp = new ObjectRestResponse();
        try {
            int result = baseBiz.updateDevice(device);
            return ResultHandler.objectRestResponseHandle(resp, result);
        } catch (Exception e) {
            log.info("------更新时发生异常------");
            log.info(e.getMessage());
            return resp.error();
        }
    }

    @ApiOperation("根据主键物理删除设备")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse deleteDevice(@RequestBody String ids) {
        ObjectRestResponse resp = new ObjectRestResponse();
        try {
            List<Integer> deviceIds = IdUtils.getIds(ids);
            int result = baseBiz.deleteDevice(deviceIds);
            return ResultHandler.objectRestResponseHandle(resp, result);
        } catch (Exception e) {
            log.info("------删除时发生异常------");
            log.info(e.getMessage());
            return resp.error();
        }
    }
}
