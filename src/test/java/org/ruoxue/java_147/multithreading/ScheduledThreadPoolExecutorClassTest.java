package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ScheduledThreadPoolExecutorClassTest {

	protected class FalseWorker implements Runnable {

		private AtomicInteger counter;
		private long start;

		public FalseWorker(AtomicInteger counter, long start) {
			this.counter = counter;
			this.start = start;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(String.format("%s T[%d] %d worker ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				counter.getAndIncrement();
				System.out.println(String.format("%s T[%d] %d worker finished, counter: %d", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start, counter.get()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void cancelFalse() {
		int poolSize = 1;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		AtomicInteger counter = new AtomicInteger();
		ScheduledFuture<?> future = executorService.scheduleAtFixedRate(new FalseWorker(counter, start), 1_000, 1_000,
				TimeUnit.MILLISECONDS);

		while (true) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			if (counter.get() == 3) {
				if (false == future.isDone()) {
					boolean cancel = future.cancel(false);
					System.out.println(String.format("%s T[%d] %d worker cancel: %b, done: %b", df.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, cancel,
							future.isDone()));
				}
				break;
			}
		}

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	protected class TrueWorker implements Runnable {

		private AtomicInteger counter;
		private long start;

		public TrueWorker(AtomicInteger counter, long start) {
			this.counter = counter;
			this.start = start;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(String.format("%s T[%d] %d worker ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				counter.getAndIncrement();
				TimeUnit.SECONDS.sleep(1);
				System.out.println(String.format("%s T[%d] %d worker finished, counter: %d", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start, counter.get()));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

	@Test
	public void cancelTrue() {
		int poolSize = 1;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		AtomicInteger counter = new AtomicInteger();
		ScheduledFuture<?> future = executorService.scheduleAtFixedRate(new TrueWorker(counter, start), 1_000, 1_000,
				TimeUnit.MILLISECONDS);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		if (false == future.isDone()) {
			boolean cancel = future.cancel(true);
			System.out.println(String.format("%s T[%d] %d worker cancel: %b, done: %b", df.format(new Date()),
					Thread.currentThread().getId(), System.currentTimeMillis() - start, cancel, future.isDone()));
		}

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void noCatchUp() {
		int poolSize = 1;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		AtomicInteger counter = new AtomicInteger();
		ScheduledFuture<?> future = executorService.scheduleWithFixedDelay(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				System.out.println(String.format("%s T[%d] %d task: A ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				if (counter.get() == 0) {
					TimeUnit.SECONDS.sleep(3);
				}
				counter.getAndIncrement();
				System.out.println(String.format("%s T[%d] %d task: A finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void catchUp() {
		int poolSize = 1;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		AtomicInteger counter = new AtomicInteger();
		ScheduledFuture<?> future = executorService.scheduleAtFixedRate(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				System.out.println(String.format("%s T[%d] %d task: A ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				if (counter.get() == 0) {
					TimeUnit.SECONDS.sleep(3);
				}
				counter.getAndIncrement();
				System.out.println(String.format("%s T[%d] %d task: A finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
