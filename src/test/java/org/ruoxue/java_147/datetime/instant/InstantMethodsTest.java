package org.ruoxue.java_147.datetime.instant;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class InstantMethodsTest {

	@Test
	public void now() {
		Instant instant = Instant.now();
		System.out.println(instant);

		ZoneId zone = ZoneId.of("America/New_York");
		Clock clock = Clock.system(zone);
		instant = Instant.now(clock);
		System.out.println(instant);

		zone = ZoneId.of("UTC-4");
		clock = Clock.system(zone);
		instant = Instant.now(clock);
		System.out.println(instant);
	}

	@Test
	public void parse() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		System.out.println(instant.toEpochMilli());
		assertEquals("2023-09-12T04:05:06.123456789Z", instant.toString());
	}

	@Test
	public void ofEpochMilli() {
		Instant instant = Instant.ofEpochMilli(1694491506123L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06.123Z", instant.toString());
	}

	@Test
	public void ofEpochSecond() {
		Instant instant = Instant.ofEpochSecond(1694491506L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06Z", instant.toString());

		instant = Instant.ofEpochSecond(1694491506L, 123456789L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06.123456789Z", instant.toString());
	}

	@Test
	public void get() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		int milli = instant.get(ChronoField.MILLI_OF_SECOND);
		System.out.println(milli);
		assertEquals(123, milli);

		int nano = instant.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nano);
		assertEquals(123456789, nano);
	}

	@Test
	public void getNano() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		int nano = instant.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nano);
		assertEquals(123456789, nano);
	}

	@Test
	public void with() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.withYear(2024);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = localDate.withMonth(7);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = localDate.withDayOfMonth(19);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(19, dayOfMonth);
	}

	@Test
	public void withTemporalAdjuster() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.with(ChronoField.DAY_OF_MONTH, 19);
		System.out.println(result);
		assertEquals("2023-06-19", result.toString());

		result = localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println(result);
		assertEquals("2023-06-24", result.toString());

		result = localDate.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(result);
		assertEquals("2023-06-30", result.toString());

		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));
		result = localDate.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("2023-07-02", result.toString());
	}
}
