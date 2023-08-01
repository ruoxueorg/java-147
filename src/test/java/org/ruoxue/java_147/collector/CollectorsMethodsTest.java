package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.junit.Test;

public class CollectorsMethodsTest {

	@Test
	public void maxBy() {
		Collectors.maxBy(null);
	}

	@Test
	public void minBy() {
		Collectors.minBy(null);
	}

	@Test
	public void groupingBy() {
		Collectors.groupingBy(null, null);
	}

	@Test
	public void partitioningBy() {
		Collectors.partitioningBy(null, null);
	}

}
