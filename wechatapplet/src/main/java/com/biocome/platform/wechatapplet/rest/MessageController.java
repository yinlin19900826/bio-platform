package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.MessageBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: MessageController
 * @Author: shenlele
 * @Date: 2019/7/31 09:21
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/message")
@Api(value = "消息通知相关操作", tags = {"消息通知相关操作"})
public class MessageController {

    private final MessageBiz biz;

    @Autowired
    public MessageController(MessageBiz biz) {
        this.biz = biz;
    }

    @ApiOperation("请求消息通知列表（0失败，1成功）")
    @ResponseBody
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public ObjectRestResponse selectMessage() {
        try {
            return biz.selectList();
        } catch (Exception e) {
            log.info("查询消息通知失败,错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("未获取到消息列表!");
        }
    }
}
