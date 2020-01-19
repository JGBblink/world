package cn.jgb.cloud.eureka_service.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ReceiverConf {

	@Bean(name="sender1HelloQueue")
	public Queue queue() {
		return new Queue("sender1HelloQueue");
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	@Bean
	Binding bindingExchangeMessage(@Qualifier("sender1HelloQueue") Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("sender1HelloQueue");
	}
}
