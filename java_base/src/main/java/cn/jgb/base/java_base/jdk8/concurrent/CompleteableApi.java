package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteableApi {


	public static void main(String[] args) throws Exception{
		demo6();
	}

	/**
	 * CompletableFuture基础使用
	 * 启动异步线程,并获取线程返回值
	 * @throws Exception
	 */
	public static void demo0() throws Exception {
		CompletableFuture<String> f = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		});

		String o = f.get();
		System.out.println(o);
	}

	/**
	 * 通过CompletableFuture来控制多个异步操作同时执行
	 * 通过设置信号线程返回值,控制子线程
	 */
	public static void demo1() throws Exception{
		CompletableFuture<String> f = new CompletableFuture<>();

		new Thread(() -> {
			try {
				System.out.println("thread1:" + f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				System.out.println("thread2:" + f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}).start();


		Thread.sleep(2000);
		f.complete("hello");
	}

	/**
	 * 连续异步操作
	 *
	 */
	public static void demo3() throws Exception{
		CompletableFuture f = CompletableFuture.supplyAsync(()->{
			return Thread.currentThread().getName() + ":step1";
		}).thenApplyAsync((e)->{
			return e + " => " + Thread.currentThread().getName() + ":step2";
		}).thenApplyAsync((e)->{
			return e + " => " + Thread.currentThread().getName() + ":step2";
		}).thenAcceptAsync(System.out::println);

		System.out.println(f.get());
	}

	/**
	 * 等待异步操作完成
	 */
	public static void demo4() throws Exception{
		CompletableFuture f = CompletableFuture.supplyAsync(()->{
			return "xx";
		}).thenApplyAsync((e)->{
			return "sasda" + e;
		}).whenComplete((result,err)->{
			System.out.println(result);
			System.out.println(err);
		});

		f.get();
	}

	/**
	 * 组合线程
	 */
	public static void demo5() throws Exception{
		CompletableFuture f = CompletableFuture.supplyAsync(()->Thread.currentThread().getId())
				.thenCompose(res->{
					return CompletableFuture.supplyAsync(()->res + ":" + Thread.currentThread().getId());
				})
				.thenCombine(CompletableFuture.supplyAsync(()->"xxxxx"),(a,b)->a + "==" + b)
				.thenAccept(e->{
					System.out.println(e);
				});

		f.get();
	}

	/**
	 * 多个线程,同步返回
	 * CompletableFuture.allOf(CompletableFuture ...)
	 * 等待线程A和线程B执行完毕,再继续执行主线程
	 */
	public static void demo6() throws Exception{
		CompletableFuture future = CompletableFuture.supplyAsync(()->{
			try {
				System.out.println("AAA");
				Thread.sleep(2000);
				System.out.println("AAA结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "aaa";
		});
		CompletableFuture future2 = CompletableFuture.supplyAsync(()->{
			try {
				System.out.println("BBB");
				Thread.sleep(5000);
				System.out.println("BBB结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "bbb";
		});
		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future, future2);
		Thread.sleep(8000);
		voidCompletableFuture.get();
		System.out.println("主线程结束,输出结果:");

		System.out.println(future.get());
		System.out.println(future2.get());


	}


}
