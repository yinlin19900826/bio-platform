package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.StreamMediaBiz;
import com.biocome.platform.basemanager.entity.Camera;
import com.biocome.platform.basemanager.entity.StreamMedia;
import com.biocome.platform.basemanager.vo.camera.StreamMediaVo;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "流媒体", tags = {"流媒体"})
@Controller
@RequestMapping("camera/streamMedia")
public class StreamMediaController extends BaseController<StreamMediaBiz, StreamMedia> {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StreamMediaBiz streamMediaBiz;

    @ApiOperation("增加流媒体信息")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody StreamMedia entity){
        baseBiz.insertSelective(entity);
        return new ObjectRestResponse<Camera>();
    }

    @ApiOperation("查询流媒体列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "ip", paramType = "query"),
            @ApiImplicitParam(name = "cameraId", value = "设备id", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<StreamMediaVo> list(@RequestParam(defaultValue = "20") int pageSize,
                                                   @RequestParam(defaultValue = "1") int pageNum, String name, String ip, Integer cameraId) {
        return streamMediaBiz.streamMediaList(name, ip, cameraId, pageNum, pageSize);
    }


    @ApiOperation("删除流媒体信息")
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<StreamMedia> remove(@PathVariable int id){
        baseBiz.deleteById(id);
        return new ObjectRestResponse();
    }

    @ApiOperation("关联流媒体信息")
    @RequestMapping(value = "/bindCamera/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse bindCamera(@PathVariable int id, @RequestBody List<Integer> ids){
        try {
            streamMediaBiz.bindCamera(id, ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("关联流媒体信息失败，错误信息："+e.getMessage());
        }
        return new ObjectRestResponse().success();
    }
}