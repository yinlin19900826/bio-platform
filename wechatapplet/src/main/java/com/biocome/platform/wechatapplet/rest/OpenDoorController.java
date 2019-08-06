package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.OpenDoorBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: OpenDoorController
 * @Author: shenlele
 * @Date: 2019/7/29 18:21
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping
@Api(value = "开门相关操作", tags = {"开门相关操作"})
public class OpenDoorController {

    private final OpenDoorBiz biz;

    @Autowired
    public OpenDoorController(OpenDoorBiz biz) {
        this.biz = biz;
    }

    @ApiOperation("远程开门（0失败，1成功）")
    @ResponseBody
    @RequestMapping(value = "/openDoor/{sn}", method = RequestMethod.POST)
    public BaseResponse openDoor(@PathVariable String sn) {
        try {
            String code = BaseContextHandler.getUsercode();
            return biz.openDoor(sn, code);
        } catch (Exception e) {
            log.info("远程开门失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("开门失败!");
        }
    }
}
