package cn.jgb.demo.starter.config;

public class UserClient{

	UserProperties userProperties;

	public UserClient(UserProperties userProperties) {
		this.userProperties = userProperties;
	}

	public String getName() {
		return userProperties.getName();
	}

	public String getAuthor() {
		return userProperties.getAuthor();
	}
}
