package cn.jgb.demo.web_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

/**
 * 线程池扩展,添加线程状态信息
 *
 * @author: JGB
 */
@Slf4j
public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

	/**
	 * 线程调用日志
	 *
	 * @param prefix
	 */
	private void showThreadPoolInfo(String prefix) {
		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
		if (null == threadPoolExecutor) {
			return;
		}

		log.info("{}, {},任务总计 [{}], 已完成 [{}], 活动线程 [{}], 等待队列 [{}]",
				this.getThreadNamePrefix(),
				prefix,
				threadPoolExecutor.getTaskCount(),
				threadPoolExecutor.getCompletedTaskCount(),
				threadPoolExecutor.getActiveCount(),
				threadPoolExecutor.getQueue().size());

	}

	/**
	 * excute表示调用无返回值线程方法
	 *
	 * @param task
	 */
	@Override
	public void execute(Runnable task) {
		showThreadPoolInfo("接收socket请求");
		super.execute(task);
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		showThreadPoolInfo("2execute. do execute");
		super.execute(task, startTimeout);
	}

	/**
	 * submit表示线程调用有返回值方法
	 *
	 * @param task
	 * @return
	 */
	@Override
	public Future<?> submit(Runnable task) {
		showThreadPoolInfo("submit. do submit");
		return super.submit(task);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		showThreadPoolInfo("submit. do submit");
		return super.submit(task);
	}

	/**
	 * ListenableFuture表示线程采用监听线程回调的方式启动
	 *
	 * @param task
	 * @return
	 */
	@Override
	public ListenableFuture<?> submitListenable(Runnable task) {
		showThreadPoolInfo("1. do submitListenable");
		return super.submitListenable(task);
	}

	@Override
	public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
		showThreadPoolInfo("2. do submitListenable");
		return super.submitListenable(task);
	}


	/**
	 * 守护线程定时打印当前线程池信息
	 * ps:由于开启了pmd代码扫描,无法创建守护线程,迫使使用用户线程
	 */
	public void daemonForExecutor() {
		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
		CompletableFuture.runAsync(() -> {
			for (; ; ) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				log.info("{}tomcat线程池状态:任务总计 [{}], 已完成 [{}], 活动线程 [{}], 等待队列 [{}]",
						this.getThreadNamePrefix(),
						threadPoolExecutor.getTaskCount(),
						threadPoolExecutor.getCompletedTaskCount(),
						threadPoolExecutor.getActiveCount(),
						threadPoolExecutor.getQueue().size());


			}
		});
	}

}
