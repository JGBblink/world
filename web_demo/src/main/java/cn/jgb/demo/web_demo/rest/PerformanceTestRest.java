package cn.jgb.demo.web_demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformanceTestRest {

	@RequestMapping("/testMethod1")
	public void testMethod1() throws Exception{

		Thread.sleep(5000);
		System.out.println("testMethod1");
	}

}
