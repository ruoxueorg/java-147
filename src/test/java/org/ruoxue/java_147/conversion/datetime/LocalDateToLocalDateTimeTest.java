package org.ruoxue.java_147.conversion.datetime;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;

public class LocalDateToLocalDateTimeTest {

	@Test
	public void atStartOfDay() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		LocalDateTime result = localDate.atStartOfDay();
		System.out.println(result);
		assertEquals("2023-04-04T00:00", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		result = localDate.atStartOfDay();
		System.out.println(result);
		assertEquals("2023-04-04T00:00", result.toString());
	}

	@Test
	public void atStartOfDayWithZone() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(zone);
		LocalDateTime result = zonedDateTime.toLocalDateTime();
		System.out.println(result);
		assertEquals("2023-04-04T00:00", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		zonedDateTime = localDate.atStartOfDay(zone);
		result = zonedDateTime.toLocalDateTime();
		System.out.println(result);
		assertEquals("2023-04-04T00:00", result.toString());
	}

	@Test
	public void atTime() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		LocalTime localTime = LocalTime.of(5, 4, 3, 21);
		LocalDateTime result = localDate.atTime(localTime);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03.000000021", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		localTime = LocalTime.parse("05:04:03.000000021");
		result = localDate.atTime(localTime);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03.000000021", result.toString());
	}

	@Test
	public void atTimeWithMinute() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		LocalDateTime result = localDate.atTime(5, 4);
		System.out.println(result);
		assertEquals("2023-04-04T05:04", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		result = localDate.atTime(5, 4);
		System.out.println(result);
		assertEquals("2023-04-04T05:04", result.toString());
	}

	@Test
	public void atTimeWithSecond() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		LocalDateTime result = localDate.atTime(5, 4, 3);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		result = localDate.atTime(5, 4, 3);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03", result.toString());
	}

	@Test
	public void atTimeWithNano() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		LocalDateTime result = localDate.atTime(5, 4, 3, 21);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03.000000021", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		result = localDate.atTime(5, 4, 3, 21);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03.000000021", result.toString());
	}

	@Test
	public void atTimeWithOffsetTime() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		ZoneOffset offset = ZoneOffset.ofHours(11);
		OffsetTime offsetTime = OffsetTime.of(5, 4, 3, 21, offset);
		System.out.println(offsetTime);
		OffsetDateTime offsetDateTime = localDate.atTime(offsetTime);
		LocalDateTime result = offsetDateTime.toLocalDateTime();
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03.000000021", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		offsetDateTime = localDate.atTime(offsetTime);
		result = offsetDateTime.toLocalDateTime();
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03.000000021", result.toString());
	}
}
