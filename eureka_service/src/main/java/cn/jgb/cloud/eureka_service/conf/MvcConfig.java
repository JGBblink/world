package cn.jgb.cloud.eureka_service.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {


	@Autowired
	Intercepter intercepter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("intercepter");
		registry.addInterceptor(intercepter).addPathPatterns("/test/**");
	}
}
