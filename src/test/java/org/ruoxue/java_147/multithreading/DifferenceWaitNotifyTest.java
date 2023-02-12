package org.ruoxue.java_147.multithreading;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class DifferenceWaitNotifyTest {
	protected class Worker {

		private volatile boolean done = false;

		public Worker() {
		}

		public synchronized void put() throws InterruptedException {
			try {
				TimeUnit.SECONDS.sleep(5);
				System.out.println("T[" + Thread.currentThread().getId() + "] put finished");
			} finally {
				done = true;
				notify();
			}
		}

		public synchronized boolean take() throws InterruptedException {
			System.out.println("T[" + Thread.currentThread().getId() + "] take waiting " + done);
			while (!done) {
				System.out.println("T[" + Thread.currentThread().getId() + "] take waiting");
				wait();
			}
			return done;
		}
	}

	@Test
	public void waitNotify() {
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

		threadA.start();
		threadB.start();

		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
