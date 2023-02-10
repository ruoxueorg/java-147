package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class RunnableWithExamplesTest {

	protected class Task extends Thread {

		private int id;

		public Task(int id) {
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
			}
		}
	}

	@Test
	public void task() {
		try {
			int taskSize = 3;
			for (int i = 0; i < taskSize; i++) {
				Thread thread = new Thread(new Task(i));
				thread.start();
			}

			TimeUnit.SECONDS.sleep(3);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class Worker implements Runnable {

		private int id;

		public Worker(int id) {
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
			}
		}
	}

	@Test
	public void worker() {
		try {
			int taskSize = 3;
			List<Thread> threads = new ArrayList<Thread>();
			IntStream.range(0, taskSize).forEach(e -> {
				Thread thread = new Thread(new Worker(e));
				thread.start();
				threads.add(thread);
			});

			threads.forEach(e -> {
				try {
					e.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class BrokenWorker implements Runnable {

		private int id;

		public BrokenWorker(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				if (id == 1) {
					throw new RuntimeException("BrokenWorker " + id + " throw exception");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void brokenWorker() {
		try {
			int taskSize = 3;
			AtomicInteger ids = new AtomicInteger();
			List<Thread> threads = Stream.generate(() -> new Thread(new BrokenWorker(ids.getAndIncrement())))
					.limit(taskSize).collect(Collectors.toList());
			threads.forEach(e -> e.start());

			threads.forEach(e -> {
				try {
					e.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
