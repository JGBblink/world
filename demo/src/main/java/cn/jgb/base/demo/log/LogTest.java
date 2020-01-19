package cn.jgb.base.demo.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogTest {


	@RequestMapping("/test/demo1")
	public void demo1() {
		log.info(this.toString() + " : demo1");
		log.warn("warn:-->" + Math.random());
		log.error("error:-->");
		log.debug("debug:-->");
		System.out.println(log.isInfoEnabled());
	}
}
