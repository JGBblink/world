package cn.jgb.com.rabbit_service.product.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 模式(默认):direct:全匹配
 * 特点:
 * 	1.rabbit默认模式
 * 	2.routingKey和bindKey必须全匹配
 * 	3.可以不用实现交换机,采用默认交换机
 */
@Component
public class PaymentNotifySender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void sender(String msg){
		System.out.println("发送消息:" + msg);
		rabbitTemplate.convertAndSend("notify.payment", msg);
	}
}
