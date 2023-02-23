package org.ruoxue.java_147.synchronization;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class DifferenceConditionSignalSignalAllTest {

	@Test
	public void signal() {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		List<Thread> threads = Stream.generate(() -> new Thread(() -> {
			try {
				lock.lock();
				try {
					System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
					condition.await();
					System.out.println(String.format("T[%d] finished", Thread.currentThread().getId()));
				} finally {
					lock.unlock();
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		try {
			TimeUnit.SECONDS.sleep(3);
			lock.lock();
			try {
				System.out.println(String.format("T[%d] signal", Thread.currentThread().getId()));
				condition.signal();
				System.out.println(String.format("T[%d] finished", Thread.currentThread().getId()));
			} finally {
				lock.unlock();
			}
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void signalAll() {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		List<Thread> threads = Stream.generate(() -> new Thread(() -> {
			try {
				lock.lock();
				try {
					System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
					condition.await();
					System.out.println(String.format("T[%d] finished", Thread.currentThread().getId()));
				} finally {
					lock.unlock();
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		try {
			TimeUnit.SECONDS.sleep(3);
			lock.lock();
			try {
				System.out.println(String.format("T[%d] signalAll", Thread.currentThread().getId()));
				condition.signalAll();
				System.out.println(String.format("T[%d] finished", Thread.currentThread().getId()));
			} finally {
				lock.unlock();
			}
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
