package org.ruoxue.java_147.datetime.offsettime;

import static org.junit.Assert.*;

import java.time.LocalTime;
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
	}

	@Test
	public void from() {
		OffsetTime offsetTime = OffsetTime.parse("06:01:04.123456789-05:00");
		OffsetTime result = OffsetTime.from(offsetTime);
		System.out.println(result);
		assertEquals("06:01:04.123456789-05:00", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-11-11T06:01:04-05:00[Brazil/Acre]");
		result = OffsetTime.from(zonedDateTime);
		System.out.println(result);
		assertEquals("06:01:04-05:00", result.toString());
	}

	@Test
	public void toLocalTime() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		LocalTime result = offsetTime.toLocalTime();
		System.out.println(result);
		assertEquals("06:01:04", result.toString());
	}

	@Test
	public void ofLocalTime() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		LocalTime localTime = LocalTime.of(6, 1, 4);
		OffsetTime offsetTime = OffsetTime.of(localTime, offset);
		System.out.println(offsetTime);
		assertEquals("06:01:04-05:00", offsetTime.toString());
	}

	@Test
	public void truncatedTo() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 123456789, offset);
		OffsetTime result = offsetTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("06:00-05:00", result.toString());

		result = offsetTime.truncatedTo(ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals("06:01-05:00", result.toString());

		result = offsetTime.truncatedTo(ChronoUnit.MILLIS);
		System.out.println(result);
		assertEquals("06:01:04.123-05:00", result.toString());
	}
}
