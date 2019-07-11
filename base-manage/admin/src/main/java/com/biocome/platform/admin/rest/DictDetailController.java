package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.DictDetailBiz;
import com.biocome.platform.admin.entity.DictDetail;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.tree.UINode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Api(value = "字典详情", tags = {"字典详情"})
@Controller
@RequestMapping("dictDetail")
public class DictDetailController extends BaseController<DictDetailBiz, DictDetail> {
    @Autowired
    DictDetailBiz dictDetailBiz;

    @ApiOperation("查询字典详情列表")
    @ApiImplicitParam(name = "name", value = "字典名称", paramType = "query", required = true)
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<DictDetail> list(String name){
        return dictDetailBiz.getListByName(name);
    }

    @ApiOperation("查询机构树")
    @RequestMapping(value = "/institutionTree",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<List<UINode>> institutionList(){
        List<UINode> list = new ArrayList<UINode>();
        list.add(dictDetailBiz.getInstitutionTree().getRoot());
        return new ObjectRestResponse<List<UINode>>().data(list);
    }
}