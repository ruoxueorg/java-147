package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class ComparatorInterfaceTest {

	@Test
	public void compare() {
		Comparator<String> comparator = (s, s2) -> s.length() - s2.length();
	}

}
