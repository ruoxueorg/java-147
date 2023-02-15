package org.ruoxue.java_147.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class DifferenceThreadRunnableTest {

	protected class Task extends Thread {

		@Override
		public void run() {
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: ready");
				TimeUnit.SECONDS.sleep(3);
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Test
	public void task() {
		Thread thread = new Task();
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	protected class Obj {
		private String name;

		public Obj(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	protected class Worker extends Obj implements Runnable {

		public Worker(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker " + getName() + ": ready");
				TimeUnit.SECONDS.sleep(3);
				System.out.println("T[" + Thread.currentThread().getId() + "] worker " + getName() + ": finished");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void worker() {
		Worker worker = new Worker("AAA");
		Thread thread = new Thread(worker);
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void threadsWorker() {
		try {
			int taskSize = 3;
			Worker worker = new Worker("BBB");
			List<Thread> threads = new ArrayList<Thread>();
			IntStream.range(0, taskSize).forEach(e -> {
				Thread thread = new Thread(worker);
				thread.start();
				threads.add(thread);
			});

			threads.forEach(e -> {
				try {
					e.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
