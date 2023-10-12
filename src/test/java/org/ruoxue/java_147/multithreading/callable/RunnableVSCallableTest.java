package org.ruoxue.java_147.multithreading.callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class RunnableVSCallableTest {

	protected class Runner implements Runnable {

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] runner: ready");
				TimeUnit.SECONDS.sleep(1);
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] runner: finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Test
	public void runner() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runner());
		try {
			executor.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	protected class Caller implements Callable<Object> {

		private Object result;

		@Override
		public Object call() throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] caller: ready");
			TimeUnit.SECONDS.sleep(1);
			System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] caller: finished");

			result = "OK";
			return result;
		}
	}

	@Test
	public void caller() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Object> future = executor.submit(new Caller());
		Object result;
		try {
			result = future.get();
			System.out.println(result);
			assertEquals("OK", ((String) result));
		} catch (InterruptedException | ExecutionException ex) {
			ex.printStackTrace();
		}
	}

	protected class BrokenCaller implements Callable<Object> {

		private Object result;

		@Override
		public Object call() throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] caller: ready");
			boolean flag = true;
			if (flag) {
				throw new IOException();
			}
			TimeUnit.SECONDS.sleep(1);
			System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] caller: finished");

			result = "OK";
			return result;
		}
	}

	@Test
	public void brokenCaller() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Object> future = executor.submit(new BrokenCaller());
		Object result;
		try {
			result = future.get();
			System.out.println(result);
			assertNull(result);
		} catch (InterruptedException | ExecutionException ex) {
			ex.printStackTrace();
		}
	}
}
