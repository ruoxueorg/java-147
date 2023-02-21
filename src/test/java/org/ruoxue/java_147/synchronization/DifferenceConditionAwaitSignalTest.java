package org.ruoxue.java_147.synchronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class DifferenceConditionAwaitSignalTest {

	protected class Worker {

		private volatile boolean done = false;
		private final Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		public Worker() {
		}

		public void put() throws InterruptedException {
			lock.lock();
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("T[" + Thread.currentThread().getId() + "] put finished");
				done = true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public boolean take() throws InterruptedException {
			lock.lock();
			try {
				while (!done) {
					System.out.println("T[" + Thread.currentThread().getId() + "] take waiting");
					condition.await();
				}
			} finally {
				lock.unlock();
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
		private final Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		public InterruptWorker() {
		}

		public void put() throws InterruptedException {
			lock.lock();
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("T[" + Thread.currentThread().getId() + "] put finished");
				done = true;
				// condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public boolean take() throws InterruptedException {
			lock.lock();
			try {
				while (!done) {
					System.out.println("T[" + Thread.currentThread().getId() + "] take waiting");
					condition.await();
				}
			} finally

			{
				lock.unlock();
			}
			System.out.println(lock);
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

}
