package cn.jgb.demo.web_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置类(属于资源密集型线程池)
 *
 * @author: JGB
 */
@Configuration
@Slf4j
public class TomcatExecutorConfig {

	/**
	 * 当前cpu数
	 */
	private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	/**
	 * 核心线程数量大小
	 */
	private static final int CORE_POOL_SIZE = CPU_COUNT * 2;
	/**
	 * 线程池最大容纳线程数(队列足够大,暂时无用)
	 */
	private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
	/**
	 * 线程空闲后的存活时长
	 */
	private static final int KEEP_ALIVE_TIME = 30;
	/**
	 * 队列最大值
	 */
	private static final int QUEUE_CAPACITY_SIZE = 1000;
	/**
	 * 守护进程执行间隔时间
	 */
	private static final int DAEMON_INTERVAL_TIME = 3000;


	@Bean(name = "myTomcatExecutor")
	public Executor asyncServiceExecutor() {
		log.info("start asyncServiceExecutor");
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		// 配置核心线程数
		executor.setCorePoolSize(100);
		// 配置最大线程数
		executor.setMaxPoolSize(200);
		// 配置队列大小
		executor.setQueueCapacity(5);
		// 配置空闲线程存活时间
		executor.setKeepAliveSeconds(30);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("tomcat-pool");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		//executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		/*executor.setRejectedExecutionHandler((r, executor1) -> {
			//add stat
			throw new RejectedExecutionException("Task " + r.toString() +
					" rejected from " +
					executor1.toString());
		});*/
		//执行初始化
		executor.initialize();
		return executor;
	}

}
