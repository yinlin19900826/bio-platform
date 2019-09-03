package com.biocome.platform.guard.rest;

/**
 * @ClassName: SearchController
 * @Author: zengqiang
 * @Date: 2019/8/27
 * @Description:
 */

import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.guard.biz.SearchBiz;
import com.biocome.platform.guard.utils.EsPage;
import com.biocome.platform.guard.vo.search.SearchResultVo;
import com.biocome.platform.inter.basemanager.vo.admin.SimpleAdminVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AdminController
 * @Author: zengqiang
 * @Date: 2019/8/8
 * @Description:
 */
@Controller
@RequestMapping("search")
@Slf4j
@Api(value = "查询", tags = {"查询操作"})
public class SearchController {
    @Autowired
    private SearchBiz searchBiz;

    @ApiOperation("根据名称检索")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "types", value = "检索类型", paramType = "query" )},
            {@ApiImplicitParam(name = "name", value = "名称", paramType = "query" )}
    )
    @ResponseBody
    @RequestMapping(value = "/searchByName", method = RequestMethod.POST)
    public TableResultResponse<Map<String, Object>> searchByName(List<String> types, String name, @RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNum) {
        return searchBiz.searchByName(types, name, pageSize, pageNum);
    }
}
