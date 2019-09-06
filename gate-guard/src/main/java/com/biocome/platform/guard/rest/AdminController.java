package com.biocome.platform.guard.rest;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.guard.biz.AdminBiz;
import com.biocome.platform.inter.basemanager.entity.Landlord;
import com.biocome.platform.inter.basemanager.vo.admin.SimpleAdminVo;
import com.biocome.platform.inter.basemanager.vo.upload.ChangeLesseePicReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: AdminController
 * @Author: zengqiang
 * @Date: 2019/8/8
 * @Description:
 */
@Controller
@RequestMapping("admin")
@Slf4j
@Api(value = "管理员", tags = {"管理员操作"})
public class AdminController extends BaseController<AdminBiz, Landlord> {

    @Autowired
    private AdminBiz adminBiz;

    @ApiOperation("管理员管理列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "管理员名称", paramType = "query" ),
            @ApiImplicitParam(name = "type", value = "管理员类型", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/simpleAdminList", method = RequestMethod.GET)
    public TableResultResponse<SimpleAdminVo> simpleAdminList(String username, Integer type,
                                                              @RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNum) {
        return adminBiz.simpleAdminList(username,type, pageNum, pageSize);
    }

    @ResponseBody
    @ApiOperation(value = "修改管理员图片")
    @RequestMapping(value = "/changeAdminPic", method = RequestMethod.POST)
    public BaseRpcResponse changeAdminPic(@RequestBody ChangeLesseePicReq req) {
        try {
            return adminBiz.changePic(req);
        } catch (Exception e) {
            log.error("修改出现异常！错误信息为：" + e.getMessage());
            return new BaseRpcResponse().error();
        }
    }

    @ApiOperation("添加管理员")
    @ResponseBody
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public BaseResponse addAdmin(@RequestBody Landlord landlord) {
        BaseResponse res;
        try {
            res = adminBiz.addAdmin(landlord);
        } catch (Exception e) {
            log.error("保存失败！错误信息为：" + e.getMessage());
            return new BaseResponse(204, "保存失败！");
        }
        return res;
    }

}
