package cn.jgb.cloud.eureka_client;

import cn.jgb.cloud.eureka_client.demo.ioc.BaseBeanInterface;
import cn.jgb.cloud.eureka_client.rest.HelloRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
@EnableDiscoveryClient
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

	@Autowired
	HelloRest rest;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/getHello")
	public String getHello() {
		String result = rest.sayHello();
		return result;
	}

	@Autowired
	BaseBeanInterface a;

	@RequestMapping("/test")
	public String test() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("age","60");

		MultiValueMap<String, String> map= new LinkedMultiValueMap(4);
		map.add("name", "phone_number");
		map.add("address", "CD");
		HttpEntity httpEntity = new HttpEntity(httpHeaders);

		restTemplate.exchange("http://localhost:8010/test/httpTest?name={name}",HttpMethod.GET,httpEntity,Void.class,map);
		// 测试get方式
		return a.getName();
	}

	class User {
		String name;
		String address;
		Integer age;

		public User(String name, String address, Integer age) {
			this.name = name;
			this.address = address;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}
}
