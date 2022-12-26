package org.ruoxue.java_147.memory;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.Test;

public class DirectBufferMemoryTest {

	@Test
	public void allocate() {
		System.out.println("freeMemory: "+Runtime.getRuntime().freeMemory() / 1024 / 1024+ " MB");
		ByteBuffer buffer = ByteBuffer.allocate(Integer.MAX_VALUE - 2);
		System.out.println("freeMemory: "+Runtime.getRuntime().freeMemory() / 1024 / 1024+ " MB");
	}

	@Test
	public void allocateDirect() {
		System.out.println("freeMemory: "+Runtime.getRuntime().freeMemory() / 1024 / 1024+ " MB");
		ByteBuffer buffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
		System.out.println("freeMemory: "+Runtime.getRuntime().freeMemory() / 1024 / 1024+ " MB");
	}

}
