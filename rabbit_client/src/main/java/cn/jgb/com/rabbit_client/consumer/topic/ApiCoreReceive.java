package cn.jgb.com.rabbit_client.consumer.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiCoreReceive {

	@RabbitHandler
	@RabbitListener(queues = "api.core")
	public void user(String msg) {
		System.out.println("API core 交换机分配消息:" + msg);
	}
}