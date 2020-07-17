package cn.jgb.demo.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jgb
 * @author: JGB
 */
@ConfigurationProperties(prefix = "jgb.starter")
@Data
public class UserProperties {
	private Boolean enable;
	private String name;
	private String author;
}
