package org.ruoxue.java_147.string;

import java.math.BigInteger;

import org.junit.Test;

public class StringFormatTest {

	@Test
	public void string() {
		String value = "Ruoxue";
		String formatted = String.format("String is: %s", value);
		System.out.println(formatted);

		value = "Ruoxue";
		formatted = String.format("String is: %10s", value);
		System.out.println(formatted);
	}

	@Test
	public void charz() {
		char value = 'R';
		String formatted = String.format("Char is: %c", value);
		System.out.println(formatted);

		formatted = String.format("Char is: %10c", value);
		System.out.println(formatted);
	}

	@Test
	public void bolleanz() {
		boolean value = true;
		String formatted = String.format("Boolean is: %b", value);
		System.out.println(formatted);

		value = false;
		formatted = String.format("Boolean is: %10b", value);
		System.out.println(formatted);
	}

	@Test
	public void integer() {
		byte byteValue = Byte.MAX_VALUE;
		String formatted = String.format("Byte is: %d", byteValue);
		System.out.println(formatted);

		short shortValue = Short.MAX_VALUE;
		formatted = String.format("Short is: %d", shortValue);
		System.out.println(formatted);

		int intValue = Integer.MAX_VALUE;
		formatted = String.format("Integer is: %d", intValue);
		System.out.println(formatted);

		long longValue = Long.MAX_VALUE;
		formatted = String.format("Long is: %d", longValue);
		System.out.println(formatted);

		BigInteger bigIntegerValue = BigInteger.ZERO;
		formatted = String.format("BigInteger is: %d", bigIntegerValue);
		System.out.println(formatted);
	}

	@Test
	public void hex() {
		int intValue = Integer.MAX_VALUE;
		String formatted = String.format("Hex is: %x", intValue);
		System.out.println(formatted);
		
		formatted = String.format("Hex is: %10x", intValue);
		System.out.println(formatted);
		
		formatted = String.format("Hex is: %010x", intValue);
		System.out.println(formatted);

	}

	@Test
	public void floating() {
		float floatValue = Float.MAX_VALUE;
		String formatted = String.format("Float is: %f", floatValue);
		System.out.println(formatted);

		floatValue = 1.47f;
		formatted = String.format("Float is: %10.4f", floatValue);
		System.out.println(formatted);

		double doubleValue = Double.MAX_VALUE;
		formatted = String.format("Double is: %e", doubleValue);
		System.out.println(formatted);
	}

	@Test
	public void padding() {
		int intValue = 147;
		String formatted = String.format("%010d", intValue);
		System.out.println(formatted);

		formatted = String.format("% d", intValue);
		System.out.println(formatted);

		formatted = String.format("%10d", intValue);
		System.out.println(formatted);

		formatted = String.format("%-10d", intValue);
		System.out.println(formatted);
	}
}
