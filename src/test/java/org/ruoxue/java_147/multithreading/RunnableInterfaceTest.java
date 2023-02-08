package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class RunnableInterfaceTest {

	protected class Worker implements Runnable {

		private int id;
		private boolean done = false;
		private Object result;

		public Worker(int id) {
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
	public void worker() {
		try {
			int taskSize = 3;
			List<Worker> workers = new ArrayList<Worker>();
			for (int i = 0; i < taskSize; i++) {
				Worker worker = new Worker(i);
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

	protected class TimeoutWorker implements Runnable {

		private int id;
		private boolean done = false;
		private Object result;

		public TimeoutWorker(int id) {
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
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				done = true;
				synchronized (this) {
					notifyAll();
				}
			}
		}

		public synchronized Object get(long timeout) throws InterruptedException, TimeoutException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			while (!done) {
				System.out.println(
						sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] get: " + id + " waiting");
				wait(timeout);
				if (Thread.interrupted())
					throw new InterruptedException();
				if (!done)
					throw new TimeoutException("ReturnTimeoutWorker " + id + " timeout");
			}
			return result;
		}
	}

	@Test
	public void timeoutWorker() {
		try {
			int taskSize = 3;
			List<TimeoutWorker> workers = new ArrayList<TimeoutWorker>();
			for (int i = 0; i < taskSize; i++) {
				TimeoutWorker worker = new TimeoutWorker(i);
				workers.add(worker);
				Thread thread = new Thread(worker);
				thread.start();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			workers.forEach(e -> {
				try {
					Object result = e.get(2000);
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
