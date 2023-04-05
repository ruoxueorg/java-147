package org.ruoxue.java_147.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.Test;

public class BiConsumerExamplesTest {

	@Test
	public void mapPut() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		BiConsumer<String, Integer> mapPut = map::put;
		mapPut.accept("Bacon", 1);
		mapPut.accept("Ham", 2);
		mapPut.accept("Pork", 3);
		System.out.println(map);
	}
}
