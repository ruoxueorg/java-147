package org.ruoxue.java_147.multithreading;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorServiceMethodsTest {

	@Test
	public void execute() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, corePoolSize * 50, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
		

	}

}
