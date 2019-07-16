package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.TreeDistrictBiz;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ShowManageController
 * @Author: shenlele
 * @Date: 2019/5/17 13:49
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("show")
@Api(value = "展示", tags = {"展示操作"})
public class ShowManageController {

    @Autowired
    private TreeDistrictBiz treeDistrictBiz;

    @ApiOperation("基础管理查询展示导航树操作")
    @ApiImplicitParam(name = "parentcode", value = "行政区划代码", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/treeformanage", method = RequestMethod.GET)
    public ObjectRestResponse<List<TreeDistrictVO>> treeformanage(String parentcode) {
        try {
            List<TreeDistrictVO> list = treeDistrictBiz.getTree(parentcode);
            return new ObjectRestResponse<>(true).data(list);
        } catch (Exception e) {
            log.error("基础管理查询展示导航树操作失败，错误信息为：" + e.getMessage());
            return new ObjectRestResponse<>().failure();
        }
    }
}
