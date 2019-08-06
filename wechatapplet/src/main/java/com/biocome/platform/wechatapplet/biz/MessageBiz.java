package com.biocome.platform.wechatapplet.biz;

import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.wechatapplet.mapper.MessageMapper;
import com.biocome.platform.wechatapplet.vo.message.MessageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: MessageBiz
 * @Author: shenlele
 * @Date: 2019/7/31 14:06
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageBiz {

    private final MessageMapper mapper;

    @Autowired
    public MessageBiz(MessageMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询消息通知列表
     *
     * @return com.biocome.platform.wechatapplet.vo.common.CommonRes
     * @Author shenlele
     * @Date 2019/7/31 14:15
     */
    public TableResultResponse<MessageVo> selectList(int pageSize, int pageNum) throws Exception {
        Page<MessageVo> result = PageHelper.startPage(pageNum, pageSize);
        mapper.selectList();
        return new TableResultResponse<>(result.getTotal(), result.getResult());
    }
}
