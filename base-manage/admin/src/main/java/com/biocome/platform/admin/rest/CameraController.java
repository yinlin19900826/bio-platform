package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.CameraBiz;
import com.biocome.platform.admin.annotation.NoRepeatSubmit;
import com.biocome.platform.admin.entity.Camera;
import com.biocome.platform.admin.vo.camera.CameraVo;
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

@Controller
@RequestMapping("camera")
@Api(value = "视频设备", tags = {"视频设备"})
public class CameraController extends BaseController<CameraBiz,Camera> {
    @Autowired
    CameraBiz cameraBiz;

    @ApiOperation("增加视频设备")
    @NoRepeatSubmit
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody Camera entity){
        baseBiz.insertSelective(entity);
        return new ObjectRestResponse().success();
    }

    @ApiOperation("查询视频设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "institutioncode", value = "机构编码", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "设备名称", paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "设备ip", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<CameraVo> list(@RequestParam(defaultValue = "20") int pageSize,
                                              @RequestParam(defaultValue = "1") int pageNum, String institutioncode, String name, String ip) {
        return cameraBiz.cameraList(institutioncode,name , ip, pageNum, pageSize);
    }


    @ApiOperation("修改视频设备信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Camera> update(@RequestBody Camera entity){
        baseBiz.updateSelectiveById(entity);
        return new ObjectRestResponse().success();
    }

    @ApiOperation("删除视频设备信息")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse remove(@PathVariable int id){
        baseBiz.deleteById(id);
        return new ObjectRestResponse().success();
    }
}