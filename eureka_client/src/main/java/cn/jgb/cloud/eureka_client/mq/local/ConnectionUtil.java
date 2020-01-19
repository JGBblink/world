package cn.jgb.cloud.eureka_client.mq.local;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

	public static Connection getConnection() throws Exception {
		//定义连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置服务地址
		factory.setHost("dev.shukangyun.com");
		//端口
		factory.setPort(53313);
		//设置账号信息，用户名、密码、vhost
		//factory.setVirtualHost("testhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		// 通过工程获取连接
		Connection connection = factory.newConnection();
		return connection;
	}
}
