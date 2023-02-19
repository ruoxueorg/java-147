package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class DifferenceNotifyNotifyAllTest {

	@Test
	public void notifyz() {
		Object lock = new Object();
		List<Thread> threads = Stream.generate(() -> new Thread(() -> {
			try {
				synchronized (lock) {
					System.out.println("T[" + Thread.currentThread().getId() + "] wait");
					lock.wait();
					System.out.println("T[" + Thread.currentThread().getId() + "] awake");
				}
				System.out.println("T[" + Thread.currentThread().getId() + "] finished");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		try {
			TimeUnit.SECONDS.sleep(2);
			synchronized (lock) {
				System.out.println("T[" + Thread.currentThread().getId() + "] notify");
				lock.notify();
			}
			TimeUnit.SECONDS.sleep(3);
			System.out.println("T[" + Thread.currentThread().getId() + "] finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void notifyAllz() {
		final Object lock = new Object();
		List<Thread> threads = Stream.generate(() -> new Thread(() -> {
			try {
				synchronized (lock) {
					System.out.println("T[" + Thread.currentThread().getId() + "] wait");
					lock.wait();
					System.out.println("T[" + Thread.currentThread().getId() + "] awake");
				}
				System.out.println("T[" + Thread.currentThread().getId() + "] finished");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		try {
			TimeUnit.SECONDS.sleep(2);
			synchronized (lock) {
				System.out.println("T[" + Thread.currentThread().getId() + "] notify");
				lock.notifyAll();
			}
			TimeUnit.SECONDS.sleep(3);
			System.out.println("T[" + Thread.currentThread().getId() + "] finished");
			threads.forEach(e -> assertFalse(e.isAlive()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
