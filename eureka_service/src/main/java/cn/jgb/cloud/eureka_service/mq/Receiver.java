package cn.jgb.cloud.eureka_service.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "sender1HelloQueue")
public class Receiver {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver : " + hello);
	}
}
