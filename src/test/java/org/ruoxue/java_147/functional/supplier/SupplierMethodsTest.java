package org.ruoxue.java_147.functional.supplier;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

public class SupplierMethodsTest {

	@Test
	public void get() {
		Supplier<String> supplier = () -> "Bacon";
		String result = supplier.get();
		System.out.println(result);
		assertNotNull(result);

		Supplier<Integer> intSupplier = () -> 5;
		int intResult = intSupplier.get();
		System.out.println(intResult);
		assertTrue(intResult > 0);

		Supplier<List<String>> listSupplier = ArrayList::new;
		List<String> listResult = listSupplier.get();
		System.out.println(listResult);
		assertNotNull(listResult);
	}

	public static class DefaultValue<E> implements Supplier<String> {
		@Override
		public String get() {
			return "DEFAULT_VALUE";
		}
	}

	@Test
	public void traditional() {
		DefaultValue<String> defaultValue = new DefaultValue<String>();
		String result = defaultValue.get();
		System.out.println(result);
		assertNotNull(result);
	}
}
