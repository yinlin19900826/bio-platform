package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.BuildDetailBiz;
import com.biocome.platform.wechatapplet.vo.build.BuildDetailResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 09:41
 */
@RestController
@RequestMapping("/buildDetail")
@Api(value = "楼栋详情相关", tags = {"楼栋详情相关"})
public class BuildDetailController {

    @Autowired
    BuildDetailBiz biz;

    @ApiOperation("根据用户编号获取相关楼栋")
    @ApiImplicitParam(name = "usercode", value = "用户编码", paramType = "body")
    @PostMapping("/getbuild")
    public ObjectRestResponse<List<BuildDetailResp>> getBuild(@RequestBody String usercode) {
        List<BuildDetailResp> resp = biz.getBuildByUsercode(usercode);
        return new ObjectRestResponse<>().data(resp);
    }
}
