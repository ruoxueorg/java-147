package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ScheduledExecutorServiceInterfaceTest {

	@Test
	public void cancelFalse() {
		int poolSize = 1;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		ScheduledFuture<?> future = executorService.scheduleAtFixedRate(() -> {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(String.format("%s T[%d] %d task ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				for (int i = 0; i < 100000; i++) {
					byte[] bytes = new byte[1024 * 1000];
				}
				System.out.println(String.format("%s T[%d] %d task finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		if (false == future.isDone()) {
			boolean cancel = future.cancel(false);
			System.out.println(String.format("%s T[%d] %d task cancel: %b, done: %b", df.format(new Date()),
					Thread.currentThread().getId(), System.currentTimeMillis() - start, cancel, future.isDone()));
		}

		try {
			executorService.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void cancelTrue() {
		int poolSize = 1;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		ScheduledFuture<?> future = executorService.scheduleAtFixedRate(() -> {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(String.format("%s T[%d] %d task ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				for (int i = 0; i < 100000; i++) {
					byte[] bytes = new byte[1024 * 1000];
					if (Thread.interrupted()) {
						throw new InterruptedException();
					}
				}
				System.out.println(String.format("%s T[%d] %d task finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		if (false == future.isDone()) {
			boolean cancel = future.cancel(true);
			System.out.println(String.format("%s T[%d] %d task cancel: %b", df.format(new Date()),
					Thread.currentThread().getId(), System.currentTimeMillis() - start, cancel));
		}

		try {
			executorService.awaitTermination(20, TimeUnit.SECONDS);
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
		ScheduledFuture<?> futureA = executorService.scheduleWithFixedDelay(() -> {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(String.format("%s T[%d] %d task: A ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				TimeUnit.SECONDS.sleep(3);
				System.out.println(String.format("%s T[%d] %d task: A finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		ScheduledFuture<?> futureB = executorService.scheduleWithFixedDelay(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				System.out.println(String.format("%s T[%d] %d task: B ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				System.out.println(String.format("%s T[%d] %d task: B finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		if (false == futureA.isDone()) {
			futureA.cancel(true);
		}

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
		ScheduledFuture<?> futureA = executorService.scheduleAtFixedRate(() -> {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(String.format("%s T[%d] %d task: A ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				TimeUnit.SECONDS.sleep(3);
				System.out.println(String.format("%s T[%d] %d task: A finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		ScheduledFuture<?> futureB = executorService.scheduleAtFixedRate(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				System.out.println(String.format("%s T[%d] %d task: B ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
				System.out.println(String.format("%s T[%d] %d task: B finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		if (false == futureA.isDone()) {
			futureA.cancel(true);
		}

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}