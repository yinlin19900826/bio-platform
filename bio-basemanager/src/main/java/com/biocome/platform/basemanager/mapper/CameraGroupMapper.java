package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.CameraGroup;
import com.biocome.platform.basemanager.vo.camera.AddGroupVo;
import com.biocome.platform.common.vo.UINodeVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 镜头分组
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 14:57:40
 */
public interface CameraGroupMapper extends Mapper<CameraGroup> {

    List<UINodeVo> selectableList();

    int add2Group(@Param("vo") AddGroupVo vo);

    void deleteByIds(@Param("ids") List<Integer> ids);

    int getDupilicationCount(@Param("parentId") Integer parentId, @Param("name") String name);

    void batchAdd2Group(@Param("vos") List<AddGroupVo> voList);
}
