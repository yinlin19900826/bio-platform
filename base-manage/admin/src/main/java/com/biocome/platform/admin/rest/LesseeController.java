package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.LesseeBiz;
import com.biocome.platform.admin.vo.LesseeVo;
import com.biocome.platform.admin.entity.Lessee;
import com.biocome.platform.admin.vo.lesseecard.LesseeCardVo;
import com.biocome.platform.admin.vo.upload.ChangeLesseePicReq;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.ValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: LesseeController
 * @Author: shenlele
 * @Date: 2019/5/7 10:22
 * @Description:
 */
@Controller
@RequestMapping("lessee")
@Api(value = "租户", tags = {"租户操作"})
public class LesseeController extends BaseController<LesseeBiz, Lessee> {

    private Logger log = LoggerFactory.getLogger(LesseeController.class);

    @Autowired
    protected LesseeBiz lesseeBiz;

    @ApiOperation(value = "获取租户列表,查询所有参数传null", notes = "传参数时，agetype年龄段设置为0:16岁以下，1:16-65岁，2:65岁以上,出生日期开始结束时间不需要传")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<Lessee> list(@RequestParam(defaultValue = "20") int pageSize,
                                            @RequestParam(defaultValue = "1") int pageNum, LesseeVo lesseeVo) {
        return lesseeBiz.selectByAttribute(pageSize, pageNum, lesseeVo);
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParam(name = "idList", value = "id列表，逗号分隔", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/deleteList", method = RequestMethod.DELETE)
    public ObjectRestResponse<Lessee> deleteLessee(String idList) {
        if (ValidateUtils.isNotEmpty(idList)) {
            try {
                return lesseeBiz.deleteLessee(idList);
            } catch (Exception e) {
                log.error("删除租户信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除租户信息失败!");
            }
        } else {
            log.error("删除租户信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("租户开卡信息添加")
    @ResponseBody
    @RequestMapping(value = "/addLesseeCardList", method = RequestMethod.POST)
    public BaseRpcResponse addLesseeCardList(@RequestBody LesseeCardVo vo) {
        try {
            return baseBiz.addLesseeCardList(vo);
        } catch (Exception e) {
            log.info("------租户开卡信息添加时发生异常------");
            log.info("异常信息:{}", e.getMessage());
            return new BaseRpcResponse().failure();
        }
    }

    @ApiOperation("保存租户信息，判断租户编号是否唯一")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse insert(@RequestBody Lessee lessee) {
        ObjectRestResponse res;
        try {
            res = lesseeBiz.insertLessee(lessee);
        } catch (Exception e) {
            log.error("保存失败！错误信息为：" + e.getMessage());
            return new ObjectRestResponse(204, "保存失败！");
        }
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "修改租户图片")
    @RequestMapping(value = "/changeLesseePic", method = RequestMethod.POST)
    public BaseRpcResponse changeLesseePic(@RequestBody ChangeLesseePicReq req) {
        try {
            return baseBiz.changeLesseePic(req);
        } catch (Exception e) {
            log.error("修改出现异常！错误信息为：" + e.getMessage());
            return new BaseRpcResponse().error();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
