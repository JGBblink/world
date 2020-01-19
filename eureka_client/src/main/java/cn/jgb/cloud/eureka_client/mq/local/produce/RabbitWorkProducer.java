package cn.jgb.cloud.eureka_client.mq.local.produce;

import cn.jgb.cloud.eureka_client.mq.local.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RabbitWorkProducer {

	private final static String QUEUE_NAME = "test_queue_work";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
		Channel channel = connection.createChannel();

		// 声明（创建）队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);

		// 消息内容
		for (int i = 0; i < 100; i++) {
			String message = "Hello Worldxxxxxx!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");

			Thread.sleep(i*10);
		}
		//关闭通道和连接
		channel.close();
		connection.close();
	}

}
