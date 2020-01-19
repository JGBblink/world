package cn.jgb.com.rabbit_client.consumer.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * topic:订阅模型
 * 特点:
 * 	1.模糊匹配
 * 	2.需要定义交换机
 * 		1.routingKey
 * 		2.bindKey
 * 	3.单词匹配:以"."为分割单词
 * 		1."*":单个单词匹配
 * 		2."#":多个单次匹配
 * 		3.例如:routingKey=AA.BB.CC
 * 			1.bindKey=*.BB.*-->匹配
 * 			2.bindKey=AA.#-->匹配
 */
@Configuration
public class TopicConfig {

	// 监听队列
	@Bean
	public Queue coreQueue() {
		return new Queue("api.core");
	}
	@Bean
	public Queue paymentQueue() {
		return new Queue("api.payment");
	}


	// 交换机
	@Bean
	public TopicExchange coreExchange() {
		return new TopicExchange("coreExchange");
	}
	@Bean
	public TopicExchange paymentExchange() {
		return new TopicExchange("paymentExchange");
	}

	// 绑定路由
	@Bean
	public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
		return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
	}
	@Bean
	public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
		return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
	}
}
