package com.biocome.platform.guard.rest;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.MapMsgAndTableResultResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.guard.biz.DoorDeviceCardBiz;
import com.biocome.platform.inter.basemanager.vo.upload.ChangeLesseePicReq;
import com.biocome.platform.inter.basemanager.LesseeCardMsgResp;
import com.biocome.platform.inter.basemanager.vo.card.*;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseeCardVo;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListReq;
import com.biocome.platform.inter.basemanager.vo.lesseecard.LesseecardListResp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hxy
 * @date 2019/7/11 17:56
 */
@Controller
@RequestMapping("/doordevicecard")
@Api(value = "门禁卡管理相关操作", tags = {"门禁卡管理相关操作"})
public class DoorDeviceCardController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DoorDeviceCardBiz doorDeviceCardBiz;

    @ApiOperation("获取租户开卡信息,查询所有参数传null")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户姓名", paramType = "query"),
            @ApiImplicitParam(name = "idNumber", value = "证件号码", paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别", paramType = "query"),
            @ApiImplicitParam(name = "buildName", value = "房屋地址", paramType = "query"),
            @ApiImplicitParam(name = "estateName", value = "小区姓名", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/lesseeCardMsgPage", method = RequestMethod.GET)
    public MapMsgAndTableResultResponse<LesseeCardMsgResp> lesseeCardMsgPage(@RequestParam(defaultValue = "20") int pageSize,
                                                                             @RequestParam(defaultValue = "1") int pageNum,
                                                                             String username,
                                                                             String idNumber,
                                                                             Integer sex,
                                                                             String buildName,
                                                                             String estateName) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            Page<LesseeCardMsgResp> result = PageHelper.startPage(pageNum, pageSize);
            doorDeviceCardBiz.selectByAdditionForLesseeCardMsgPage(username, idNumber, sex, buildName, estateName);
            return new MapMsgAndTableResultResponse<LesseeCardMsgResp>(map, result.getTotal(), result.getResult());
        } catch (Exception e) {
            log.info("------获取租户开卡信息时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            MapMsgAndTableResultResponse<LesseeCardMsgResp> resp = new MapMsgAndTableResultResponse<>();
            resp.setStatus(401);
            resp.setMessage("无法获取租户开卡信息，请联系管理员");
            return resp;
        }
    }

    @ApiOperation("租户开卡信息添加")
    @ResponseBody
    @RequestMapping(value = "/addLesseeCardList", method = RequestMethod.POST)
    public BaseRpcResponse addLesseeCardList(@RequestBody LesseeCardVo vo) {
        try {
            return doorDeviceCardBiz.addLesseeCardList(vo);
        } catch (Exception e) {
            log.info("------租户开卡信息添加时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            return new BaseRpcResponse().failure();
        }
    }

    @ResponseBody
    @ApiOperation(value = "修改租户图片")
    @RequestMapping(value = "/changeLesseePic", method = RequestMethod.POST)
    public BaseResponse changeLesseePic(@RequestBody ChangeLesseePicReq req) {
        try {
            return doorDeviceCardBiz.changeLesseePic(req);
        } catch (Exception e) {
            log.error("修改出现异常！错误信息为：" + e.getMessage());
            BaseResponse resp = new BaseResponse(500,"发生异常");
            return resp;
        }
    }
    @ApiOperation("开卡")
    @ResponseBody
    @RequestMapping(value = "/openCard", method = RequestMethod.POST)
    public BaseRpcResponse openCard(@RequestBody OpenCardVo req) {
        return doorDeviceCardBiz.openCard(req);
    }

    @ApiOperation("批量开卡")
    @ResponseBody
    @RequestMapping(value = "/openbulk", method = RequestMethod.POST)
    public List<OpenblukResp> openbulk(@RequestBody OpenblukVo req) {
        return doorDeviceCardBiz.openbulk(req);
    }

    @ApiOperation("注销卡")
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseRpcResponse logout(@RequestBody LogoutCardVo req) {
        return doorDeviceCardBiz.logoutCard(req);
    }

    @ApiOperation("禁用/恢复卡")
    @ResponseBody
    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public BaseRpcResponse manager(@RequestBody ManagerCardVo req) {
        return doorDeviceCardBiz.managerCard(req);
    }

    @ApiOperation("根据楼栋编号获取租户和卡信息")
    @ResponseBody
    @RequestMapping(value = "/lesseecardList", method = RequestMethod.POST)
    public TableResultResponse<LesseecardListResp> lesseecardList(@RequestBody LesseecardListReq req) throws Exception{
        return doorDeviceCardBiz.selectLesseecardList(req);
    }

}
