package org.ruoxue.java_147.multithreading;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CountDownLatchClassTest {

	protected class Worker implements Runnable {

		private CountDownLatch latch;
		private int id;

		public Worker(CountDownLatch latch, int id) {
			this.latch = latch;
			this.id = id;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}

	@Test
	public void getCount() {
		try {
			int taskSize = 3;
			CountDownLatch latch = new CountDownLatch(taskSize);
			AtomicInteger ids = new AtomicInteger();

			List<Thread> workers = Stream.generate(() -> new Thread(new Worker(latch, ids.getAndIncrement())))
					.limit(taskSize).collect(Collectors.toList());
			workers.forEach(e -> e.start());
			System.out.println(latch.getCount());
			assertThat(latch.getCount()).isEqualTo(taskSize);
			latch.await();
			System.out.println(latch.getCount());
			assertThat(latch.getCount()).isEqualTo(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class WaitingWorker implements Runnable {

		private CountDownLatch readyLatch;
		private CountDownLatch callingLatch;
		private CountDownLatch finishedLatch;
		private int id;
		private Map<Integer, String> output;

		public WaitingWorker(CountDownLatch readyLatch, CountDownLatch callingLatch, CountDownLatch finishedLatch,
				int id, Map<Integer, String> output) {
			this.readyLatch = readyLatch;
			this.callingLatch = callingLatch;
			this.finishedLatch = finishedLatch;
			this.id = id;
			this.output = output;
		}

		@Override
		public void run() {
			try {
				readyLatch.countDown();
				callingLatch.await();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(3);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");
				output.put(id, "finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				finishedLatch.countDown();
			}
		}
	}

	@Test
	public void waitingWorker() {
		try {
			int taskSize = 3;
			CountDownLatch readyLatch = new CountDownLatch(taskSize);
			CountDownLatch callingLatch = new CountDownLatch(1);
			CountDownLatch finishedLatch = new CountDownLatch(taskSize);
			Map<Integer, String> output = Collections.synchronizedMap(new LinkedHashMap<Integer, String>());
			AtomicInteger ids = new AtomicInteger();

			List<Thread> workers = Stream
					.generate(() -> new Thread(
							new WaitingWorker(readyLatch, callingLatch, finishedLatch, ids.getAndIncrement(), output)))
					.limit(taskSize).collect(Collectors.toList());
			workers.forEach(e -> e.start());
			readyLatch.await();
			callingLatch.countDown();
			finishedLatch.await();

			assertThat(output).hasSize(taskSize).containsOnly(entry(0, "finished"), entry(1, "finished"),
					entry(2, "finished"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
