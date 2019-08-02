package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.wechatapplet.vo.message.MessageVo;

import java.util.List;

/**
 * @ClassName: MessageMapper
 * @Author: shenlele
 * @Date: 2019/7/31 09:22
 * @Description:
 */
public interface MessageMapper {

    /**
     * 查询消息列表
     *
     * @return java.util.List<com.biocome.platform.wechatapplet.vo.message.MessageVo>
     * @Author shenlele
     * @Date 2019/7/31 13:54
     */
    List<MessageVo> selectList();

}
