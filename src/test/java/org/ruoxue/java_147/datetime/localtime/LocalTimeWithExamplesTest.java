package org.ruoxue.java_147.datetime.localtime;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class LocalTimeWithExamplesTest {

	@Test
	public void format() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		String result = localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println(result);
		assertEquals("09:12:05", result);

		localTime = LocalTime.of(9, 12, 5, 123456789);
		result = localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println(result);
		assertEquals("09:12:05.123456789", result);

		result = localTime.format(DateTimeFormatter.ofPattern("HH-mm-ss.SSS"));
		System.out.println(result);
		assertEquals("09-12-05.123", result);
	}

	@Test
	public void parse() {
		LocalTime localTime = LocalTime.parse("09:12:05");
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

		localTime = LocalTime.parse("09:12:05.123456789");
		System.out.println(localTime);
		assertEquals("09:12:05.123456789", localTime.toString());

		localTime = LocalTime.parse("09-12-05.123", DateTimeFormatter.ofPattern("HH-mm-ss.SSS"));
		System.out.println(localTime);
		assertEquals("09:12:05.123", localTime.toString());
	}

	@Test
	public void from() {
		LocalTime localTime = LocalTime.parse("09:12:05.123456789");
		LocalTime result = LocalTime.from(localTime);
		System.out.println(result);
		assertEquals("09:12:05.123456789", result.toString());

		localTime = LocalTime.parse("09:12:05.123");
		ZoneOffset zoneOffset = ZoneOffset.of("+01:00");
		OffsetTime offsetTime = OffsetTime.of(localTime, zoneOffset);
		System.out.println(offsetTime);
		result = LocalTime.from(offsetTime);
		System.out.println(result);
		assertEquals("09:12:05.123", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-12-25T09:12:05+01:00[Europe/London]");
		result = LocalTime.from(zonedDateTime);
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
	public void ofNanoOfDay() {
		LocalTime localTime = LocalTime.ofNanoOfDay(33125123456789L);
		System.out.println(localTime);
		assertEquals("09:12:05.123456789", localTime.toString());
	}

	@Test
	public void truncatedTo() {
		LocalTime localTime = LocalTime.of(9, 12, 5, 123456789);
		LocalTime result = localTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("09:00", result.toString());

		result = localTime.truncatedTo(ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals("09:12", result.toString());

		result = localTime.truncatedTo(ChronoUnit.MILLIS);
		System.out.println(result);
		assertEquals("09:12:05.123", result.toString());
	}
}
