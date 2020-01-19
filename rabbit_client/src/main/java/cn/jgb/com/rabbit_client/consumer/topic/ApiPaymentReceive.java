package cn.jgb.com.rabbit_client.consumer.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiPaymentReceive {
	@RabbitHandler
	@RabbitListener(queues = "api.payment")
	public void order(String msg) {
		System.out.println("API payment 交换机分配消息:" + msg);
	}
}
