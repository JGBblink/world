package cn.jgb.mq.activemq_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Topic;

@SpringBootApplication
@RestController
public class ActivemqClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqClientApplication.class, args);
	}

	@Autowired
	private JmsMessagingTemplate jms;


	@Autowired
	private Topic topic;


	@RequestMapping("/test")
	public void test() {
		System.out.println("开始activemq测试");

		for(int i = 0 ; i < 10; i++) {
			jms.convertAndSend(topic,"topic:" + i);
		}
	}

	@JmsListener(destination = "publish.topic", containerFactory = "jmsListenerContainerTopic")
	public void consumerMsg(String msg){
		System.out.println(msg);
	}

}
