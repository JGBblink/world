package cn.jgb.demo.web_demo.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Configuration
public class TomcatConfig {

	@Resource(name = "myTomcatExecutor")
	Executor myTomcatExecutor;

	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
		tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer(myTomcatExecutor));
		// 端口号
		tomcatFactory.setPort(8110);
		// 访问前缀
		tomcatFactory.setContextPath("/web-demo");
		return tomcatFactory;
	}
}
