package com.biocome.platform.record.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.inter.basemanager.entity.InoutRecord;
import com.biocome.platform.record.biz.InoutRecordBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/9 16:41
 */
@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(MQConsumeMsgListenerProcessor.class);

    @Value("${rocketmq.consumer.topics}")
    private String topics;
    @Value("${rocketmq.consumer.tags}")
    private String tags;
    @Autowired
    private InoutRecordBiz biz;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            logger.info("接收到的消息为空，不做任何处理");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        msgs.stream().forEach(msg -> {
            try{
                String info = new String(msg.getBody());
                logger.info("接收到的消息是：" + info);
                if (msg.getTopic().equals(topics)) {
                    if (msg.getTags().equals(tags)) {
                        int reconsumeTimes = msg.getReconsumeTimes();
                        if (reconsumeTimes == 3) {
                            return;
                        }
                        InoutRecord inoutRecord = JsonUtils.jsonToBean(info, InoutRecord.class);
                        biz.addInoutRecord(inoutRecord);
                    }
                }
            }catch (Exception e){
                logger.info("出入记录保存失败：" + e.getMessage());
            }
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
