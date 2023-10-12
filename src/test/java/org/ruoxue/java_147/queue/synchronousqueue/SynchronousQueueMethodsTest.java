package org.ruoxue.java_147.queue.synchronousqueue;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class SynchronousQueueMethodsTest {

	@Test(expected = IllegalStateException.class)
	public void add() {
		int expectedSize = 0;
		BlockingQueue<String> queue = new SynchronousQueue<String>();
		queue.add("Papaya");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void remove() {
		int expectedSize = 0;
		BlockingQueue<String> queue = new SynchronousQueue<String>();
		queue.remove("Papaya");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void put() {
		try {
			int expectedSize = 0;
			BlockingQueue<String> queue = new SynchronousQueue<String>();

			Thread thread = new Thread(() -> {
				try {
					String value = queue.take();
					System.out.println("T[" + Thread.currentThread().getId() + "] take: " + value);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
			thread.start();

			queue.put("Papaya");
			queue.put("Strawberry");
			System.out.println("T[" + Thread.currentThread().getId() + "] put: " + queue);
			assertEquals(expectedSize, queue.size());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void take() {
		try {
			int expectedSize = 0;
			BlockingQueue<String> queue = new SynchronousQueue<String>();

			Thread thread = new Thread(() -> {
				try {
					queue.put("Papaya");
					System.out.println("T[" + Thread.currentThread().getId() + "] put: " + queue);
					queue.put("Strawberry");
					System.out.println("T[" + Thread.currentThread().getId() + "] put: " + queue);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
			thread.start();

			String value = queue.take();
			System.out.println("T[" + Thread.currentThread().getId() + "] take: " + value);
			assertEquals(expectedSize, queue.size());
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void produceAndConsume() {
		int expectedSize = 0;
		BlockingQueue<Integer> queue = new SynchronousQueue<Integer>();
		AtomicInteger ids = new AtomicInteger();
		List<Thread> putThreads = Stream.generate(() -> new Thread(() -> {
			try {
				int value = ids.getAndIncrement();
				queue.put(value);
				System.out.println("T[" + Thread.currentThread().getId() + "] put: " + value);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(2).collect(Collectors.toList());
		putThreads.forEach(e -> e.start());

		List<Thread> takeThreads = Stream.generate(() -> new Thread(() -> {
			try {
				Integer value = queue.take();
				System.out.println("T[" + Thread.currentThread().getId() + "] take: " + value);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(2).collect(Collectors.toList());
		takeThreads.forEach(e -> e.start());

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("T[" + Thread.currentThread().getId() + "] " + queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void newCachedThreadPool() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		int taskSize = 3;
		CountDownLatch latch = new CountDownLatch(taskSize);
		try {
			IntStream.range(0, taskSize).forEach(e -> {
				executorService.execute(() -> {
					try {
						System.out.println("T[" + Thread.currentThread().getId() + "] task: " + e + " ready");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("T[" + Thread.currentThread().getId() + "] task: " + e + " finished");
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} finally {
						latch.countDown();
					}
				});
			});
			latch.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
