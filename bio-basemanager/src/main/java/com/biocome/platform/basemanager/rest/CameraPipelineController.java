package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.CameraPipelineBiz;
import com.biocome.platform.basemanager.entity.CameraPipeline;
import com.biocome.platform.basemanager.vo.camera.CameraPipelineVo;
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

@Api(value = "通道管理", tags = {"通道管理"})
@Controller
@RequestMapping("cameraPipeline")
public class CameraPipelineController extends BaseController<CameraPipelineBiz, CameraPipeline> {
    @Autowired
    CameraPipelineBiz cameraPipelineBiz;

    @ApiOperation("增加视频通道")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody CameraPipeline entity){
        baseBiz.insertSelective(entity);
        return new ObjectRestResponse().success();
    }

    @ApiOperation("查询视频设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cameraId", value = "镜头id", paramType = "query", required = true),
            @ApiImplicitParam(name = "serialNo", value = "编号", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<CameraPipelineVo> list(@RequestParam(defaultValue = "20") int pageSize,
                                                      @RequestParam(defaultValue = "1") int pageNum, String cameraId, String serialNo, String name) {
        return cameraPipelineBiz.cameraPipelineList(cameraId, serialNo , name, pageNum, pageSize);
    }


    @ApiOperation("修改视频通道信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse update(@RequestBody CameraPipeline entity){
        baseBiz.updateSelectiveById(entity);
        return new ObjectRestResponse().success();
    }

    @ApiOperation("删除视频设备信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse remove(@PathVariable int id){
        baseBiz.deleteById(id);
        return new ObjectRestResponse().success();
    }
}