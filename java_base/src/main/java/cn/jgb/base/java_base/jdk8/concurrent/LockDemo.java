package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class LockDemo {

	ReentrantLock lock = new ReentrantLock();
	ReentrantReadWriteLock writeLock = new ReentrantReadWriteLock();
	StampedLock stampedLock = new StampedLock();
	List<Integer> queue = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		//methodForReentrantLock();
		//methodForReentrantTry();
		//methodForWriterAndReadLock();

		//methodForDemo();

		ReentrantLock lock2 = new ReentrantLock();

		Condition condition = lock2.newCondition();

		condition.signal();
	}

	private static void methodForDemo() {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		LockDemo demo = new LockDemo();
		Runnable taskWrite = ()->{
			long writeLock = 0L;
			long readLock = 0L;
			try {
				writeLock = demo.stampedLock.writeLock();
				Thread.sleep(100);
				System.out.println("1");
				demo.stampedLock.unlockWrite(writeLock);
				readLock = demo.stampedLock.readLock();
				System.out.println("2");
				demo.stampedLock.unlockRead(readLock);
				Thread.sleep(5000);
				demo.queue.add(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {

				demo.stampedLock.unlockWrite(writeLock);
			}
		};

		Runnable taskRead1 = ()->{
			long readLock = 0L;
			try {
				readLock = demo.stampedLock.readLock();
				Thread.sleep(3000);
				System.out.println(demo.queue);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				demo.stampedLock.unlockRead(readLock);
			}
		};

		Runnable taskRead2 = ()->{
			long readLock = 0L;
			try {
				readLock = demo.stampedLock.readLock();
				System.out.println(demo.queue);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				demo.stampedLock.unlockRead(readLock);
			}
		};
		executorService.submit(taskWrite);
		//executorService.submit(taskRead1);
		//executorService.submit(taskRead2);
		executorService.shutdown();
	}

	/**
	 * 可重入读写锁
	 *  writeLock():写锁,获取写锁后必须要释放锁后才能继续执行
	 *  readLock():读锁,如果未获取到写锁,那么程序不会阻塞,只有写锁未释放,才会阻塞等待写锁释放
	 *  unLock():解锁
	 */
	private static void methodForWriterAndReadLock() {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		LockDemo demo = new LockDemo();
		Runnable taskWrite = ()->{
			try {
				demo.writeLock.writeLock().lock();
				Thread.sleep(5000);
				demo.queue.add(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				demo.writeLock.writeLock().unlock();
			}
		};

		Runnable taskRead1 = ()->{
			try {
				demo.writeLock.readLock().lock();
				System.out.println(demo.queue);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				demo.writeLock.readLock().unlock();
			}
		};

		Runnable taskRead2 = ()->{
			try {
				demo.writeLock.readLock().lock();
				System.out.println(demo.queue);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				demo.writeLock.readLock().unlock();
			}
		};

		executorService.submit(taskWrite);
		executorService.submit(taskRead1);
		executorService.submit(taskRead2);
		executorService.shutdown();
	}

	/**
	 * 可重入锁
	 *  tryLock():尝试获取锁,不阻塞线程
	 *  unLock():解锁
	 */
	private static void methodForReentrantTry() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		LockDemo demo = new LockDemo();
		Runnable task1 = ()->{
			try {
				Thread.sleep(3000);
				demo.lock.lock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				demo.lock.unlock();
			}
		};

		Runnable task2 = ()->{
			try {
				//Thread.sleep(1000);
				while (!demo.lock.tryLock()) {
					System.out.println("----------");
					demo.count();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println(demo.count);
				demo.lock.unlock();
			}

		};

		executorService.submit(task1);
		executorService.submit(task2);

		executorService.shutdown();
	}

	/**
	 * 可重入锁示例
	 * 	lock():加锁
	 * 	unLock():解锁
	 * @throws InterruptedException
	 */
	private static void methodForReentrantLock() throws InterruptedException {
		long begin = System.currentTimeMillis();
		LockDemo demo = new LockDemo();
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		IntStream.range(0,10000).forEach(e->executorService.submit(()->{
			demo.count();
		}));

		executorService.shutdown();
		executorService.awaitTermination(5,TimeUnit.MINUTES);
		System.out.println(demo.count + " : " + (System.currentTimeMillis()-begin));
	}

	int count = 0;
	private void count() {
		boolean b = lock.tryLock();
		count++;
		if(b) {
			lock.unlock();
		}
	}
}

class A {

	public B b;

	public A(B b) {
		this.b = b;
	}

	public synchronized void methodA() {
		try {
			System.out.println("开始获取锁" + Thread.currentThread().getName() + "==>" + b);
			b.methodB();
			Thread.sleep(5000);
			System.out.println("结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class B {
	public synchronized void methodB() {
		try {
			System.out.println(Thread.currentThread().getName() + "===>B执行" + this);
			Thread.sleep(5000);
			System.out.println("B执行结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
