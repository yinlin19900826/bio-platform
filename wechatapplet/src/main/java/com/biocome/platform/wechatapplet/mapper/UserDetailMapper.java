package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.wechatapplet.vo.userdetail.CompleteVo;
import org.apache.ibatis.annotations.Param;


/**
 * @ClassName: UserDetailMapper
 * @Author: shenlele
 * @Date: 2019/8/2 10:24
 * @Description:
 */
public interface UserDetailMapper {

    /**
     * 根据用户编码更新数据，不为null则更新
     *
     * @param vo
     * @return void
     * @Author shenlele
     * @Date 2019/8/2 10:28
     */
    void updateSelectiveById(@Param("vo") CompleteVo vo);

}
