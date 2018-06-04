package com.wxy.spring_boot.mq.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.MixAll;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class Producer {
	public static final String ADDR = "localhost:9876";

	static {
		String nameAddr = System.getenv(MixAll.NAMESRV_ADDR_ENV);
		System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, nameAddr == null || nameAddr.length() == 0 ? ADDR : nameAddr);
	}

	public static void main(String[] args) throws UnsupportedEncodingException, MQClientException, RemotingException,
			MQBrokerException, InterruptedException {
		// simpleSync();
		// simpleAsync();
		//simpleOneWay();
		order();
	}

	public static void simpleSync() throws MQClientException, UnsupportedEncodingException, RemotingException,
			MQBrokerException, InterruptedException {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
		// Launch the instance.
		producer.start();
		for (int i = 0; i < 100; i++) {
			// Create a message instance, specifying topic, tag and message
			// body.
			Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */, ("Hello RocketMQ " + i)
					.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
			);
			// Call send message to deliver message to one of brokers.
			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}

	public static void simpleAsync()
			throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
		// Launch the instance.
		producer.start();
		producer.setRetryTimesWhenSendAsyncFailed(0);
		for (int i = 0; i < 100; i++) {
			final int index = i;
			// Create a message instance, specifying topic, tag and message
			// body.
			Message msg = new Message("TopicTest", "TagA", "OrderID188",
					"Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
			producer.send(msg, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
				}

				@Override
				public void onException(Throwable e) {
					System.out.printf("%-10d Exception %s %n", index, e);
					e.printStackTrace();
				}
			});
		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}

	public static void simpleOneWay()
			throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
		// Launch the instance.
		producer.start();
		for (int i = 0; i < 100; i++) {
			// Create a message instance, specifying topic, tag and message
			// body.
			Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */, ("Hello RocketMQ " + i)
					.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
			);
			// Call send message to deliver message to one of brokers.
			producer.sendOneway(msg);

		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}

	public static void order() throws MQClientException, UnsupportedEncodingException, RemotingException,
			MQBrokerException, InterruptedException {
		// Instantiate with a producer group name.
		MQProducer producer = new DefaultMQProducer("example_group_name");

		// Launch the instance.
		producer.start();
		String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };
		for (int i = 0; i < 100; i++) {
			int orderId = i % 10;
			// Create a message instance, specifying topic, tag and message
			// body.
			Message msg = new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i,
					("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
				@Override
				public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
					Integer id = (Integer) arg;
					int index = id % mqs.size();
					return mqs.get(index);
				}
			}, orderId);

			System.out.printf("%s%n", sendResult);
		}
		// server shutdown
		producer.shutdown();
	}
}
