/**
 * 
**/
package test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

/**
 * 线程测试类
 * @author yehl
 * @date 2018年7月11日 上午9:21:22
 */
public class ThreadTest {

	public static void main(String[] args) throws Exception {
		testAwaitTermination();
	}

	/**
	 * CountDownLatch用法
	 * @throws Exception
	 */
	public static void testCountDownLatch() throws Exception {
		// 等待指定数量的线程执行完（等待两个线程调用latch.countDown()方法后继续往下执行）
		CountDownLatch latch = new CountDownLatch(2);
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " --> 执行结束……");
					latch.countDown(); // 执行结束标识减一（放在最后面，线程任务执行完后，再执行此处）
				}
			}.start();
		}
		latch.await(); // 等待指定数量的线程调用latch.countDown()的次数
		System.out.println("主线程………………结束");
	}
	
	public static void testAwaitTermination() throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " --> 执行结束……");
				}
			}.start();
		}
		executorService.shutdown();
		executorService.awaitTermination(30, TimeUnit.MINUTES); // 时间结束前返回true，时间过后线程还未结束，返回false
		System.out.println("主线程………………结束");
	}
	
	/**
	 * 线程全部执行完后处理
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void testInvokeAll() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Callable<Integer>> callables = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			Callable<Integer> callable = new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " --> 执行结束……");
					return index;
				}
			};
			callables.add(callable);
		}
		List<Future<Integer>> result = executorService.invokeAll(callables);
		executorService.shutdown();
		int total = 0;
		for (Future<Integer> future : result) {
			total += future.get().intValue();
		}
		System.out.println("长度：" + result.size());
		System.out.println("结果值：" + total);
		System.out.println("主线程………………结束");
	}
}
