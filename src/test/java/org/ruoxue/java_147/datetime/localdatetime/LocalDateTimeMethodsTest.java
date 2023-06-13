package org.ruoxue.java_147.datetime.localdatetime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class LocalDateTimeMethodsTest {

	@Test
	public void now() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		ZoneId zone = ZoneId.of("America/Chicago");
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.of("UTC-4");
		Clock clock = Clock.system(zone);
		localDateTime = LocalDateTime.now(clock);
		System.out.println(localDateTime);
	}

	@Test
	public void of() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		long epochSecond = localDateTime.toEpochSecond(ZoneOffset.UTC);
		System.out.println(epochSecond);
		assertEquals(1691024523, epochSecond);

		localDateTime = LocalDateTime.of(2023, Month.AUGUST, 4, 1, 2, 3);
		System.out.println(localDateTime);
		assertEquals("2023-08-04T01:02:03", localDateTime.toString());

		localDateTime = LocalDateTime.ofEpochSecond(1691197323, 0, ZoneOffset.UTC);
		System.out.println(localDateTime);
		assertEquals("2023-08-05T01:02:03", localDateTime.toString());
	}

	@Test
	public void get() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		int year = localDateTime.get(ChronoField.YEAR);
		System.out.println(year);
		assertEquals(2023, year);

		int month = localDateTime.get(ChronoField.MONTH_OF_YEAR);
		System.out.println(month);
		assertEquals(8, month);

		int dayOfMonth = localDateTime.get(ChronoField.DAY_OF_MONTH);
		System.out.println(dayOfMonth);
		assertEquals(3, dayOfMonth);

		int dayOfWeek = localDateTime.get(ChronoField.DAY_OF_WEEK);
		System.out.println(dayOfWeek);
		assertEquals(4, dayOfWeek);

		int dayOfYear = localDateTime.get(ChronoField.DAY_OF_YEAR);
		System.out.println(dayOfYear);
		assertEquals(215, dayOfYear);

		int hourOfDay = localDateTime.get(ChronoField.HOUR_OF_DAY);
		System.out.println(hourOfDay);
		assertEquals(1, hourOfDay);

		int minuteOfHour = localDateTime.get(ChronoField.MINUTE_OF_HOUR);
		System.out.println(minuteOfHour);
		assertEquals(2, minuteOfHour);

		int secondOfMinute = localDateTime.get(ChronoField.SECOND_OF_MINUTE);
		System.out.println(secondOfMinute);
		assertEquals(3, secondOfMinute);
	}

	@Test
	public void getValue() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		int year = localDateTime.getYear();
		System.out.println(year);
		assertEquals(2023, year);

		int month = localDateTime.getMonthValue();
		System.out.println(month);
		assertEquals(8, month);

		int dayOfMonth = localDateTime.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(3, dayOfMonth);

		int dayOfWeek = localDateTime.getDayOfWeek().getValue();
		System.out.println(dayOfWeek);
		assertEquals(4, dayOfWeek);

		int dayOfYear = localDateTime.getDayOfYear();
		System.out.println(dayOfYear);
		assertEquals(215, dayOfYear);

		int hourOfDay = localDateTime.getHour();
		System.out.println(hourOfDay);
		assertEquals(1, hourOfDay);

		int minuteOfHour = localDateTime.getMinute();
		System.out.println(minuteOfHour);
		assertEquals(2, minuteOfHour);

		int secondOfMinute = localDateTime.getSecond();
		System.out.println(secondOfMinute);
		assertEquals(3, secondOfMinute);
	}

	@Test
	public void with() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = localDateTime.withYear(2024);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = localDateTime.withMonth(9);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(9, month);

		result = localDateTime.withDayOfMonth(4);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(4, dayOfMonth);

		result = localDateTime.withHour(2);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(2, hour);

		result = localDateTime.withMinute(3);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = localDateTime.withSecond(4);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void withTemporalAdjuster() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = localDateTime.with(ChronoField.DAY_OF_MONTH, 4);
		System.out.println(result);
		assertEquals("2023-08-04T01:02:03", result.toString());

		result = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println(result);
		assertEquals("2023-08-05T01:02:03", result.toString());

		result = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(result);
		assertEquals("2023-08-31T01:02:03", result.toString());

		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));
		result = localDateTime.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("2023-08-17T01:02:03", result.toString());

		result = localDateTime.with(t -> t.plus(Duration.ofHours(1)));
		System.out.println(result);
		assertEquals("2023-08-03T02:02:03", result.toString());

	}
}
