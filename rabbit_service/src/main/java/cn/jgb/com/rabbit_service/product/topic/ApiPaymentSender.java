package cn.jgb.com.rabbit_service.product.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiPaymentSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void order(String msg){
		rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
	}

	public void orderQuery(String msg){
		rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
	}

	public void orderDetailQuery(String msg){
		rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
	}
}
