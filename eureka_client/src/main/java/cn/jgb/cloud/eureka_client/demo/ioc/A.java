package cn.jgb.cloud.eureka_client.demo.ioc;

import org.springframework.stereotype.Component;

@Component
public class A implements BaseBeanInterface{

	private String name = "A";

	@Override
	public String getName() {
		return name;
	}
}
