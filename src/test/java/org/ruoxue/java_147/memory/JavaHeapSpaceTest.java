package org.ruoxue.java_147.memory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JavaHeapSpaceTest {

	@Test
	public void list() {
		List<byte[]> list = new ArrayList<byte[]>();
		int counter = 1;
		for (;;) {
			byte[] bytes = new byte[100 * 1024 * 1024];
			list.add(bytes);
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
		}
	}

	@Test
	public void integer() {
		List<Integer> list = new ArrayList<Integer>();
		int counter = 1;
		for (;;) {
			list.add(counter);
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
		}
	}

	@Test
	public void byteArray() {
		createBytes();
	}

	public void createBytes() {
		Byte[] bytes = null;
		int counter = 1;
		for (;;) {
			bytes = new Byte[100 * 1024 * 1024];
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
		}
	}

	@Test
	public void normal() {
		int counter = 1;
		for (;;) {
			Byte[] bytes = new Byte[300 * 1024 * 1024];
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
		}
	}
}
