package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CallableInterfaceTest {

	protected class Worker implements Callable<Object> {

		private int id;
		private Object result;

		public Worker(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		@Override
		public Object call() throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
			TimeUnit.SECONDS.sleep(3);
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");

			result = "OK";
			return result;
		}
	}

	@Test
	public void worker() {
		try {
			int taskSize = 3;
			ExecutorService executor = Executors.newFixedThreadPool(taskSize);
			List<Future<Object>> futures = new ArrayList<Future<Object>>();
			for (int i = 0; i < taskSize; i++) {
				Future<Object> future = executor.submit(new Worker(i));
				futures.add(future);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			futures.forEach(e -> {
				try {
					Object result = e.get();
					System.out.println(
							sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + result);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class TimeoutWorker implements Callable<Object> {

		private int id;
		private Object result;

		public TimeoutWorker(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		@Override
		public Object call() throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
			TimeUnit.SECONDS.sleep(3);
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");

			result = "OK";
			return result;
		}
	}

	@Test
	public void timeoutWorker() {
		try {
			int taskSize = 3;
			ExecutorService executor = Executors.newFixedThreadPool(taskSize);
			List<Future<Object>> futures = new ArrayList<Future<Object>>();
			for (int i = 0; i < taskSize; i++) {
				Future<Object> future = executor.submit(new TimeoutWorker(i));
				futures.add(future);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			futures.forEach(e -> {
				try {
					Object result = e.get(2, TimeUnit.SECONDS);
					System.out.println(
							sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + result);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
