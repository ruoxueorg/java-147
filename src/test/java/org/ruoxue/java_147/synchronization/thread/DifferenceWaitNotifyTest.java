package org.ruoxue.java_147.synchronization.thread;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DifferenceWaitNotifyTest {

	protected class Worker {

		private volatile boolean done = false;

		public Worker() {
		}

		public synchronized void put() throws InterruptedException {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("T[" + Thread.currentThread().getId() + "] put finished");
			done = true;
			notify();
		}

		public synchronized boolean take() throws InterruptedException {
			while (!done) {
				System.out.println("T[" + Thread.currentThread().getId() + "] take waiting");
				wait();
			}
			return done;
		}
	}

	@Test
	public void worker() {
		Worker worker = new Worker();
		Thread threadA = new Thread(() -> {
			String id = "A";
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				worker.put();
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});

		Thread threadB = new Thread(() -> {
			String id = "B";
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				boolean done = worker.take();
				System.out.println(
						"T[" + Thread.currentThread().getId() + "] worker: " + id + " finished, result: " + done);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});

		threadB.start();
		threadA.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	protected class InterruptWorker {

		private volatile boolean done = false;

		public InterruptWorker() {
		}

		public synchronized void put() throws InterruptedException {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("T[" + Thread.currentThread().getId() + "] put finished");
			done = true;
			// notify();
		}

		public synchronized boolean take() throws InterruptedException {
			while (!done) {
				System.out.println("T[" + Thread.currentThread().getId() + "] take waiting");
				wait();
			}
			return done;
		}
	}

	@Test
	public void interruptWorker() {
		InterruptWorker worker = new InterruptWorker();
		Thread threadA = new Thread(() -> {
			String id = "A";
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				worker.put();
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});

		Thread threadB = new Thread(() -> {
			String id = "B";
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				boolean done = worker.take();
				System.out.println(
						"T[" + Thread.currentThread().getId() + "] worker: " + id + " finished, result: " + done);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});

		threadB.start();
		threadA.start();

		try {
			threadA.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		long startTime = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - startTime > 1000) {
				threadB.interrupt();
				break;
			}
		}
	}

	protected class IllegalMonitorWorker {

		private volatile boolean done = false;
		private final Object lock = new Object();

		public IllegalMonitorWorker() {
		}

		public void put() throws InterruptedException {
			synchronized (lock) {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("T[" + Thread.currentThread().getId() + "] put finished");
				done = true;
				notify();
			}
		}

		public boolean take() throws InterruptedException {
			synchronized (lock) {
				while (!done) {
					System.out.println("T[" + Thread.currentThread().getId() + "] take waiting");
					wait();
				}
			}
			return done;
		}
	}

	@Test
	public void illegalMonitorWorker() {
		IllegalMonitorWorker worker = new IllegalMonitorWorker();
		Thread threadA = new Thread(() -> {
			String id = "A";
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				worker.put();
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});

		Thread threadB = new Thread(() -> {
			String id = "B";
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				boolean done = worker.take();
				System.out.println(
						"T[" + Thread.currentThread().getId() + "] worker: " + id + " finished, result: " + done);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});

		threadB.start();
		threadA.start();

		try {
			threadB.join();
			threadA.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
