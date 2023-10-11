package org.ruoxue.java_147.conversion.datetime;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class LocalDateToZonedDateTimeTest {

	@Test
	public void atStartOfDay() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		System.out.println(localDate);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime result = localDate.atStartOfDay(zone);
		System.out.println(result);
		assertEquals("2023-04-04T00:00+11:00[Asia/Sakhalin]", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		System.out.println(localDate);
		result = localDate.atStartOfDay(zone);
		System.out.println(result);
		assertEquals("2023-04-04T00:00+11:00[Asia/Sakhalin]", result.toString());
	}

	@Test
	public void atZone() {
		LocalDate localDate = LocalDate.of(2023, 4, 4);
		System.out.println(localDate);
		LocalDateTime localDateTime = localDate.atTime(0, 0, 0);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime result = localDateTime.atZone(zone);
		System.out.println(result);
		assertEquals("2023-04-04T00:00+11:00[Asia/Sakhalin]", result.toString());

		localDate = LocalDate.parse("2023-04-04");
		System.out.println(localDate);
		result = localDateTime.atZone(zone);
		System.out.println(result);
		assertEquals("2023-04-04T00:00+11:00[Asia/Sakhalin]", result.toString());
	}
}
