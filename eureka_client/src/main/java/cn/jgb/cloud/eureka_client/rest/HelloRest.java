package cn.jgb.cloud.eureka_client.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-hi")
public interface HelloRest {

	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	String sayHello();
}
