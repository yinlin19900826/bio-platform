package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.wechatapplet.biz.OpenDoorBiz;
import com.biocome.platform.wechatapplet.vo.opendoor.OpenDoorVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/openDoor")
@Api(value = "开门相关操作", tags = {"开门相关操作"})
public class OpenDoorController {

    private final OpenDoorBiz biz;

    @Autowired
    public OpenDoorController(OpenDoorBiz biz) {
        this.biz = biz;
    }

    @ApiOperation("远程开门（0失败，1成功）")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public String openDoor(@RequestBody OpenDoorVo req) {
        BaseRpcResponse resp = biz.openDoor(req);
        log.info("APP远程开门返回数据为：{}", JsonUtils.beanToJson(resp));
        return resp.getResult();
    }
}
