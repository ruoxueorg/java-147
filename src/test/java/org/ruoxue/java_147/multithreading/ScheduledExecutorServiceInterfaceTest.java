package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ScheduledExecutorServiceInterfaceTest {

	@Test
	public void taskBlock() {
		int poolSize = 1;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " T[" + Thread.currentThread().getId() + "] init");

		ScheduledFuture<?> futureA = executorService.scheduleAtFixedRate(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + 0
						+ " ready, time: " + Calendar.getInstance().get(Calendar.SECOND));
				for (int i = 0; i < 100000; i++) {
					byte[] bytes = new byte[1024 * 1000];
					if (Thread.interrupted()) {
						throw new InterruptedException();
					}
				}
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + 0
						+ " finished, time: " + Calendar.getInstance().get(Calendar.SECOND));
			} catch (Exception ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		ScheduledFuture<?> futureB = executorService.scheduleAtFixedRate(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + 1
						+ " ready, time: " + Calendar.getInstance().get(Calendar.SECOND));
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + 1
						+ " finished, time: " + Calendar.getInstance().get(Calendar.SECOND));
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			}

		}, 1_000, 1_000, TimeUnit.MILLISECONDS);

		try {
			TimeUnit.SECONDS.sleep(3);
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
