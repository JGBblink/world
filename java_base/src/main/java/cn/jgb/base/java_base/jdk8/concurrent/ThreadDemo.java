package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		methodForRunable(executorService);

		/*Callable<String> task = ()->{
			System.out.println(Thread.currentThread().getName());
			return "sss";
		};

		Future<String> future = executorService.submit(task);
		System.out.println(future.get());

		ExecutorService executorService1 = Executors.newFixedThreadPool(2);
		List<Callable<String>> tasks = Arrays.asList(() -> "A", () -> "B", () -> "C");

		List<Future<String>> futures = executorService1.invokeAll(tasks);

		futures.stream().forEach(e->{
			try {
				System.out.println(e.get());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			}
		});

		ScheduledExecutorService executorService2 = Executors.newScheduledThreadPool(1);
		executorService2.schedule(()->{
			System.out.println(Thread.currentThread().getName());
		},2000,TimeUnit.MILLISECONDS);


		executorService.shutdown();*/
	}

	private static void methodForRunable(ExecutorService executorService) throws InterruptedException {
		List<Runnable> tasks = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			tasks.add(()->{
				try {
					Thread.sleep(3000);
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		tasks.stream().forEach(task->executorService.submit(task));

		System.out.println("begin");
		executorService.awaitTermination(3000,TimeUnit.MILLISECONDS);
		System.out.println("over");
		executorService.shutdown();
	}
}
