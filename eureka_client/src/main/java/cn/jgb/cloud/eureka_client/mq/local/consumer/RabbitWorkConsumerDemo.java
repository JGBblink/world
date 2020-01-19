package cn.jgb.cloud.eureka_client.mq.local.consumer;

import cn.jgb.cloud.eureka_client.mq.local.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitWorkConsumerDemo {
	private final static String QUEUE_NAME = "test_queue_work";

	public static void main(String[] argv) throws Exception {

		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
		Channel channel = connection.createChannel();
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 同一时刻服务器只能发送一条消息给消费者
		channel.basicQos(1);


		// 定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);

		// 监听队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
		// 获取消息
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [y] Received '" + message + "'");
			//休眠
			Thread.sleep(10);
			// 返回确认状态，注释掉表示使用自动确认模式
			//channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}
