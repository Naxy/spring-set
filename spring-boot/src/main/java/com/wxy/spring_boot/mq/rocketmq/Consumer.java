package com.wxy.spring_boot.mq.rocketmq;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.MixAll;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

public class Consumer {
	public static final String ADDR = "localhost:9876";
	static {
		String nameAddr = System.getenv(MixAll.NAMESRV_ADDR_ENV);
		System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, nameAddr == null || nameAddr.length() == 0 ? ADDR : nameAddr);
	}
	public static void main(String[] args) throws MQClientException {
		simple();
	}
	public static void simple() throws MQClientException{
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("simple");
		consumer.setNamesrvAddr(ADDR);
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("TopicTest","*");
		
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();

        System.out.println("Consumer Started.");
	}
	public static void order() throws MQClientException{
		 DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");

	        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

	        consumer.subscribe("TopicTest", "TagA || TagC || TagD");

	        consumer.registerMessageListener(new MessageListenerOrderly() {

	            AtomicLong consumeTimes = new AtomicLong(0);
	            @Override
	            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
	                                                       ConsumeOrderlyContext context) {
	                context.setAutoCommit(false);
	                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
	                this.consumeTimes.incrementAndGet();
	                if ((this.consumeTimes.get() % 2) == 0) {
	                    return ConsumeOrderlyStatus.SUCCESS;
	                } else if ((this.consumeTimes.get() % 3) == 0) {
	                    return ConsumeOrderlyStatus.ROLLBACK;
	                } else if ((this.consumeTimes.get() % 4) == 0) {
	                    return ConsumeOrderlyStatus.COMMIT;
	                } else if ((this.consumeTimes.get() % 5) == 0) {
	                    context.setSuspendCurrentQueueTimeMillis(3000);
	                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
	                }
	                return ConsumeOrderlyStatus.SUCCESS;

	            }
	        });

	        consumer.start();

	        System.out.printf("Consumer Started.%n");
	}
}
