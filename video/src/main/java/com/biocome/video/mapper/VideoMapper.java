package com.biocome.video.mapper;

import com.biocome.video.entity.Video;
import com.biocome.video.vo.TreeVideoVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: VideoMapper
 * @Author: yinlin
 * @Date: 2019/6/3 13:58
 * @Description:
 */
public interface VideoMapper extends Mapper<Video> {
    /**
     * 根据所属行政区划代码查询所有小区，构建树
     *
     * @param
     *
     * @Date 2019/6/3 11:40
     */
   // List<TreeVideoVO> selectByTree(@Param("areacode") String areacode);
    List<TreeVideoVO> selectsixTree(@Param("type") Integer type, @Param("parentcode") String parentcode);

    List<TreeVideoVO> selectTree(@Param("type") Integer type, @Param("parentcode") String parentcode);


}
