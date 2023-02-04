package org.ruoxue.java_147.multithreading;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class RunnableWithExamplesTest {

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
	public void run() {
		try {
			int taskSize = 3;
			for (int i = 0; i < taskSize; i++) {
				Thread thread = new Thread(new Worker(i));
				thread.start();
			}
			TimeUnit.SECONDS.sleep(5);
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
	public void runBroken() {
		try {
			int taskSize = 3;
			for (int i = 0; i < taskSize; i++) {
				Thread thread = new Thread(new BrokenWorker(i));
				thread.start();
			}
			TimeUnit.SECONDS.sleep(5);
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
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(3 + id);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			while (result == null) {
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] get: " + id + " waiting");
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

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			workers.forEach(e -> {
				try {
					Object result = e.get();
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: "
							+ e.getId() + " result " + result);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class ReturnTimeoutWorker implements Runnable {

		private int id;
		private Object result;

		public ReturnTimeoutWorker(int id) {
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
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(3);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			while (result == null) {
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] get: " + id + " waiting");
				wait(2000);
				if (Thread.interrupted())
					throw new InterruptedException();
				if (result == null)
					throw new InterruptedException();
			}
			return result;
		}
	}

	@Test
	public void runReturnTimeout() {
		try {
			int taskSize = 1;
			List<ReturnTimeoutWorker> workers = new ArrayList<ReturnTimeoutWorker>();
			for (int i = 0; i < taskSize; i++) {
				ReturnTimeoutWorker worker = new ReturnTimeoutWorker(i);
				workers.add(worker);
				Thread thread = new Thread(worker);
				thread.start();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			workers.forEach(e -> {
				try {
					Object result = e.get();
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: "
							+ e.getId() + " result " + result);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
