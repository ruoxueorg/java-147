package org.ruoxue.java_147.multithreading;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class RunnableTest {

	protected class Worker implements Runnable {

		private int id;

		public Worker(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				System.out.println(
						sdf.format(new Date()) + "T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				Thread.sleep(1_000);
				System.out.println(sdf.format(new Date()) + "T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void run() {
		try {
			int taskSize = 3;
			for (int i = 0; i < taskSize; i++) {
				Thread thread = new Thread(new Worker(i));
				thread.start();
			}
			Thread.sleep(3_000);
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
					throw new IOException("BrokenWorker " + id + " throw exception");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				System.out.println(
						sdf.format(new Date()) + "T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				Thread.sleep(1_000);
				System.out.println(sdf.format(new Date()) + "T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void runBroken() {
		try {
			int taskSize = 3;
			for (int i = 0; i < taskSize; i++) {
				Thread thread = new Thread(new BrokenWorker(i));
				thread.start();
			}
			Thread.sleep(3_000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class ReturnWorker implements Runnable {

		private int id;
		private Object result;

		public ReturnWorker(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				System.out.println(
						sdf.format(new Date()) + "T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				Thread.sleep(3_000);
				System.out.println(sdf.format(new Date()) + "T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");

				result = "OK";
				synchronized (this) {
					notifyAll();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public synchronized Object get() throws InterruptedException {
			while (result == null) {
				System.out.println("wait");
				wait();
			}
			return result;
		}
	}

	@Test
	public void runReturn() {
		try {
			int taskSize = 3;
			List<ReturnWorker> workers = new ArrayList<ReturnWorker>();
			for (int i = 0; i < taskSize; i++) {
				ReturnWorker worker = new ReturnWorker(i);
				workers.add(worker);
				Thread thread = new Thread(worker);
				thread.start();
			}
			workers.forEach(e -> {
				try {
					Object result = e.get();
					System.out.println("worker: " + e.getId() + ", result: " + result);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
			Thread.sleep(3_000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
