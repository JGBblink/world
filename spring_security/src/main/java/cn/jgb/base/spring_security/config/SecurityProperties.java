package cn.jgb.base.spring_security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Security 属性 类
 * Created by Fant.J.
 */
@Component
@ConfigurationProperties(prefix = "fantJ.security")
public class SecurityProperties {
	/**
	 * 浏览器 属性类
	 */
	private BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
}
