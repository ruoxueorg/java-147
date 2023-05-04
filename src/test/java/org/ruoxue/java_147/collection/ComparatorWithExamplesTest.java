package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class ComparatorWithExamplesTest {

	@Test
	public void comparing() {
		Comparator.comparing(null);
	}

	@Test
	public void comparingFunctionComparator() {
		Comparator.comparing(null, null);
	}

	@Test
	public void comparingInt() {
		Comparator.comparingInt(null);
	}

	@Test
	public void comparingLong() {
		Comparator.comparingLong(null);
	}

	@Test
	public void comparingDouble() {
		Comparator.comparingDouble(null);
	}

	@Test
	public void nullsFirst() {
		Comparator.nullsFirst(null);
	}

	@Test
	public void nullsLast() {
		Comparator.nullsLast(null);
	}

	@Test
	public void naturalOrder() {
		Comparator.naturalOrder();
	}

	@Test
	public void reverseOrder() {
		Comparator.reverseOrder();
	}
}
