package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.StreamMedia;
import com.biocome.platform.basemanager.mapper.StreamMediaMapper;
import com.biocome.platform.basemanager.vo.camera.StreamMediaVo;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流媒体服务
 *
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-03 17:39:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StreamMediaBiz extends BaseBiz<StreamMediaMapper,StreamMedia> {
    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 流媒体列表
     * @param name
     * @param ip
     * @param cameraId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public TableResultResponse<StreamMediaVo> streamMediaList(String name, String ip, Integer cameraId, int pageNum, int pageSize) {
        try {
            Page<StreamMediaVo> page = PageHelper.startPage(pageNum, pageSize);
            mapper.selectStraemMediaList(name, ip, cameraId);
            return new TableResultResponse<StreamMediaVo>(page.getTotal(), page.getResult());
        }catch (Exception e){
            e.printStackTrace();
            log.info("查询服务配置列表失败，错误信息："+e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询服务配置列表失败，错误信息：数据库错误！");
        }
    }

    public void bindCamera(int cameraId, List<Integer> ids) throws Exception{
        mapper.bindCamera(cameraId, ids);
    }
}