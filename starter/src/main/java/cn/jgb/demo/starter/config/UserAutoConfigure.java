package cn.jgb.demo.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {UserProperties.class})
public class UserAutoConfigure {

	@Bean
	@ConditionalOnProperty(name = "jgb.starter.enable",havingValue = "true")
	public UserClient getUserClient(UserProperties userProperties) {
		return new UserClient(userProperties);
	}
}
