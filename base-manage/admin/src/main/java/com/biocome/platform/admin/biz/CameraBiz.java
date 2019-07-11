package com.biocome.platform.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.biocome.platform.admin.vo.camera.CameraVo;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.biocome.platform.admin.entity.Camera;
import com.biocome.platform.admin.mapper.CameraMapper;
import com.biocome.platform.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

/**
 * 视频设备表
 *
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-31 14:37:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CameraBiz extends BaseBiz<CameraMapper,Camera> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 查询视频设备列表
     *
     * @param institutioncode
     * @param name
     * @param ip
     * @param pageNum
     * @param pageSize
     * @return
     */
    public TableResultResponse<CameraVo> cameraList(String institutioncode, String name, String ip, int pageNum, int pageSize) {
        try {
            Page<CameraVo> page = PageHelper.startPage(pageNum, pageSize);
            mapper.selectCameraList(institutioncode, name, ip);
            return new TableResultResponse<CameraVo>(page.getTotal(), page.getResult());
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询视频设备失败，错误信息：数据库错误！");
        }
    }

}