package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.ServiceConfig;
import com.biocome.platform.basemanager.mapper.ServiceConfigMapper;
import com.biocome.platform.basemanager.vo.camera.ServiceConfigVo;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务配置表（视频设备管理）
 *
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-03 17:39:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceConfigBiz extends BaseBiz<ServiceConfigMapper,ServiceConfig> {

    Logger log = LoggerFactory.getLogger(this.getClass());

    /***
     * 服务配置列表
     * @param name
     * @param ip
     * @param pageNum
     * @param pageSize
     * @return
     */
    public TableResultResponse<ServiceConfigVo> configList(String name, String ip, int pageNum, int pageSize) {
        try {
            Page<ServiceConfigVo> page = PageHelper.startPage(pageNum, pageSize);
            mapper.selectServiceConfigList(name, ip);
            return new TableResultResponse<ServiceConfigVo>(page.getTotal(), page.getResult());
        }catch (Exception e){
            e.printStackTrace();
            log.info("查询服务配置列表失败，错误信息："+e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询服务配置列表失败，错误信息：数据库错误！");
        }
    }
}