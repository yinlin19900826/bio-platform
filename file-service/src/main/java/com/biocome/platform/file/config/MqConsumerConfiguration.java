package com.biocome.platform.file.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.biocome.platform.file.listener.MqConsumeMsgListenerProcessor;
import com.biocome.platform.rocketmq.constant.RocketMQErrorEnum;
import com.biocome.platform.rocketmq.exception.RocketMQException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @ClassName: MqConsumerConfiguration
 * @Author: shenlele
 * @Date: 2019/7/9 10:36
 * @Description:
 */
@Configuration
public class MqConsumerConfiguration {

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.topics}")
    private String topics;
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;

    private final MqConsumeMsgListenerProcessor mqMessageListenerProcessor;

    @Autowired
    public MqConsumerConfiguration(MqConsumeMsgListenerProcessor mqMessageListenerProcessor) {
        this.mqMessageListenerProcessor = mqMessageListenerProcessor;
    }

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() throws RocketMQException {

        if (StringUtils.isEmpty(groupName)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "组名为空 !!!", false);
        }
        if (StringUtils.isEmpty(namesrvAddr)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "namesrvAddr is null !!!", false);
        }
        if (StringUtils.isEmpty(topics)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "主题为空 !!!", false);
        }

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(mqMessageListenerProcessor);


//         设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
//         如果非第一次启动，那么按照上次消费的位置继续消费

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);


        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);

        try {

            // 设置该消费者订阅的主题和tag，如果是订阅该主题下的所有tag，
            // 则tag使用*；如果需要指定订阅该主题下的某些tag，则使用||分割，例如tag1||tag2||tag3
            consumer.subscribe(topics, "*");

            consumer.start();

        } catch (Exception e) {
            throw new RocketMQException(e);
        }

        return consumer;
    }

}
