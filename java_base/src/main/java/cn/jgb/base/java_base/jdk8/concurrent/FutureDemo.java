package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.Arrays;

/**
 * @Description:future示例类
 * @author: JGB
 * @date: 2019/5/7 10:18
 */
public class FutureDemo {

	public static void main(String[] args) throws Exception{

		Arrays.asList("a","b","c","d","e","f","g").parallelStream().map(e->{
			System.out.println(Thread.currentThread().getName());
			return e.toUpperCase();
		}).forEach(e->{
			System.out.println(Thread.currentThread().getName());
			System.out.println(e);
		});




		/*System.out.println("开始执行");
		Future<String> future = CompletableFuture.supplyAsync(() -> method1());
		Thread.sleep(5000);
		System.out.println("结束执行");

		System.out.println(future.get(2000,TimeUnit.MILLISECONDS));
		System.out.println("over");*/

	}

	public static String method1(){
		System.out.println("method1 bengin");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1 over");

		return "xxxx";
	}
}
