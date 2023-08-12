package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class StringClassTest {

//	@Test
//	public void charAt() {
//		char expected = 'g';
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		char result = builder.charAt(5);
//		System.out.println(result);
//		assertEquals(expected, result);
//
//		result = builder.charAt(7);
//		System.out.println(result);
//		assertEquals('b', result);
//	}
//
//	@Test
//	public void getChars() {
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		IntStream stream = builder.chars();
//		List<Integer> list = stream.boxed().collect(Collectors.toList());
//		System.out.println(list);
//	}
//
//	@Test
//	public void indexOf() {
//		int expected = 3;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		int result = builder.indexOf("i");
//		System.out.println(result);
//		assertEquals(expected, result);
//
//		result = builder.indexOf("i", 2);
//		System.out.println(result);
//		assertEquals(3, result);
//	}
//
//	@Test
//	public void lastIndexOf() {
//		int expected = 3;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		int result = builder.lastIndexOf("i");
//		System.out.println(result);
//		assertEquals(expected, result);
//
//		result = builder.lastIndexOf("i", 5);
//		System.out.println(result);
//		assertEquals(3, result);
//	}
//
//	@Test
//	public void setCharAt() {
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		builder.setCharAt(3, 'I');
//		System.out.println(builder);
//		assertEquals("SprIng boot 168", builder.toString());
//	}

	@Test
	public void substring() {
		String value = "Assertj 155";
		String result = value.substring(8);
		System.out.println(result);
		assertEquals("155", result);

		result = value.substring(0, 7);
		System.out.println(result);
		assertEquals("Assertj", result);
	}

//	@Test
//	public void replace() {
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		builder.replace(0, 4, "SPRING");
//		System.out.println(builder);
//		assertEquals("SPRINGng boot 168", builder.toString());
//	}
}
