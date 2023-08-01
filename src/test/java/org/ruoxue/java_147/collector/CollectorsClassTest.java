package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.junit.Test;

public class CollectorsClassTest {

	@Test
	public void averagingInt() {
		Collectors.averagingInt(null);
	}

	@Test
	public void averagingLong() {
	}

	@Test
	public void averagingDouble() {
	}

	@Test
	public void summarizingInt() {
		Collectors.summarizingInt(null);
	}

	@Test
	public void summarizingLong() {
		Collectors.summarizingLong(null);
	}

	@Test
	public void summarizingDouble() {
		Collectors.summarizingDouble(null);
	}

}
