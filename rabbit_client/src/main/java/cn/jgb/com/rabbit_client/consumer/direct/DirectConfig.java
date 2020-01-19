package cn.jgb.com.rabbit_client.consumer.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 模式(默认):direct:全匹配
 * 特点:
 * 	1.rabbit默认模式
 * 	2.routingKey和bindKey必须全匹配
 * 	3.可以不用实现交换机,采用默认交换机
 */
@Configuration
public class DirectConfig {
	@Bean
	public Queue paymentNotifyQueue() {
		return new Queue("notify.payment");
	}
}
