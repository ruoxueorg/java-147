package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class RunnableInterfaceTest {

	protected class ReturnWorker<T> implements Runnable {

		private int id;
		private boolean done = false;
		private T result;

		public ReturnWorker(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(3);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");

				result = (T) "OK";
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				done = true;
				synchronized (this) {
					notifyAll();
				}
			}
		}

		public synchronized Object get() throws InterruptedException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			while (!done) {
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] get: " + id + " waiting");
				wait();
			}
			return result;
		}
	}

	@Test
	public void returnWorker() {
		try {
			int taskSize = 3;
			List<ReturnWorker<String>> workers = new ArrayList<ReturnWorker<String>>();
			for (int i = 0; i < taskSize; i++) {
				ReturnWorker<String> worker = new ReturnWorker<String>(i);
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
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected class ReturnTimeoutWorker<T> implements Runnable {

		private int id;
		private boolean done = false;
		private T result;

		public ReturnTimeoutWorker(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(3);
				System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id
						+ " finished");

				result = (T) "OK";
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				done = true;
				synchronized (this) {
					notifyAll();
				}
			}
		}

		public synchronized Object get() throws InterruptedException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			while (!done) {
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] get: " + id + " waiting");
				wait(2000);
				if (Thread.interrupted())
					throw new InterruptedException();
				if (!done)
					throw new InterruptedException("ReturnTimeoutWorker " + id + " Timeout");
			}
			return result;
		}
	}

	@Test
	public void returnTimeoutWorker() {
		try {
			int taskSize = 3;
			List<ReturnTimeoutWorker<String>> workers = new ArrayList<ReturnTimeoutWorker<String>>();
			for (int i = 0; i < taskSize; i++) {
				ReturnTimeoutWorker<String> worker = new ReturnTimeoutWorker<String>(i);
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
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
