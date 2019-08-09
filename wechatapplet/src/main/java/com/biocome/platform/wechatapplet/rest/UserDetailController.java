package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.UserDetailBiz;
import com.biocome.platform.wechatapplet.vo.userdetail.CompleteVo;
import com.biocome.platform.wechatapplet.vo.userdetail.UserDetailReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hxy
 * @date 2019/8/1 10:57
 */
@RestController
@RequestMapping("userDetail")
@Api(value = "用户相关", tags = {"用户相关"})
public class UserDetailController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailBiz biz;

    @PostMapping("insertUserDetail")
    public ObjectRestResponse insertUserDetail(@RequestBody UserDetailReq req) {
        ObjectRestResponse resp = new ObjectRestResponse().failure();
        try {
            resp = biz.insertUserDetail(req);
        } catch (Exception e) {
            log.error("用户注册基本信息失败：{}", e.getMessage());
            resp.customError("发生异常");
        }
        return resp;
    }

    @ApiOperation("完善信息方法（0失败，1成功）")
    @ResponseBody
    @PostMapping("/complete")
    public BaseResponse complete(@RequestBody CompleteVo vo) {
        try {
            String code = BaseContextHandler.getUsercode();
            vo.setUsercode(code);
            return biz.updateSelectiveById(vo);
        } catch (Exception e) {
            log.info("完善信息方法失败：{}", e.getMessage());
            return new ObjectRestResponse().customError("完善信息失败!");
        }
    }

}
