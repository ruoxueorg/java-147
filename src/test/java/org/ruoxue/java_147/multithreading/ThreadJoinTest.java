package org.ruoxue.java_147.multithreading;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadJoinTest {

	@Test
	public void join() {
		Thread threadA = new Thread(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String id = "A";
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] " + i);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
		});

		Thread threadB = new Thread(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String id = "B";
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
			try {
				threadA.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			assertFalse(threadA.isAlive());
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] " + i);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
		});

		threadA.start();
		threadB.start();

		try {
			assertTrue(threadA.isAlive());
			threadB.join();
			assertFalse(threadA.isAlive());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		assertFalse(threadB.isAlive());
	}

	@Test
	public void joinTimeout() {
		Thread threadA = new Thread(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String id = "A";
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] " + i);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
		});
		threadA.start();

		try {
			threadA.join(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		assertTrue(threadA.isAlive());
	}

	@Test
	public void mainJoin() {
		Thread threadA = new Thread(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String id = "A";
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] " + i);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
		});

		Thread threadB = new Thread(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String id = "B";
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] " + i);
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			System.out.println(
					sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
		});

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		assertFalse(threadA.isAlive());
		assertFalse(threadB.isAlive());
	}
}
