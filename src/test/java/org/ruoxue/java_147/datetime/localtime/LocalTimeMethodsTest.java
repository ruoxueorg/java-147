package org.ruoxue.java_147.datetime.localtime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;

import org.junit.Test;

public class LocalTimeMethodsTest {

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
	}

	@Test
	public void of() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

		long secondOfDay = localTime.toSecondOfDay();
		System.out.println(secondOfDay);
		assertEquals(33125, secondOfDay);

		localTime = LocalTime.of(10, 12, 5);
		System.out.println(localTime);
		assertEquals("10:12:05", localTime.toString());

		localTime = LocalTime.ofSecondOfDay(40325);
		System.out.println(localTime);
		assertEquals("11:12:05", localTime.toString());
	}

	@Test
	public void get() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		int hourOfDay = localTime.get(ChronoField.HOUR_OF_DAY);
		System.out.println(hourOfDay);
		assertEquals(9, hourOfDay);

		int minuteOfHour = localTime.get(ChronoField.MINUTE_OF_HOUR);
		System.out.println(minuteOfHour);
		assertEquals(12, minuteOfHour);

		int secondOfMinute = localTime.get(ChronoField.SECOND_OF_MINUTE);
		System.out.println(secondOfMinute);
		assertEquals(5, secondOfMinute);
	}

	@Test
	public void getValue() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		int hour = localTime.getHour();
		System.out.println(hour);
		assertEquals(9, hour);

		int minute = localTime.getMinute();
		System.out.println(minute);
		assertEquals(12, minute);

		int second = localTime.getSecond();
		System.out.println(second);
		assertEquals(5, second);
	}

	@Test
	public void with() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime result = localTime.withHour(10);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(10, hour);

		result = localTime.withMinute(13);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(13, minute);

		result = localTime.withSecond(6);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(6, second);
	}

	@Test
	public void withTemporalAdjuster() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime result = localTime.with(ChronoField.HOUR_OF_DAY, 10);
		System.out.println(result);
		assertEquals("10:12:05", result.toString());

		TemporalAdjuster temporalAdjuster = t -> t.plus(Duration.ofHours(2));
		result = localTime.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("11:12:05", result.toString());

		result = localTime.with(t -> t.plus(Duration.ofMinutes(1)));
		System.out.println(result);
		assertEquals("09:13:05", result.toString());
		
		result = localTime.with(t -> t.plus(Duration.ofSeconds(1)));
		System.out.println(result);
		assertEquals("09:12:06", result.toString());
	}
}
