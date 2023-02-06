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

public class CountDownLatchWithExamplesTest {

	protected class Worker implements Runnable {

		private CountDownLatch latch;
		private int id;
		private Map<Integer, String> output;

		public Worker(CountDownLatch latch, int id, Map<Integer, String> output) {
			this.latch = latch;
			this.id = id;
			this.output = output;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");
				output.put(id, "finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}

	@Test
	public void worker() {
		try {
			int taskSize = 3;
			CountDownLatch latch = new CountDownLatch(taskSize);
			Map<Integer, String> output = Collections.synchronizedMap(new LinkedHashMap<Integer, String>());
			AtomicInteger ids = new AtomicInteger();

			List<Thread> workers = Stream
					.generate(() -> new Thread(new Worker(latch, ids.getAndIncrement(), output))).limit(taskSize)
					.collect(Collectors.toList());
			workers.forEach(e -> e.start());
			latch.await();

			assertThat(output).hasSize(taskSize).containsOnly(entry(0, "finished"), entry(1, "finished"),
					entry(2, "finished"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class BrokenWorker implements Runnable {

		private CountDownLatch latch;
		private int id;
		private Map<Integer, String> output;

		public BrokenWorker(CountDownLatch latch, int id, Map<Integer, String> output) {
			this.latch = latch;
			this.id = id;
			this.output = output;
		}

		@Override
		public void run() {
			try {
				if (id == 1) {
					throw new RuntimeException("BrokenWorker " + id + " throw exception");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");
				output.put(id, "finished");
				latch.countDown();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void brokenWorker() {
		try {
			int taskSize = 3;
			CountDownLatch latch = new CountDownLatch(taskSize);
			Map<Integer, String> output = Collections.synchronizedMap(new LinkedHashMap<Integer, String>());
			AtomicInteger ids = new AtomicInteger();

			List<Thread> workers = Stream
					.generate(() -> new Thread(new BrokenWorker(latch, ids.getAndIncrement(), output)))
					.limit(taskSize).collect(Collectors.toList());
			workers.forEach(e -> e.start());
			boolean completed = latch.await(5, TimeUnit.SECONDS);

			assertThat(completed).isFalse();
			assertThat(output).hasSize(2).containsOnly(entry(0, "finished"), entry(2, "finished"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
