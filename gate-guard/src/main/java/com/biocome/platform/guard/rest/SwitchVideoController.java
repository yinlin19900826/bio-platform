package com.biocome.platform.guard.rest;


import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.guard.biz.SwitchVideoBiz;
import com.biocome.platform.guard.vo.showmanage.SwitchVideoVo;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yinlin
 * @date 2019/8/28 13:57
 */
@Controller
@RequestMapping("switch")
@Api(value = "切换视频操作", tags = {"切换视频相关操作"})
public class SwitchVideoController {
    private Logger log = LoggerFactory.getLogger(SwitchVideoController.class);
    @Autowired
    private SwitchVideoBiz switchVideoBiz;

    @ApiOperation("切换直播视频")
    @ApiImplicitParam(name = "buildcode", value = "楼栋编码", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/livevideo", method = RequestMethod.GET)
    public ObjectRestResponse<List<SwitchVideoVo>> switchLiveVideo(String buildcode) {
        try {
            List<SwitchVideoVo> list = switchVideoBiz.switchLiveVideo(buildcode);
            return new ObjectRestResponse<>(true).data(list);
        } catch (Exception e) {
            log.error("切换直播视频操作失败，错误信息为：" + e.getMessage());
            return new ObjectRestResponse<>().failure();
        }
    }

}
