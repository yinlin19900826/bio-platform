package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.inter.basemanager.biz.DictBiz;
import com.biocome.platform.inter.basemanager.entity.Dictionary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/16 14:01
 */
@Controller
@RequestMapping("/dict")
@Api(value = "字典", tags = {"字典操作"})
public class DictController extends BaseController<DictBiz, Dictionary> {
    @ApiOperation("获取字典信息")
    @RequestMapping(value = "/dictList", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(name = "dictCode", value = "字典编号", paramType = "query")})
    public List<Dictionary> dictList(@RequestParam String dictCode) {
        Dictionary dictionary = new Dictionary();
        dictionary.setDictCode(dictCode);
        return baseBiz.selectList(dictionary);
    }
}
