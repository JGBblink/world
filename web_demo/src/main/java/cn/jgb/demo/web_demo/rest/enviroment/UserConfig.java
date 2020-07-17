package cn.jgb.demo.web_demo.rest.enviroment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jgb")
public class UserConfig {

	private String name;
}
