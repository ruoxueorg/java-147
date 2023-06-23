package org.ruoxue.java_147.datetime.localtime;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class LocalTimeWithExamplesTest {

	@Test
	public void format() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		String result = localTime.format(DateTimeFormatter.ISO_TIME);
		System.out.println(result);
		assertEquals("09:12:05", result);

		result = localTime.format(DateTimeFormatter.ofPattern("HH-mm-ss"));
		System.out.println(result);
		assertEquals("09-12-05", result);
	}

	@Test
	public void parse() {
		LocalTime localTime = LocalTime.parse("09:12:05");
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

		localTime = LocalTime.parse("09-12-05", DateTimeFormatter.ofPattern("HH-mm-ss"));
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());
	}

	@Test
	public void from() {
		LocalTime localTime = LocalTime.parse("09:12:05");
		LocalTime result = LocalTime.from(localTime);
		System.out.println(result);
		assertEquals("09:12:05", result.toString());
	}

	@Test
	public void toSecondOfDay() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		long result = localTime.toSecondOfDay();
		System.out.println(result);
		assertEquals(33125L, result);
	}

	@Test
	public void ofSecondOfDay() {
		LocalTime localTime = LocalTime.ofSecondOfDay(33125);
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());
	}
	
	@Test
	public void truncatedTo() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime result = localTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("09:00", result.toString());

		result = localTime.truncatedTo(ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals("09:12", result.toString());
	}
}
