package org.ruoxue.java_147.datetime.localtime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeLocalTimeTest {

	@Test
	public void now() {
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);

		ZoneId zone = ZoneId.of("Europe/London");
		localTime = LocalTime.now(zone);
		System.out.println(localTime);

		zone = ZoneId.of("UTC+1");
		Clock clock = Clock.system(zone);
		localTime = LocalTime.now(clock);
		System.out.println(localTime);

		zone = ZoneId.of("Etc/GMT-1");
		localTime = LocalTime.now(zone);
		System.out.println(localTime);
	}

	@Test
	public void of() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

		int secondOfDay = localTime.toSecondOfDay();
		System.out.println(secondOfDay);
		assertEquals(33125L, secondOfDay);

		localTime = LocalTime.of(9, 12, 5, 123456789);
		System.out.println(localTime);
		assertEquals("09:12:05.123456789", localTime.toString());

		localTime = LocalTime.ofSecondOfDay(33127L);
		System.out.println(localTime);
		assertEquals("09:12:07", localTime.toString());
	}

	@Test
	public void parse() {
		LocalTime localTime = LocalTime.parse("09:12:05");
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

		localTime = LocalTime.parse("091205", DateTimeFormatter.ofPattern("HHmmss"));
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

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
	public void ofSecondOfDay() {
		LocalTime localTime = LocalTime.ofSecondOfDay(33125L);
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());
	}

	@Test
	public void ofNanoOfDay() {
		LocalTime localTime = LocalTime.ofNanoOfDay(33125123456789L);
		System.out.println(localTime);
		assertEquals("09:12:05.123456789", localTime.toString());
	}
}
