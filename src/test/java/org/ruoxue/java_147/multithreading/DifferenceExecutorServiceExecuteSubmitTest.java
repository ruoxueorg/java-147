package org.ruoxue.java_147.multithreading;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DifferenceExecutorServiceExecuteSubmitTest {

	@Test
	public void execute() {
		int poolSize = 2;
		int taskSize = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		for (int i = 0; i < taskSize; i++) {
			executorService.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("T[" + Thread.currentThread().getId() + "] OK");
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
		}

		try {
			executorService.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void submit() {
		int poolSize = 2;
		int taskSize = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i = 0; i < taskSize; i++) {
			Future<String> future = executorService.submit(() -> {
				String result = null;
				try {
					TimeUnit.SECONDS.sleep(1);
					result = "T[" + Thread.currentThread().getId() + "] OK";
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				return result;
			});
			futures.add(future);
		}

		futures.forEach(e -> {
			try {
				String result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "]: " + result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Test
	public void brokenSubmit() {
		int poolSize = 2;
		int taskSize = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i = 0; i < taskSize; i++) {
			Future<String> future = executorService.submit(() -> {
				String result = null;
				boolean flag = true;
				if (flag) {
					throw new IOException();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
					result = "T[" + Thread.currentThread().getId() + "] OK";
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				return result;
			});
			futures.add(future);
		}

		futures.forEach(e -> {
			try {
				String result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "]: " + result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}
}
