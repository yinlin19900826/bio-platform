package com.biocome.platform.guard.biz;

import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.utils.ElasticSearchUtil;
import com.biocome.platform.guard.utils.EsPage;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.alibaba.nacos.client.config.utils.JVMUtil.log;

/**
 * @ClassName: SearchBiz
 * @Author: zengqiang
 * @Date: 2019/8/27
 * @Description:
 */
@Service
@Slf4j
public class SearchBiz {
    @Value("${es.search.index}")
    String index;
    @Value("${es.search.doc}")
    String doc;
    @Value("${es.search.condition}")
    String condition;
    @Value("${es.search.fields}")
    String fields;
    @Value("${es.search.sortField}")
    String sortField;

    public TableResultResponse<Map<String, Object>> searchByName(List<String> types, String name, int pageSize, int pageNum) {
        TableResultResponse<Map<String, Object>> res = null;
        if(ValidateUtils.isEmpty(types)){
            return new TableResultResponse<Map<String, Object>>(CommonConstants.EX_ES_SEARCH_TYPE_IS_NULL, "请选择检索资源类型！");
        }
        if(ValidateUtils.isEmpty(name)){
            return new TableResultResponse<Map<String, Object>>(CommonConstants.EX_ES_SEARCH_NAME_IS_NULL, "请输入检索资源名称！");
        }
        try {
            QueryBuilder query = QueryBuilders.wildcardQuery(condition,"*"+name+"*");;
            EsPage page =  ElasticSearchUtil.searchDataPage(index, doc, pageNum, pageSize, query, fields, sortField, name);
            res = new TableResultResponse<Map<String, Object>>((long)page.getRecordCount(), page.getRecordList());
        }catch (Exception e){
            log.info("检索失败，数据错误!");
            e.printStackTrace();
            return new TableResultResponse<Map<String, Object>>(CommonConstants.EX_ES_DATA_ERR, "检索失败，数据错误！");
        }
        return res;
    }
}
