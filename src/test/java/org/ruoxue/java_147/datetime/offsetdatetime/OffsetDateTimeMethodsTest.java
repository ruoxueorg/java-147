package org.ruoxue.java_147.datetime.offsetdatetime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class OffsetDateTimeMethodsTest {

	@Test
	public void now() {
		OffsetDateTime offsetDateTime = OffsetDateTime.now();
		System.out.println(offsetDateTime);

		ZoneId zone = ZoneId.of("Egypt");
		offsetDateTime = OffsetDateTime.now(zone);
		System.out.println(offsetDateTime);

		zone = ZoneId.of("UTC+2");
		Clock clock = Clock.system(zone);
		offsetDateTime = OffsetDateTime.now(clock);
		System.out.println(offsetDateTime);

		zone = ZoneId.of("Etc/GMT-2");
		offsetDateTime = OffsetDateTime.now(zone);
		System.out.println(offsetDateTime);
	}

	@Test
	public void of() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());

		long epochSecond = offsetDateTime.toEpochSecond();
		System.out.println(epochSecond);
		assertEquals(1703473443L, epochSecond);

		offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2023, 12, 25, 5, 4, 3, 0), offset);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());

		offsetDateTime = OffsetDateTime.of(LocalDate.of(2023, 12, 25), LocalTime.of(5, 4, 3, 0), offset);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());
	}

	@Test
	public void ofInstant() {
		ZoneId zone = ZoneId.of("Egypt");
		OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(Instant.parse("2023-12-25T03:04:03Z"), zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());
	}

	@Test
	public void get() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		int year = offsetDateTime.get(ChronoField.YEAR);
		System.out.println(year);
		assertEquals(2023, year);

		int month = offsetDateTime.get(ChronoField.MONTH_OF_YEAR);
		System.out.println(month);
		assertEquals(12, month);

		int dayOfMonth = offsetDateTime.get(ChronoField.DAY_OF_MONTH);
		System.out.println(dayOfMonth);
		assertEquals(25, dayOfMonth);

		int dayOfWeek = offsetDateTime.get(ChronoField.DAY_OF_WEEK);
		System.out.println(dayOfWeek);
		assertEquals(1, dayOfWeek);

		int dayOfYear = offsetDateTime.get(ChronoField.DAY_OF_YEAR);
		System.out.println(dayOfYear);
		assertEquals(359, dayOfYear);

		int hourOfDay = offsetDateTime.get(ChronoField.HOUR_OF_DAY);
		System.out.println(hourOfDay);
		assertEquals(5, hourOfDay);

		int minuteOfHour = offsetDateTime.get(ChronoField.MINUTE_OF_HOUR);
		System.out.println(minuteOfHour);
		assertEquals(4, minuteOfHour);

		int secondOfMinute = offsetDateTime.get(ChronoField.SECOND_OF_MINUTE);
		System.out.println(secondOfMinute);
		assertEquals(3, secondOfMinute);
	}

	@Test
	public void getValue() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		int year = offsetDateTime.getYear();
		System.out.println(year);
		assertEquals(2023, year);

		int month = offsetDateTime.getMonthValue();
		System.out.println(month);
		assertEquals(12, month);

		int dayOfMonth = offsetDateTime.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(25, dayOfMonth);

		int dayOfWeek = offsetDateTime.getDayOfWeek().getValue();
		System.out.println(dayOfWeek);
		assertEquals(1, dayOfWeek);

		int dayOfYear = offsetDateTime.getDayOfYear();
		System.out.println(dayOfYear);
		assertEquals(359, dayOfYear);

		int hour = offsetDateTime.getHour();
		System.out.println(hour);
		assertEquals(5, hour);

		int minute = offsetDateTime.getMinute();
		System.out.println(minute);
		assertEquals(4, minute);

		int second = offsetDateTime.getSecond();
		System.out.println(second);
		assertEquals(3, second);
	}

	@Test
	public void with() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime result = offsetDateTime.withYear(2024);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = offsetDateTime.withMonth(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(1, month);

		result = offsetDateTime.withDayOfMonth(26);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(26, dayOfMonth);

		result = offsetDateTime.withHour(6);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(6, hour);

		result = offsetDateTime.withMinute(5);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(5, minute);

		result = offsetDateTime.withSecond(4);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void withTemporalAdjuster() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime result = offsetDateTime.with(ChronoField.DAY_OF_MONTH, 26);
		System.out.println(result);
		assertEquals("2023-12-26T05:04:03+02:00", result.toString());

		result = offsetDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println(result);
		assertEquals("2023-12-30T05:04:03+02:00", result.toString());

		result = offsetDateTime.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(result);
		assertEquals("2023-12-31T05:04:03+02:00", result.toString());

		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));
		result = offsetDateTime.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("2024-01-08T05:04:03+02:00", result.toString());

		result = offsetDateTime.with(t -> t.plus(Duration.ofHours(1)));
		System.out.println(result);
		assertEquals("2023-12-25T06:04:03+02:00", result.toString());
	}

	@Test
	public void toLocalDate() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		LocalDate result = offsetDateTime.toLocalDate();
		System.out.println(result);
		assertEquals("2023-12-25", result.toString());
	}

	@Test
	public void toLocalTime() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		LocalTime result = offsetDateTime.toLocalTime();
		System.out.println(result);
		assertEquals("05:04:03", result.toString());
	}

	@Test
	public void toLocalDateTime() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		LocalDateTime result = offsetDateTime.toLocalDateTime();
		System.out.println(result);
		assertEquals("2023-12-25T05:04:03", result.toString());
	}
}
