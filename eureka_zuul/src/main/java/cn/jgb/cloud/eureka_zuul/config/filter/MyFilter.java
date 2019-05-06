package cn.jgb.cloud.eureka_zuul.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter {

	@Override
	public String filterType() {
		/**
		 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
		 * pre：路由之前
		 * routing：路由之时
		 * post： 路由之后
		 * error：发送错误调用
		 */
		return "pre";
	}

	@Override
	public int filterOrder() {
		/**
		 * 路由顺序,如果有多个,则不同过滤器根据不同优先级执行
		 */
		return 0;
	}

	/**
	 * 是否需要拦截,如果返回false后续拦截器不再被拦截
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.sendZuulResponse();
	}

	/**
	 * 过滤器执行具体逻辑,可以通过修改sendZuulResponse状态来是否继续执行后续拦截器
	 * @return
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().getWriter().write("token is empty");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
