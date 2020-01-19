package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicDemo {


	public static void main(String[] args) throws Exception{
		AtomicDemo demo = new AtomicDemo();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		IntStream.range(0,1000).forEach(i->executorService.submit(atomicInteger::incrementAndGet));

		executorService.shutdown();
		executorService.awaitTermination(5,TimeUnit.MINUTES);
		System.out.println(atomicInteger.get());
	}
}
