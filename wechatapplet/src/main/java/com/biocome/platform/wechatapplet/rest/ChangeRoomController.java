package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.wechatapplet.biz.CardManageBiz;
import com.biocome.platform.wechatapplet.biz.ChangeRoomBiz;
import com.biocome.platform.wechatapplet.biz.RefundRentBiz;
import com.biocome.platform.wechatapplet.entity.Fault;
import com.biocome.platform.wechatapplet.vo.common.CardManageVo;
import com.biocome.platform.wechatapplet.vo.common.ChangeRoomVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ChangeRoomController
 * @Author: yinlin
 * @Date: 2019/8/8 13:49
 * @Description:
 */
@Controller
@RequestMapping("changeroom")
@Api(value = "更改房间号", tags = {"更改房间号"})
public class ChangeRoomController extends BaseController<ChangeRoomBiz, CardManageVo> {

    private Logger log = LoggerFactory.getLogger(ChangeRoomController.class);

    @Autowired
    private ChangeRoomBiz changeRoomBiz;

    @ApiOperation("更改租户房间号(0失败，1成功)")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse changeRoom( @RequestBody ChangeRoomVo changeRoomVo) {
        try {
            return changeRoomBiz.changeRoom(changeRoomVo);
        } catch (Exception e) {
            log.info("更改租户房间号失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("更改租户房间号失败!");
        }
    }


}
