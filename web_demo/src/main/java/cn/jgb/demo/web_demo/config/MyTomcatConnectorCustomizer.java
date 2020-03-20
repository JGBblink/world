package cn.jgb.demo.web_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;

import java.util.concurrent.Executor;

/**
 * 
 * @author: JGB
 */
@Slf4j
public class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {

	Executor tomcatExecutor;

	public MyTomcatConnectorCustomizer(Executor executor) {
		this.tomcatExecutor = executor;
	}

	@Override
	public void customize(Connector connector) {
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		//设置最大连接数
		protocol.setMaxConnections(1);
		//设置最大线程数
		protocol.setMaxThreads(1);
		protocol.setConnectionTimeout(100);
		protocol.setAcceptCount(5);
		protocol.setExecutor(tomcatExecutor);
		protocol.setMinSpareThreads(100);

	}

}
