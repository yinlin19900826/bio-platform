package com.biocome.platform.guard.rest;

import com.biocome.platform.common.context.BaseContextHandler;
import com.biocome.platform.common.handler.ResultHandler;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.guard.biz.AdvertPlanBiz;
import com.biocome.platform.guard.constant.AdvertConstant;
import com.biocome.platform.guard.entity.AdvertPlan;
import com.biocome.platform.guard.vo.advert.AddAdvertPlanReq;
import com.biocome.platform.guard.vo.advert.AddvertPreviewReq;
import com.biocome.platform.guard.vo.advert.AdvertPlanPreviewResp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/31 09:54
 */
@Controller
@RequestMapping("/advertplan")
@Api(value = "广告计划操作", tags = {"广告计划操作"})
public class AdvertPlanController extends BaseController<AdvertPlanBiz, AdvertPlan> {
    Logger log = LoggerFactory.getLogger(AdvertPlanController.class);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加广告计划")
    public ObjectRestResponse addAdvertplan(@RequestBody AddAdvertPlanReq req) {
        try {
            return baseBiz.addAdvertplan(req);
        } catch (Exception e) {
            log.info("设备添加广告计划失败，{}" + e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("获取广告计划列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键ID", paramType = "query"),
            @ApiImplicitParam(name = "planname", value = "计划名称（可模糊查询）", paramType = "query"),
            @ApiImplicitParam(name = "isaudit", value = "审批状态", paramType = "query"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", paramType = "query"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", paramType = "query")
    })
    public TableResultResponse<AdvertPlan> page(@RequestParam(defaultValue = "20") int pageSize,
                                                @RequestParam(defaultValue = "1") int pageNum,
                                                Integer id,
                                                String planname,
                                                String isaudit,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date starttime,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endtime) {
        try {
            Page<AdvertPlan> result = PageHelper.startPage(pageNum, pageSize);
            baseBiz.getAdvertPlanList(id, planname, isaudit, starttime, endtime);
            return new TableResultResponse<AdvertPlan>(result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.info("------获取广告计划列表时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            TableResultResponse<AdvertPlan> resp = new TableResultResponse<>();
            resp.setStatus(401);
            resp.setMessage("无法获取广告计划列表，请联系管理员");
            return resp;
        }
    }

    @ApiOperation("批量删除广告计划")
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse deleteAdvertPlan(@RequestBody String ids) {
        ObjectRestResponse resp = new ObjectRestResponse();
        try {
            List<Integer> advertPlanIds = IdUtils.getIds(ids);
            int result = baseBiz.deleteAdvertPlan(advertPlanIds);
            return ResultHandler.objectRestResponseHandle(resp, result);
        } catch (Exception e) {
            log.info("------删除时发生异常------");
            log.info(e.getMessage());
            return resp.error();
        }
    }

    @ApiOperation("广告预览")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<AdvertPlanPreviewResp> previewAdvertPlan(@RequestBody AddvertPreviewReq req) {
        ObjectRestResponse<AdvertPlanPreviewResp> resp = new ObjectRestResponse<>();
        try {
            AdvertPlanPreviewResp previewAdvertPlan = baseBiz.getPreviewAdvertPlan(req.getId());
            resp.setData(previewAdvertPlan);
        } catch (Exception e) {
            log.info("------获取预览素材列表时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            resp.setStatus(401);
            resp.setMessage("无法获取广告计划列表，请联系管理员");
        }
        return resp;
    }

    @ApiOperation("广告计划审核")
    @RequestMapping(value = "/audit", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse auditAdvertPlan(@RequestBody AdvertPlan req) {
        BaseResponse resp = new BaseResponse();
        try {
            req.setAudittime(DateUtils.getCurrentDate());
            req.setAuditcode(BaseContextHandler.getUserID());
            req.setAuditname(BaseContextHandler.getUsername());
            baseBiz.updateSelectiveById(req);
        } catch (Exception e) {
            log.info("------审批广告计划时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            resp.setStatus(401);
            resp.setMessage("无法审批广告计划列表，请联系管理员");
            return resp;
        }
        //审批通过后
        if ((AdvertConstant.ADVERT_PLAN_ISAUDIT_PASS).equals(req.getIsaudit())) {
            try {
                baseBiz.rpcAdvert(req.getId());
            } catch (Exception e) {
                log.info("------下发广告计划时发生异常------");
                log.info("异常信息:{}", e.getMessage());
                resp.setStatus(401);
                resp.setMessage("无法下发广告计划，请联系管理员");
                resp.setStatus(401);
                resp.setMessage("无法正常下发广告计划，请联系管理员");
                return resp;
            }
        }
        return new BaseResponse();
    }

    @ApiOperation("根据codes获取楼栋社区信息列表")
    @RequestMapping(value = "/codesHandler", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<AdvertPlanPreviewResp> codesHandler(@RequestBody List<String> codes) {
        ObjectRestResponse<AdvertPlanPreviewResp> resp = new ObjectRestResponse<>();
        try {
            AdvertPlanPreviewResp advertPlanPreviewResp = new AdvertPlanPreviewResp();
            baseBiz.codeHandler(codes, advertPlanPreviewResp);
            resp.setData(advertPlanPreviewResp);
        } catch (Exception e) {
            log.info("------获取预览素材列表时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            resp.setStatus(401);
            resp.setMessage("无法获取广告计划列表，请联系管理员");
        }
        return resp;
    }
}
