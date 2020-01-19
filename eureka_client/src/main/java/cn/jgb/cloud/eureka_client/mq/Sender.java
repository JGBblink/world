package cn.jgb.cloud.eureka_client.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String sendMsg = "hello1 " + new Date();
		System.out.println("Sender1 : " + sendMsg);
		rabbitTemplate.convertAndSend("sender1HelloQueue",sendMsg);
	}

}
