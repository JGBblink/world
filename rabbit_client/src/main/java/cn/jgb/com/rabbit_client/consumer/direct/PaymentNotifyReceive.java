package cn.jgb.com.rabbit_client.consumer.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 定义监听队列
 */
@Component
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceive {
	@RabbitHandler
	public void receive(String msg) {
		System.out.println("收到消息" + msg);
	}
}
