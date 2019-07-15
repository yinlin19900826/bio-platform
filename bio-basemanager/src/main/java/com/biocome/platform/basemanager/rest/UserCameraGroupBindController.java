package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.UserCameraGroupBindBiz;
import com.biocome.platform.basemanager.entity.UserCameraGroupBind;
import com.biocome.platform.basemanager.vo.camera.AddGroupBindVo;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Api(value = "分配用户组", tags = {"分配用户组"})
@Controller
@RequestMapping("camera/userCameraGroupBind")
public class UserCameraGroupBindController extends BaseController<UserCameraGroupBindBiz,UserCameraGroupBind> {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserCameraGroupBindBiz biz;

    @ApiOperation("分配用户组")
    @ResponseBody
    @RequestMapping(value = "/addGroupBind", method = RequestMethod.POST)
    public ObjectRestResponse add2Group(@RequestBody AddGroupBindVo vo) {
        try {
            biz.addGroupBind(vo);
        }catch (Exception e){
            e.printStackTrace();
            log.info("分配用户组失败，错误信息："+e.getMessage());
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "分配用户组失败，错误信息："+e.getMessage());
        }
        return new ObjectRestResponse().success();
    }

    @ApiOperation("机构用户绑定列表")
    @ApiImplicitParam(name = "username",value = "用户名", paramType = "query", required = true)
    @ResponseBody
    @RequestMapping(value = "/bindList", method = RequestMethod.GET)
    public TableResultResponse<Integer> bindList(String username) {
        return biz.bindList(username);
    }
}