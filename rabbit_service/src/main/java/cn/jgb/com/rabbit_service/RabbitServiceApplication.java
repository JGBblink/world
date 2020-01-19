package cn.jgb.com.rabbit_service;

import cn.jgb.com.rabbit_service.product.direct.PaymentNotifySender;
import cn.jgb.com.rabbit_service.product.topic.ApiCoreSender;
import cn.jgb.com.rabbit_service.product.topic.ApiPaymentSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RabbitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitServiceApplication.class, args);
	}


	@Autowired
	PaymentNotifySender sender;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForMobile(@RequestParam(name = "message") String message) {
		sender.sender(message);

		return "success";
	}

	@Autowired
	ApiCoreSender topicCoreSender;

	@RequestMapping(value = "/topic/core", method = RequestMethod.GET)
	public String topicTestMethodCore(@RequestParam(name = "message") String message) {
		topicCoreSender.user(message);
		topicCoreSender.userQuery(message);

		return "success";
	}

	@Autowired
	ApiPaymentSender topicPaymentSender;

	@RequestMapping(value = "/topic/payment", method = RequestMethod.GET)
	public String topicTestMethodPayment(@RequestParam(name = "message") String message) {
		topicPaymentSender.order(message);
		topicPaymentSender.orderDetailQuery(message);
		topicPaymentSender.orderQuery(message);

		return "success";
	}


}
