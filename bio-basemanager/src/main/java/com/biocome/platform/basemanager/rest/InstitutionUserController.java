package com.biocome.platform.basemanager.rest;

import com.biocome.platform.basemanager.biz.InstitutionUserBiz;
import com.biocome.platform.basemanager.entity.InstitutionUser;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.tree.UINode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Api(value = "机构用户", tags = {"机构用户"})
@Controller
@RequestMapping("institutionUser")
public class InstitutionUserController extends BaseController<InstitutionUserBiz, InstitutionUser> {
    @Autowired
    InstitutionUserBiz institutionUserBiz;

    @ApiOperation("查询机构用户")
    @RequestMapping(value = "/userTree",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<List<UINode>> list() {
        List<UINode> list = new ArrayList<UINode>();
        list.add(institutionUserBiz.getUserTree().getRoot());
        return new ObjectRestResponse<List<UINode>>().data(list);
    }
}