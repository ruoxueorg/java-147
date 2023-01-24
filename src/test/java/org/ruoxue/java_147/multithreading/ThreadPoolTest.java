package org.ruoxue.java_147.multithreading;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ThreadPoolTest {

	@Test
	public void newFixedThreadPool() {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
	}

}
