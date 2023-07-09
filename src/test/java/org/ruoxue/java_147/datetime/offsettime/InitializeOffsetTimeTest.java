package org.ruoxue.java_147.datetime.offsettime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.OffsetTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeOffsetTimeTest {

	@Test
	public void now() {
		OffsetTime offsetTime = OffsetTime.now();
		System.out.println(offsetTime);

		ZoneId zone = ZoneId.of("Europe/London");
		offsetTime = OffsetTime.now(zone);
		System.out.println(offsetTime);

		zone = ZoneId.of("UTC+1");
		Clock clock = Clock.system(zone);
		offsetTime = OffsetTime.now(clock);
		System.out.println(offsetTime);

		zone = ZoneId.of("Etc/GMT-1");
		offsetTime = OffsetTime.now(zone);
		System.out.println(offsetTime);
	}

	@Test
	public void of() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		System.out.println(offsetTime);
		assertEquals("09:12:05", offsetTime.toString());

		int secondOfDay = offsetTime.toSecondOfDay();
		System.out.println(secondOfDay);
		assertEquals(33125L, secondOfDay);

		offsetTime = OffsetTime.of(9, 12, 6, 123456789);
		System.out.println(offsetTime);
		assertEquals("09:12:06.123456789", offsetTime.toString());

		offsetTime = OffsetTime.ofSecondOfDay(33127L);
		System.out.println(offsetTime);
		assertEquals("09:12:07", offsetTime.toString());
	}

	@Test
	public void parse() {
		OffsetTime offsetTime = OffsetTime.parse("09:12:05");
		System.out.println(offsetTime);
		assertEquals("09:12:05", offsetTime.toString());

		offsetTime = OffsetTime.parse("091205", DateTimeFormatter.ofPattern("HHmmss"));
		System.out.println(offsetTime);
		assertEquals("09:12:05", offsetTime.toString());

		offsetTime = OffsetTime.parse("09-12-05.123", DateTimeFormatter.ofPattern("HH-mm-ss.SSS"));
		System.out.println(offsetTime);
		assertEquals("09:12:05.123", offsetTime.toString());
	}

	@Test
	public void from() {
		OffsetTime offsetTime = OffsetTime.parse("09:12:05.123456789");
		OffsetTime result = OffsetTime.from(offsetTime);
		System.out.println(result);
		assertEquals("09:12:05.123456789", result.toString());

		offsetTime = OffsetTime.parse("09:12:05.123");
		ZoneOffset zoneOffset = ZoneOffset.of("+01:00");
		OffsetTime offsetTime = OffsetTime.of(offsetTime, zoneOffset);
		System.out.println(offsetTime);
		result = OffsetTime.from(offsetTime);
		System.out.println(result);
		assertEquals("09:12:05.123", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-12-25T09:12:05+01:00[Europe/London]");
		result = OffsetTime.from(zonedDateTime);
		System.out.println(result);
		assertEquals("09:12:05", result.toString());
	}

	@Test
	public void ofSecondOfDay() {
		OffsetTime offsetTime = OffsetTime.ofSecondOfDay(33125L);
		System.out.println(offsetTime);
		assertEquals("09:12:05", offsetTime.toString());
	}

	@Test
	public void ofNanoOfDay() {
		OffsetTime offsetTime = OffsetTime.ofNanoOfDay(33125123456789L);
		System.out.println(offsetTime);
		assertEquals("09:12:05.123456789", offsetTime.toString());
	}
}
