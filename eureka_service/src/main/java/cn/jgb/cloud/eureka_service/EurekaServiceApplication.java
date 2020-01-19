package cn.jgb.cloud.eureka_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	public String hello() {
		return "端口号:" + port;
	}


	@RequestMapping(value = "/test/httpTest",method = RequestMethod.GET)
	public void test(@RequestParam("name") String name,
					 @RequestHeader(value = "age",defaultValue = "25") Integer age,
					 @RequestAttribute(value = "address") String address,
					 @RequestBody(required = false) String mm) {

		System.out.println(name);
		System.out.println(age);
		System.out.println(address);
		System.out.println(mm);
	}
}
