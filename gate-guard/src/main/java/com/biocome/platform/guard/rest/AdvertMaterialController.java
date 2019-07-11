package com.biocome.platform.guard.rest;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.biz.AdvertMaterialBiz;
import com.biocome.platform.guard.entity.AdvertMaterial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: AdvertMaterialController
 * @Author: shenlele
 * @Date: 2019/5/30 16:19
 * @Description:
 */
@Controller
@RequestMapping("advertMaterial")
@Api(value = "广告素材", tags = {"广告素材操作"})
public class AdvertMaterialController extends BaseController<AdvertMaterialBiz, AdvertMaterial> {

    private Logger log = LoggerFactory.getLogger(AdvertMaterialController.class);

    @Autowired
    private AdvertMaterialBiz biz;

    @ApiOperation("根据所传广告素材信息查询")
    @ResponseBody
    @RequestMapping(value = "/selectByAttribute", method = RequestMethod.GET)
    public TableResultResponse<AdvertMaterial> selectByAttribute(@RequestParam(defaultValue = "20") int pageSize,
                                                                 @RequestParam(defaultValue = "1") int pageNum,
                                                                 AdvertMaterial model) {
        TableResultResponse<AdvertMaterial> res;
        try {
            res = biz.selectByAttribute(pageSize, pageNum, model);
        } catch (Exception e) {
            log.error("查询素材列表失败!失败信息为：" + e.getMessage());
            res = new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询素材列表失败!");
        }
        return res;
    }

    @ApiOperation("批量删除数据")
    @ApiImplicitParam(name = "idList", value = "id列表，逗号分隔", paramType = "query")
    @ResponseBody
    @RequestMapping(value = "/deleteList", method = RequestMethod.DELETE)
    public ObjectRestResponse deleteByIdList(String idList) {
        if (ValidateUtils.isNotEmpty(idList)) {
            try {
                return biz.deleteByIdList(idList);
            } catch (Exception e) {
                log.error("删除广告素材信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "删除广告素材信息失败!");
            }
        } else {
            log.error("删除广告素材信息失败,参数为空!");
            return new ObjectRestResponse<>().error();
        }
    }

    @ApiOperation("插入广告素材信息，检查素材名称是否唯一")
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ObjectRestResponse insert(@RequestBody AdvertMaterial model) {
        ObjectRestResponse res;
        if (ValidateUtils.isNotEmpty(model.getMaterialname()) && ValidateUtils.isNotEmpty(model.getType())) {
            try {
                res = biz.insertMaterial(model);
            } catch (Exception e) {
                log.error("插入广告素材信息失败，错误信息为：" + e.getMessage());
                return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "插入广告素材信息失败!");
            }
        } else {
            log.error("插入广告素材信息时参数不完整！");
            res = new ObjectRestResponse().error();
        }
        return res;
    }
}
