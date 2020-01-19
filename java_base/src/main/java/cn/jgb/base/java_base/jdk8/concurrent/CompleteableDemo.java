package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:compleabble示例
 * @author: JGB
 * @date: 2019/5/8 10:31
 */
public class CompleteableDemo {


	public static void main(String[] args) throws Exception{

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "运行");
			try {
				Thread.sleep(2000);
				int i = 1/0;
			} catch (Exception e) {
				throw new RuntimeException("exception");
			}
			System.out.println(Thread.currentThread().getName() + "结束运行");
			return "hello";
		});
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "运行");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "结束运行");
			return "word";
		});

		//future1.thenCombine(future2, (v1, v2) ->v1 + " " + v2);

		/*future1.runAfterBothAsync(future2,()->{
			System.out.println(Thread.currentThread().getName() + "开始执行");
		},executorService).join();*/

		//future1.applyToEither(future2,(v)->v).whenComplete((v,e)->System.out.println(v)).join();

		future1.exceptionally((e)->{
			System.out.println(e);
			return "xxxx";
		}).join();

		System.out.println(future1.get());

		//Thread.sleep(5000);
	}

	/**
	 * completeableFuture结合流编程
	 *   thenApply:线程结束后转换结果->map
	 *   thenAccept:线程结束后消耗结果
	 *   thenRun:线程结束后执行另外一个线程
	 *   thenCombine:连接两个计算(两个线程异步执行),然后做转换操作
	 *   thenAcceptBoth:连接两个计算,然后做消耗操作
	 *   runAfterBoth:两个计算结束后,再执行另外一个线程
	 * @throws InterruptedException
	 */
	private static void method4() throws InterruptedException {
		// 连续计算
		CompletableFuture.supplyAsync(()->45).thenApply(Integer::intValue).whenComplete((v, e)->System.out.println(v + "xxxx"));

		// 消耗计算
		CompletableFuture.supplyAsync(() -> 45).thenAccept((v) -> System.out.println(v));

		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(2000);
			}catch (Exception e) {

			}
			System.out.println("1 kaishi");
			return 45;
		});

		// 计算结束后开始下一个线程
		completableFuture.thenRun(()->System.out.println("over"));
		// 连接两个计算结果,进行转换操作
		completableFuture.thenCombine(CompletableFuture.supplyAsync(()->{
			System.out.println("开始1");
			return 78;
		}),(v1,v2)->v1*v2).thenAccept((v)->System.out.println(v));
		// 连接两个计算结果,进行消耗操作
		completableFuture.thenAcceptBoth(CompletableFuture.supplyAsync(()->{
			System.out.println("开始2");
			return 78;
		}),(v1,v2)->{
			try {
				Thread.sleep(2000);
				System.out.println(v1-v2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		// 两个连续计算执行完成
		completableFuture.runAfterBoth(CompletableFuture.supplyAsync(()->{
			System.out.println("开始3");
			return 16;
		}),()->System.out.println("over3"));

		Thread.sleep(5000);
	}

	/**
	 * 多个线程间同步到主线程
	 *   allOf:所有线程都完成后再往后执行
	 *   anyOf:其中一个线程结束后就开始往后执行
	 */
	private void method3() {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Integer> taskList = Arrays.asList(1,5,8);

		CompletableFuture<Integer>[] completableFutures = taskList.stream().map(value -> CompletableFuture.supplyAsync(() -> {
			calc(value,System.currentTimeMillis());
			return value;
		},executorService)).toArray(CompletableFuture[]::new);

		//CompletableFuture.anyOf(completableFutures);
		CompletableFuture.allOf(completableFutures);
	}

	private int calc(Integer i,long begin) {
		System.out.println(Thread.currentThread().getName() + "进入");
		try {
			/*switch (i) {
				case 1 : ;
				case 5 : Thread.sleep(5000);
				case 8 : Thread.sleep(8000);
				default:;
			}*/
			if(i == 1) {
				Thread.sleep(2000);
			}

			if(i == 5) {
				Thread.sleep(3000);
			}

			if(i == 8) {
				Thread.sleep(5000);
			}
			System.out.println(Thread.currentThread().getName() + "执行 + " + i + "+完成,耗时" + (System.currentTimeMillis()- begin));
		}catch (Exception e) {

		}finally {
			return i;
		}

	}


	/**
	 * 入门示例
	 * method:
	 * 	 future.complete(param): 完成(结束)异步执行,并返回传入的值
	 * @throws InterruptedException
	 * @throws java.util.concurrent.ExecutionException
	 */
	private static void method1() throws InterruptedException, java.util.concurrent.ExecutionException {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		new Thread(()->{
			try {
				Thread.sleep(2000);
				future.complete(null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		future.get();
	}


	/**
	 * 另一种实现方式:手动设置completeable状态控制线程执行
	 *
	 * @throws Exception
	 */
	private static void method2() throws Exception {
		CompletableFuture<String> future = new CompletableFuture();

		new Thread(()->{

			try {
				Thread.sleep(2000);
				int i = 1/0;
			} catch (InterruptedException e) {
				future.completeExceptionally(e);
			}

			future.complete("xxx");

		}).start();
		System.out.println(future.get(5000,TimeUnit.MILLISECONDS));
	}
}
