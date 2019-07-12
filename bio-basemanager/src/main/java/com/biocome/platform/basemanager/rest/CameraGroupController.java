package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.annotation.NoRepeatSubmit;
import com.biocome.platform.basemanager.biz.CameraGroupBiz;
import com.biocome.platform.basemanager.entity.CameraGroup;
import com.biocome.platform.basemanager.vo.camera.AddGroupVo;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.tree.UINode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "基础镜头组", tags = {"基础镜头组"})
@Controller
@RequestMapping("cameraGroup")
public class CameraGroupController extends BaseController<CameraGroupBiz,CameraGroup> {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CameraGroupBiz cameraGroupBiz;

    @ApiOperation("增加基础镜头组节点")
    @NoRepeatSubmit
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody CameraGroup entity){
        return cameraGroupBiz.insertEntity(entity);
    }

    @ApiOperation("修改基础镜头组节点")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse update(@RequestBody CameraGroup entity){
        cameraGroupBiz.updateSelectiveById(entity);
        return new ObjectRestResponse().success();
    }

    @ApiOperation("查询基础镜头组树")
    @ResponseBody
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ObjectRestResponse<List<UINode>> tree() {
        List<UINode> list = new ArrayList<UINode>();
        list.add(cameraGroupBiz.getTree().getRoot());
        return new ObjectRestResponse<List<UINode>>().data(list);
    }

    @ApiOperation("重命名节点")
    @ApiImplicitParam(name = "name", value = "名称", paramType = "query", required = true)
    @RequestMapping(value = "/rename/{id}",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse rename(@PathVariable Integer id, String name){
        return cameraGroupBiz.rename(id, name);
    }

    @ApiOperation("查询可选镜头组树")
    @ResponseBody
    @RequestMapping(value = "/selectableTree", method = RequestMethod.GET)
    public ObjectRestResponse<List<UINode>> selectableTree() {
        List<UINode> list = new ArrayList<UINode>();
        list.add(cameraGroupBiz.getSelectableTree().getRoot());
        return new ObjectRestResponse<List<UINode>>().data(list);
    }

    @ApiOperation("添加到基础镜头组")
    @ResponseBody
    @RequestMapping(value = "/add2Group", method = RequestMethod.POST)
    public ObjectRestResponse add2Group(@RequestBody List<AddGroupVo> vos) {
        try {
            cameraGroupBiz.add2Group(vos);
        }catch (Exception e){
            e.printStackTrace();
            log.info("添加到基础镜头失败，错误信息："+e.getMessage());
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "添加到基础镜头失败，错误信息："+e.getMessage());
        }
        return new ObjectRestResponse().success();
    }

    @ApiOperation("批量删除基础镜头组节点")
    @RequestMapping(value = "/removeGroup/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse removeGroup(@PathVariable Integer id){
        try {
            cameraGroupBiz.removeGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("添加到基础镜头失败，错误信息："+e.getMessage());
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "批量删除基础镜头失败，错误信息："+e.getMessage());
        }
        return new ObjectRestResponse().success();
    }
}