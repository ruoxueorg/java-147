package org.ruoxue.java_147.memory;

import org.junit.Test;

public class UnableToCreateNewNativeThreadTest {

	@Test
	public void thread() {
		for (;;) {
			new Thread(() -> {
				try {
					Thread.sleep(Integer.MAX_VALUE);
				} catch (InterruptedException e) {
				}
			}).start();
		}
	}
}
