package org.ruoxue.java_147.datetime.offsettime;

import static org.junit.Assert.*;

import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class OffsetTimeWithExamplesTest {

	@Test
	public void format() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		String result = offsetTime.format(DateTimeFormatter.ISO_OFFSET_TIME);
		System.out.println(result);
		assertEquals("06:01:04-05:00", result);

		offsetTime = OffsetTime.of(6, 1, 4, 123456789, offset);
		result = offsetTime.format(DateTimeFormatter.ISO_OFFSET_TIME);
		System.out.println(result);
		assertEquals("06:01:04.123456789-05:00", result);

		result = offsetTime.format(DateTimeFormatter.ofPattern("HH-mm-ss.SSS-05:00"));
		System.out.println(result);
		assertEquals("06-01-04.123-05:00", result);
	}

	@Test
	public void parse() {
		OffsetTime offsetTime = OffsetTime.parse("06:01:04-05:00");
		System.out.println(offsetTime);
		assertEquals("06:01:04-05:00", offsetTime.toString());

		offsetTime = OffsetTime.parse("06:01:04.123456789-05:00");
		System.out.println(offsetTime);
		assertEquals("06:01:04.123456789-05:00", offsetTime.toString());

		offsetTime = OffsetTime.parse("06-01-04.123", DateTimeFormatter.ofPattern("HH-mm-ss.SSS"));
		System.out.println(offsetTime);
		assertEquals("06:01:04.123-05:00", offsetTime.toString());
	}

	@Test
	public void from() {
		OffsetTime offsetTime = OffsetTime.parse("06:01:04.123456789");
		OffsetTime result = OffsetTime.from(offsetTime);
		System.out.println(result);
		assertEquals("06:01:04.123456789", result.toString());

		offsetTime = OffsetTime.parse("06:01:04.123");
		ZoneOffset zoneOffset = ZoneOffset.of("+01:00");
		OffsetTime offsetTime = OffsetTime.of(offsetTime, zoneOffset);
		System.out.println(offsetTime);
		result = OffsetTime.from(offsetTime);
		System.out.println(result);
		assertEquals("06:01:04.123", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-12-25T06:01:04+01:00[Europe/London]");
		result = OffsetTime.from(zonedDateTime);
		System.out.println(result);
		assertEquals("06:01:04", result.toString());
	}

	@Test
	public void toSecondOfDay() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		long result = offsetTime.toSecondOfDay();
		System.out.println(result);
		assertEquals(33125L, result);
	}

	@Test
	public void ofSecondOfDay() {
		OffsetTime offsetTime = OffsetTime.ofSecondOfDay(33125);
		System.out.println(offsetTime);
		assertEquals("06:01:04", offsetTime.toString());
	}

	@Test
	public void ofNanoOfDay() {
		OffsetTime offsetTime = OffsetTime.ofNanoOfDay(33125123456789L);
		System.out.println(offsetTime);
		assertEquals("06:01:04.123456789", offsetTime.toString());
	}

	@Test
	public void truncatedTo() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5, 123456789);
		OffsetTime result = offsetTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("09:00", result.toString());

		result = offsetTime.truncatedTo(ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals("09:12", result.toString());

		result = offsetTime.truncatedTo(ChronoUnit.MILLIS);
		System.out.println(result);
		assertEquals("06:01:04.123", result.toString());
	}
}
