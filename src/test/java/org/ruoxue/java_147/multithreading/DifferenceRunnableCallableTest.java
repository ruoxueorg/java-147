package org.ruoxue.java_147.multithreading;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DifferenceRunnableCallableTest {

	@Test
	public void runner() {
		Thread thread = new Thread(() -> {
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] runner: ready");
				TimeUnit.SECONDS.sleep(3);
				System.out.println("T[" + Thread.currentThread().getId() + "] runner: finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	protected class Caller implements Callable<String> {

		private String result;

		@Override
		public String call() throws Exception {
			System.out.println("T[" + Thread.currentThread().getId() + "] caller: ready");
			TimeUnit.SECONDS.sleep(3);
			System.out.println("T[" + Thread.currentThread().getId() + "] caller: finished");
			result = "OK";
			return result;
		}
	}

	@Test
	public void caller() {
		List<FutureTask<String>> futureTasks = new ArrayList<FutureTask<String>>();
		FutureTask<String> futureTask = new FutureTask<String>(new Caller());
		futureTasks.add(futureTask);
		Thread thread = new Thread(futureTask);
		thread.start();

		futureTasks.forEach(e -> {
			try {
				String result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "] caller: " + result);
				assertNotNull(result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	protected class BrokenCaller implements Callable<String> {

		private String result;

		@Override
		public String call() throws Exception {
			System.out.println("T[" + Thread.currentThread().getId() + "] caller: ready");
			boolean flag = true;
			if (flag) {
				throw new IOException();
			}
			TimeUnit.SECONDS.sleep(3);
			System.out.println("T[" + Thread.currentThread().getId() + "] caller: finished");
			result = "OK";
			return result;
		}
	}

	@Test
	public void brokenCaller() {
		List<FutureTask<String>> futureTasks = new ArrayList<FutureTask<String>>();
		FutureTask<String> futureTask = new FutureTask<String>(new BrokenCaller());
		futureTasks.add(futureTask);
		Thread thread = new Thread(futureTask);
		thread.start();

		futureTasks.forEach(e -> {
			try {
				String result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "] caller: " + result);
				assertNotNull(result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}
}
