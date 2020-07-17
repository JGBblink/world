package cn.jgb.demo.web_demo.rest.enviroment;

import cn.jgb.demo.starter.config.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentRest {

	@Autowired
	Environment environment;

	@Value("${spring.profiles.active}")
	String dev;
	@Value("${xxxxx:test}")
	String test;
	@Autowired
	UserClient userClient;

	@RequestMapping("/environment")
	public String testEnvironment() throws Exception{

		return environment.getProperty("spring.profiles.active");
	}

	@RequestMapping("/value")
	public String testValue() throws Exception{

		return dev + test;
	}

	@RequestMapping("/config")
	public String testConfiguration() throws Exception{

		return userClient.getName() + userClient.getAuthor();
	}

}
