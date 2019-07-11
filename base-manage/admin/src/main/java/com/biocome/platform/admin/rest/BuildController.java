package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.BuildBiz;
import com.biocome.platform.admin.entity.Build;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.ValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: BuildController
 * @Author: shenlele
 * @Date: 2019/5/6 19:36
 * @Description:
 */
@Controller
@RequestMapping("build")
@Api(value = "楼栋", tags = {"楼栋操作"})
public class BuildController extends BaseController<BuildBiz, Build> {

    private Logger log = LoggerFactory.getLogger(BuildController.class);

    @Autowired
    protected BuildBiz buildBiz;

    @ApiOperation("获取楼栋列表,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "buildaddress", value = "楼栋地址", paramType = "query"),
            @ApiImplicitParam(name = "buildname", value = "楼栋名称", paramType = "query")})
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<Build> list(@RequestParam(defaultValue = "20") int pageSize,
                                           @RequestParam(defaultValue = "1") int pageNum,
                                           String buildaddress, String buildname) {
        return buildBiz.selectByAttribute(pageSize, pageNum, buildaddress, buildname);
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParam(name = "idList", value = "id列表，逗号分隔", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/deleteList", method = RequestMethod.DELETE)
    public ObjectRestResponse<Build> updateById(String idList) {
        if (ValidateUtils.isNotEmpty(idList)) {
            try {
                return buildBiz.deleteBuild(idList);
            } catch (Exception e) {
                log.error("删除楼栋信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除楼栋信息失败!");
            }
        } else {
            log.error("删除楼栋信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("根据所传楼栋信息查询")
    @ApiImplicitParam(name = "build", value = "想怎么传怎么传，so happy", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/selectByBuild", method = RequestMethod.GET)
    public TableResultResponse<Build> selectByBuild(@RequestParam(defaultValue = "20") int pageSize,
                                                    @RequestParam(defaultValue = "1") int pageNum,
                                                    Build build) {
        return buildBiz.selectByBuild(pageSize, pageNum, build);
    }

    @ApiOperation("保存楼栋信息，判断楼栋编号是否唯一")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse insert(@RequestBody Build build) {
        ObjectRestResponse res;
        try {
            res = buildBiz.insertBuild(build);
        } catch (Exception e) {
            log.error("保存失败！错误信息为：" + e.getMessage());
            return new ObjectRestResponse(204, "保存失败！");
        }
        return res;
    }
}
