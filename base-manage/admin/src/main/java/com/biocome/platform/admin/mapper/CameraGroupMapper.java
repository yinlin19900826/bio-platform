package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.CameraGroup;
import com.biocome.platform.admin.vo.camera.AddGroupVo;
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

    void add2Group(@Param("vos") List<AddGroupVo> vos);

    void deleteByIds(@Param("ids") List<Integer> ids);

    int getDupilicationCount(@Param("parentId") Integer parentId, @Param("name") String name);
}
