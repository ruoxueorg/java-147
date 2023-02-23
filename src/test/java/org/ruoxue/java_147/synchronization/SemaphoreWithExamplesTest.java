package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class SemaphoreWithExamplesTest {

	protected class Worker implements Runnable {

		private Semaphore semaphore = new Semaphore(2);
		private int count;

		public Worker() {
		}

		@Override
		public void run() {
			try {
				System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
				semaphore.acquire();
				try {
					System.out.println(String.format("T[%d] acquire", Thread.currentThread().getId()));
					count++;
					TimeUnit.SECONDS.sleep(1);
					System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} finally {
					semaphore.release();
					System.out.println(String.format("T[%d] release", Thread.currentThread().getId()));
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void acquire() {
		int expected = 3;
		int taskSize = 3;
		Worker worker = new Worker();
		List<Thread> threads = Stream.generate(() -> new Thread(worker)).limit(taskSize).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		threads.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}
}
