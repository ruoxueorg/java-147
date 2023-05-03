package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class ComparableInterfaceTest {

	@Test
	public void compare() {
		Comparable<String> comparable = (s) -> s.length();
	}

}
