package com.biocome.video.controller;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.video.biz.TreeDistrictBiz;
import com.biocome.video.vo.TreeDistrictVO;
import com.biocome.video.vo.TreeVideoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ShowVideoController
 * @Author: yinlin
 * @Date: 2019/6/17 13:49
 * @Description:
 */
@Controller
@RequestMapping("show")
@Api(value = "视频", tags = {"视频监控回放操作"})
public class ShowVideoController {

    private Logger log = LoggerFactory.getLogger(ShowVideoController.class);

    @Autowired
    private TreeDistrictBiz treeDistrictBiz;

    @ApiOperation("视频监控和视频回放展示导航树操作")
    @ResponseBody
    @RequestMapping(value = "/treeforvideo", method = RequestMethod.GET)
    public ObjectRestResponse<List<TreeDistrictVO>> treeforvideo() {
        String parentcode = "0";
        try {
            List<TreeVideoVO> list = treeDistrictBiz.getVideoTree(parentcode);
            return new ObjectRestResponse<>(true).data(list);
        } catch (Exception e) {
            log.error("视频监控和视频回放展示导航树操作失败，错误信息为：" + e.getMessage());
            return new ObjectRestResponse<>().failure();
        }
    }
}
