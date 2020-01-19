package cn.jgb.cloud.eureka_client.demo.ioc;

import org.springframework.stereotype.Component;

@Component
public class B implements BaseBeanInterface {

	private String name = "B";

	@Override
	public String getName() {
		return name;
	}
}
