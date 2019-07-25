package com.biocome.platform.file.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.file.constant.CommonConstant;
import com.biocome.platform.file.entity.OpenDoorImages;
import com.biocome.platform.file.entity.UserImages;
import com.biocome.platform.file.mapper.OpenDoorImagesMapper;
import com.biocome.platform.file.mapper.UserImagesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName: MqConsumeMsgListenerProcessor
 * @Author: shenlele
 * @Date: 2019/7/9 10:36
 * @Description:
 */
@Slf4j
@Component
public class MqConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    @Value("${rocketmq.consumer.topics}")
    private String topics;
    @Value("${rocketmq.producer.userTags}")
    private String userTags;
    @Value("${rocketmq.producer.openDoorTags}")
    private String openDoorTags;

    private final UserImagesMapper userImagesMapper;
    private final OpenDoorImagesMapper openDoorImagesMapper;

    @Autowired
    public MqConsumeMsgListenerProcessor(UserImagesMapper userImagesMapper, OpenDoorImagesMapper openDoorImagesMapper) {
        this.userImagesMapper = userImagesMapper;
        this.openDoorImagesMapper = openDoorImagesMapper;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            log.info("接收到的消息为空，不做任何处理");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        msgs.forEach(msg -> {
            try {
                String info = new String(msg.getBody());
                log.info("接收到的消息是：" + info);
                if (msg.getTopic().equals(topics)) {
                    if (msg.getTags().equals(userTags)) {
                        int reconsumeTimes = msg.getReconsumeTimes();
                        if (reconsumeTimes == CommonConstant.DEFAULT_INT_THREE) {
                            return;
                        }
                        UserImages model = JsonUtils.jsonToBean(info, UserImages.class);
                        userImagesMapper.insertSelective(model);
                    } else if (msg.getTags().equals(openDoorTags)) {
                        int reconsumeTimes = msg.getReconsumeTimes();
                        if (reconsumeTimes == CommonConstant.DEFAULT_INT_THREE) {
                            return;
                        }
                        OpenDoorImages model = JsonUtils.jsonToBean(info, OpenDoorImages.class);
                        openDoorImagesMapper.insertSelective(model);
                    }
                }
            } catch (Exception e) {
                log.info("人员图片或开门图片保存失败，错误信息为：" + e.getMessage());
            }
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
