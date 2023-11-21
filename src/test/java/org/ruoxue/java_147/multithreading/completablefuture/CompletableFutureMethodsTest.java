package org.ruoxue.java_147.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class CompletableFutureMethodsTest {

	@Test
	public void complete() {
		try {
			int poolSize = 3;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			CompletableFuture<String> completableFuture = new CompletableFuture<>();
			executorService.submit(() -> {
				completableFuture.complete("Hello");
				return null;
			});

			String result = completableFuture.get();
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
