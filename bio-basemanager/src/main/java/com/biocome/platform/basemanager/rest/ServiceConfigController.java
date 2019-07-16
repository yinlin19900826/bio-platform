package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.ServiceConfigBiz;
import com.biocome.platform.basemanager.entity.ServiceConfig;
import com.biocome.platform.basemanager.vo.camera.ServiceConfigVo;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "视频服务配置", tags = {"视频服务配置"})
@Controller
@RequestMapping("serviceConfig")
public class ServiceConfigController extends BaseController<ServiceConfigBiz, ServiceConfig> {
    @Autowired
    ServiceConfigBiz serviceConfigBiz;

    @ApiOperation("增加视频通道")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody ServiceConfig entity){
        serviceConfigBiz.insertSelective(entity);
        return new ObjectRestResponse().success();
    }

    @ApiOperation("查询视频设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "ip", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<ServiceConfigVo> list(@RequestParam(defaultValue = "20") int pageSize,
                                                     @RequestParam(defaultValue = "1") int pageNum, String name, String ip) {
        return serviceConfigBiz.configList(name, ip, pageNum, pageSize);
    }


    @ApiOperation("修改视频通道信息")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<ServiceConfig> update(@RequestBody ServiceConfig entity){
        serviceConfigBiz.updateSelectiveById(entity);
        return new ObjectRestResponse();
    }

    @ApiOperation("删除视频设备信息")
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<ServiceConfig> remove(@PathVariable int id){
        serviceConfigBiz.deleteById(id);
        return new ObjectRestResponse();
    }
}