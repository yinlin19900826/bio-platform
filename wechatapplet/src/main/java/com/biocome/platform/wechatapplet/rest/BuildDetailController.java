package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.biz.BuildDetailBiz;
import com.biocome.platform.wechatapplet.vo.build.BuildDetailResp;
import com.biocome.platform.wechatapplet.vo.build.HouseResp;
import com.biocome.platform.wechatapplet.vo.build.LesseeResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 09:41
 */
@Slf4j
@RestController
@RequestMapping("/buildDetail")
@Api(value = "楼栋详情相关", tags = {"楼栋详情相关"})
public class BuildDetailController {

    @Autowired
    BuildDetailBiz biz;

    @ApiOperation("根据用户编号获取相关楼栋")
    @GetMapping("/getbuild")
    public ObjectRestResponse<List<BuildDetailResp>> getBuild() {
        String usercode = BaseContextHandler.getUsercode();
        List<BuildDetailResp> resp = biz.getBuildByUsercode(usercode);
        return new ObjectRestResponse<>().data(resp);
    }

    @ApiOperation("根据楼栋编号获取房间列表")
    @GetMapping("/houseList/{buildCode}")
    public ObjectRestResponse houseList(@PathVariable String buildCode) {
        try {
            List<HouseResp> resp = biz.selectHouseResp(buildCode);
            return new ObjectRestResponse<>().success().data(resp);
        } catch (Exception e) {
            log.info("获取房间列表失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("获取房间列表失败!");
        }
    }

    @ApiOperation("根据房屋编号获取租户列表（分负责人与租户返回）")
    @GetMapping("/lesseeList/{houseCode}")
    public ObjectRestResponse lesseeList(@PathVariable String houseCode) {
        try {
            LesseeResp resp = biz.selectLesseeResp(houseCode);
            return new ObjectRestResponse().success().data(resp);
        } catch (Exception e) {
            log.info("获取租户列表失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().customError("获取租户列表失败!");
        }
    }
}
