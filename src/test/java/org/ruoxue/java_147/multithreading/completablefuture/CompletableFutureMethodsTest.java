package org.ruoxue.java_147.multithreading.completablefuture;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class CompletableFutureMethodsTest {

	@Test
	public void complete() {
		try {
			int poolSize = 2;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			int taskSize = 3;
			IntStream.range(0, taskSize).forEach(e -> {
				try {
					CompletableFuture<String> completableFuture = new CompletableFuture<>();
					executorService.submit(() -> {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						System.out.println(String.format("%s T[%d] task: %d ready",
								formatter.format(LocalDateTime.now()), Thread.currentThread().getId(), e));
						TimeUnit.SECONDS.sleep(1);
						completableFuture.complete(String.format("%s T[%d] task: %d finished",
								formatter.format(LocalDateTime.now()), Thread.currentThread().getId(), e));
						return null;
					});
					String result = completableFuture.get();
					System.out.println(result);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
