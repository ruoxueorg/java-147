package org.ruoxue.java_147.datetime.offsettime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;

import org.junit.Test;

public class OffsetTimeMethodsTest {

	@Test
	public void now() {
		OffsetTime offsetTime = OffsetTime.now();
		System.out.println(offsetTime);

		ZoneId zone = ZoneId.of("Brazil/Acre");
		offsetTime = OffsetTime.now(zone);
		System.out.println(offsetTime);

		zone = ZoneId.of("UTC-5");
		Clock clock = Clock.system(zone);
		offsetTime = OffsetTime.now(clock);
		System.out.println(offsetTime);

		zone = ZoneId.of("Etc/GMT+5");
		offsetTime = OffsetTime.now(zone);
		System.out.println(offsetTime);
	}

	@Test
	public void of() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		System.out.println(offsetTime);
		assertEquals("06:01:04-05:00", offsetTime.toString());

		offsetTime = OffsetTime.of(LocalTime.of(6, 1, 4, 0), offset);
		System.out.println(offsetTime);
		assertEquals("06:01:04-05:00", offsetTime.toString());
	}

	@Test
	public void ofInstant() {
		ZoneId zone = ZoneId.of("Brazil/Acre");
		OffsetTime offsetTime = OffsetTime.ofInstant(Instant.parse("2023-11-11T06:01:04Z"), zone);
		System.out.println(offsetTime);
		assertEquals("01:01:04-05:00", offsetTime.toString());
	}

	@Test
	public void get() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);

		int hourOfDay = offsetTime.get(ChronoField.HOUR_OF_DAY);
		System.out.println(hourOfDay);
		assertEquals(6, hourOfDay);

		int minuteOfHour = offsetTime.get(ChronoField.MINUTE_OF_HOUR);
		System.out.println(minuteOfHour);
		assertEquals(1, minuteOfHour);

		int secondOfMinute = offsetTime.get(ChronoField.SECOND_OF_MINUTE);
		System.out.println(secondOfMinute);
		assertEquals(4, secondOfMinute);
	}

	@Test
	public void getValue() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);

		int hour = offsetTime.getHour();
		System.out.println(hour);
		assertEquals(6, hour);

		int minute = offsetTime.getMinute();
		System.out.println(minute);
		assertEquals(1, minute);

		int second = offsetTime.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void with() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);

		OffsetTime result = offsetTime.withHour(7);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(7, hour);

		result = offsetTime.withMinute(2);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(2, minute);

		result = offsetTime.withSecond(5);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(5, second);
	}

	@Test
	public void withTemporalAdjuster() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		
		OffsetTime result = offsetTime.with(ChronoField.HOUR_OF_DAY, 7);
		System.out.println(result);
		assertEquals("07:01:04-05:00", result.toString());

		TemporalAdjuster temporalAdjuster = t -> t.plus(Duration.ofHours(2));
		result = offsetTime.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("08:01:04-05:00", result.toString());

		result = offsetTime.with(t -> t.plus(Duration.ofMinutes(1)));
		System.out.println(result);
		assertEquals("06:02:04-05:00", result.toString());

		result = offsetTime.with(t -> t.plus(Duration.ofSeconds(1)));
		System.out.println(result);
		assertEquals("06:01:05-05:00", result.toString());
	}

	@Test
	public void toLocalTime() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		LocalTime result = offsetTime.toLocalTime();
		System.out.println(result);
		assertEquals("06:01:04", result.toString());
	}
}
