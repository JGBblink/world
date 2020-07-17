package cn.jgb.com.rabbit_service.product.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCoreSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void user(String msg){
		rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
	}

	public void userQuery(String msg){
		//rabbitTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
		rabbitTemplate.convertAndSend("rplus.service.logger:event:exchange", "rplus.service.logger:event:record.world", msg.getBytes());
	}
}
