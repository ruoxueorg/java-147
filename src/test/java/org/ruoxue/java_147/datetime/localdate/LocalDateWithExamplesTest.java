package org.ruoxue.java_147.datetime.localdate;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class LocalDateWithExamplesTest {

	@Test
	public void format() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		String result = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(result);
		assertEquals("2023-06-18", result);

		result = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		System.out.println(result);
		assertEquals("2023/06/18", result);
	}

	@Test
	public void parse() {
		LocalDate localDate = LocalDate.parse("2023-06-18");
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());

		localDate = LocalDate.parse("2023/06/18", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());
	}

	@Test
	public void from() {
		LocalDate localDate = LocalDate.parse("2023-06-18");
		LocalDate result = LocalDate.from(localDate);
		System.out.println(result);
		assertEquals("2023-06-18", result.toString());
	}

	@Test
	public void toEpochDay() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		long result = localDate.toEpochDay();
		System.out.println(result);
		assertEquals(19526L, result);
	}

	@Test
	public void ofEpochDay() {
		LocalDate localDate = LocalDate.ofEpochDay(19526L);
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());
	}
}
