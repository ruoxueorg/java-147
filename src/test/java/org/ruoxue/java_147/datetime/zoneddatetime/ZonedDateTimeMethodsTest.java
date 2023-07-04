package org.ruoxue.java_147.datetime.zoneddatetime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class ZonedDateTimeMethodsTest {

	@Test
	public void now() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println(zonedDateTime);

		ZoneId zone = ZoneId.of("Europe/Athens");
		zonedDateTime = ZonedDateTime.now(zone);
		System.out.println(zonedDateTime);

		zone = ZoneId.of("UTC+3");
		Clock clock = Clock.system(zone);
		zonedDateTime = ZonedDateTime.now(clock);
		System.out.println(zonedDateTime);

		zone = ZoneId.of("Etc/GMT-3");
		zonedDateTime = ZonedDateTime.now(zone);
		System.out.println(zonedDateTime);
	}

	@Test
	public void of() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());

		long epochSecond = zonedDateTime.toEpochSecond();
		System.out.println(epochSecond);
		assertEquals(1686024489L, epochSecond);

		zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());

		zonedDateTime = ZonedDateTime.of(LocalDate.of(2023, 6, 6), LocalTime.of(7, 8, 9, 0), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());
	}

	@Test
	public void ofInstant() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.parse("2023-06-06T04:08:09Z"), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());

		zonedDateTime = ZonedDateTime.ofInstant(LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0), ZoneOffset.ofHours(3), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());
	}

	@Test
	public void ofLocal() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofLocal(LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0), zone,
				ZoneOffset.ofHours(3));
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());
	}

	@Test
	public void ofStrict() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofStrict(LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0),
				ZoneOffset.ofHours(3), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());
	}

	@Test
	public void get() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		int year = zonedDateTime.get(ChronoField.YEAR);
		System.out.println(year);
		assertEquals(2023, year);

		int month = zonedDateTime.get(ChronoField.MONTH_OF_YEAR);
		System.out.println(month);
		assertEquals(6, month);

		int dayOfMonth = zonedDateTime.get(ChronoField.DAY_OF_MONTH);
		System.out.println(dayOfMonth);
		assertEquals(6, dayOfMonth);

		int dayOfWeek = zonedDateTime.get(ChronoField.DAY_OF_WEEK);
		System.out.println(dayOfWeek);
		assertEquals(2, dayOfWeek);

		int dayOfYear = zonedDateTime.get(ChronoField.DAY_OF_YEAR);
		System.out.println(dayOfYear);
		assertEquals(157, dayOfYear);

		int hourOfDay = zonedDateTime.get(ChronoField.HOUR_OF_DAY);
		System.out.println(hourOfDay);
		assertEquals(7, hourOfDay);

		int minuteOfHour = zonedDateTime.get(ChronoField.MINUTE_OF_HOUR);
		System.out.println(minuteOfHour);
		assertEquals(8, minuteOfHour);

		int secondOfMinute = zonedDateTime.get(ChronoField.SECOND_OF_MINUTE);
		System.out.println(secondOfMinute);
		assertEquals(9, secondOfMinute);
	}

	@Test
	public void getValue() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		int year = zonedDateTime.getYear();
		System.out.println(year);
		assertEquals(2023, year);

		int month = zonedDateTime.getMonthValue();
		System.out.println(month);
		assertEquals(6, month);

		int dayOfMonth = zonedDateTime.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(6, dayOfMonth);

		int dayOfWeek = zonedDateTime.getDayOfWeek().getValue();
		System.out.println(dayOfWeek);
		assertEquals(2, dayOfWeek);

		int dayOfYear = zonedDateTime.getDayOfYear();
		System.out.println(dayOfYear);
		assertEquals(157, dayOfYear);

		int hour = zonedDateTime.getHour();
		System.out.println(hour);
		assertEquals(7, hour);

		int minute = zonedDateTime.getMinute();
		System.out.println(minute);
		assertEquals(8, minute);

		int second = zonedDateTime.getSecond();
		System.out.println(second);
		assertEquals(9, second);
	}

	@Test
	public void with() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime result = zonedDateTime.withYear(2024);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = zonedDateTime.withMonth(7);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = zonedDateTime.withDayOfMonth(7);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(7, dayOfMonth);

		result = zonedDateTime.withHour(2);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(2, hour);

		result = zonedDateTime.withMinute(3);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = zonedDateTime.withSecond(4);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void withTemporalAdjuster() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime result = zonedDateTime.with(ChronoField.DAY_OF_MONTH, 7);
		System.out.println(result);
		assertEquals("2023-06-07T07:08:09+03:00[Europe/Athens]", result.toString());

		result = zonedDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println(result);
		assertEquals("2023-06-10T07:08:09+03:00[Europe/Athens]", result.toString());

		result = zonedDateTime.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(result);
		assertEquals("2023-06-30T07:08:09+03:00[Europe/Athens]", result.toString());

		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));
		result = zonedDateTime.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("2023-06-20T07:08:09+03:00[Europe/Athens]", result.toString());

		result = zonedDateTime.with(t -> t.plus(Duration.ofHours(1)));
		System.out.println(result);
		assertEquals("2023-06-06T08:08:09+03:00[Europe/Athens]", result.toString());
	}

	@Test
	public void toLocalDate() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		LocalDate result = zonedDateTime.toLocalDate();
		System.out.println(result);
		assertEquals("2023-06-06", result.toString());
	}

	@Test
	public void toLocalTime() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		LocalTime result = zonedDateTime.toLocalTime();
		System.out.println(result);
		assertEquals("07:08:09", result.toString());
	}

	@Test
	public void toLocalDateTime() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		LocalDateTime result = zonedDateTime.toLocalDateTime();
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09", result.toString());
	}
}
