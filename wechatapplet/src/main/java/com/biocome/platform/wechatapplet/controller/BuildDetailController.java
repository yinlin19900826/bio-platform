package com.biocome.platform.wechatapplet.controller;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.BuildDetailBiz;
import com.biocome.platform.wechatapplet.vo.build.BuildDetailResp;
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
public class BuildDetailController {

    @Autowired
    BuildDetailBiz biz;

    @PostMapping("/getbuild")
    public ObjectRestResponse<List<BuildDetailResp>> getBuild(@RequestBody String usercode) {
        List<BuildDetailResp> resp = biz.getBuildByUsercode(usercode);
        return new ObjectRestResponse<>().data(resp);
    }
}
