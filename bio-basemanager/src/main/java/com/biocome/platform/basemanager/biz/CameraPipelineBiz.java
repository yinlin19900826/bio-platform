package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.Camera;
import com.biocome.platform.basemanager.entity.CameraPipeline;
import com.biocome.platform.basemanager.mapper.CameraPipelineMapper;
import com.biocome.platform.basemanager.vo.camera.CameraPipelineVo;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class CameraPipelineBiz extends BaseBiz<CameraPipelineMapper, CameraPipeline> {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CameraBiz cameraBiz;

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
    public TableResultResponse<CameraPipelineVo> cameraPipelineList(int cameraId, String serialNo, String name, int pageNum, int pageSize) {
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

    /**
     * 添加通道
     * @param entity
     * @return
     */
    public BaseResponse add(CameraPipeline entity) {
        Integer cameraId = entity.getCameraId();
        if(ValidateUtils.isEmpty(cameraId)){
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "参数：视频设备id不能为空！");
        }
        Camera camera = cameraBiz.selectById(cameraId);
        if(ValidateUtils.isEmpty(camera)){
            return new ObjectRestResponse(CommonConstants.EX_OTHER_CODE, "未找到对应的视频设备！");
        }
        entity.setVcnIp(camera.getLanIp());
        mapper.insertSelective(entity);
        return new ObjectRestResponse().success();
    }
}