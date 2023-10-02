package org.ruoxue.java_147.conversion.datetime;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class LocalDateTimeToLocalDateTest {

	@Test
	public void toLocalDate() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		LocalDate result = localDateTime.toLocalDate();
		System.out.println(result);
		assertEquals("2023-04-04", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		result = localDateTime.toLocalDate();
		System.out.println(result);
		assertEquals("2023-04-04", result.toString());
	}

	@Test
	public void toLocalTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		LocalTime result = localDateTime.toLocalTime();
		System.out.println(result);
		assertEquals("05:04:03", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		result = localDateTime.toLocalTime();
		System.out.println(result);
		assertEquals("05:04:03", result.toString());
	}

	@Test
	public void atZoneToLocalDate() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		System.out.println(zonedDateTime);
		LocalDate result = zonedDateTime.toLocalDate();
		System.out.println(result);
		assertEquals("2023-04-04", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		zonedDateTime = localDateTime.atZone(zone);
		result = zonedDateTime.toLocalDate();
		System.out.println(result);
		assertEquals("2023-04-04", result.toString());
	}

	@Test
	public void atZoneToLocalTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		System.out.println(zonedDateTime);
		LocalTime result = zonedDateTime.toLocalTime();
		System.out.println(result);
		assertEquals("05:04:03", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		zonedDateTime = localDateTime.atZone(zone);
		result = zonedDateTime.toLocalTime();
		System.out.println(result);
		assertEquals("05:04:03", result.toString());
	}
}
