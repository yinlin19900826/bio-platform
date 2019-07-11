package com.biocome.platform.admin.biz;

import com.biocome.platform.admin.mapper.CameraPipelineMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.vo.camera.CameraPipelineVo;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.biocome.platform.admin.entity.CameraPipeline;
import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

/**
 * 视频通道表
 *
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-03 17:39:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CameraPipelineBiz extends BaseBiz<CameraPipelineMapper,CameraPipeline> {

    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 查找通道列表
     *
     * @param cameraId
     * @param serialNo
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    public TableResultResponse<CameraPipelineVo> cameraPipelineList(String cameraId, String serialNo, String name, int pageNum, int pageSize) {
        try {
            Page< CameraPipelineVo> page = PageHelper.startPage(pageNum, pageSize);
            mapper.selectCameraPiplineList(cameraId, serialNo, name);
            return new TableResultResponse<CameraPipelineVo>(page.getTotal(), page.getResult());
        }catch (Exception e){
            e.printStackTrace();
            log.info("查询通道列表失败，错误信息："+e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询通道列表失败，错误信息：数据库错误！");
        }
    }
}