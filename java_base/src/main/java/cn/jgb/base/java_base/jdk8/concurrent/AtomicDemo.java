package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {


	public static void main(String[] args) throws Exception{
		demo1();
	}

	private static AtomicInteger atomicI = new AtomicInteger(0);
	private static Integer sum = 0;

	public static void demo1() throws Exception{
		for (int i = 0; i < 100 ; i++) {
			new Thread(()->{
				for (int j = 0; j < 100; j++) {
					sum ++;
					//safeAdd();
					atomicI.addAndGet(1);
				}
			}).start();
		}

		Thread.sleep(5000);
		System.out.println(sum);
		System.out.println(atomicI.get());
	}

	public static void safeAdd() {
		while (true) {
			int i = atomicI.get();
			if(atomicI.compareAndSet(i, ++i)) {
				break;
			}
		}
	}
}
