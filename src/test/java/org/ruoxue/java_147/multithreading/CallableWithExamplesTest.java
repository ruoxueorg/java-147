package org.ruoxue.java_147.multithreading;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CallableWithExamplesTest {

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
			List<FutureTask<Object>> futureTasks = new ArrayList<FutureTask<Object>>();
			for (int i = 0; i < taskSize; i++) {
				FutureTask<Object> futureTask = new FutureTask<Object>(new Worker(i));
				futureTasks.add(futureTask);
				Thread thread = new Thread(futureTask);
				thread.start();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			futureTasks.forEach(e -> {
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

	protected class BrokenWorker implements Callable<Object> {

		private int id;
		private Object result;

		public BrokenWorker(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		@Override
		public Object call() throws Exception {
			if (id == 1) {
				throw new RuntimeException("BrokenWorker " + id + " throw exception");
			}
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
	public void brokenWorker() {
		try {
			int taskSize = 3;
			List<FutureTask<Object>> futureTasks = new ArrayList<FutureTask<Object>>();
			for (int i = 0; i < taskSize; i++) {
				FutureTask<Object> futureTask = new FutureTask<Object>(new BrokenWorker(i));
				futureTasks.add(futureTask);
				Thread thread = new Thread(futureTask);
				thread.start();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			futureTasks.forEach(e -> {
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

}
