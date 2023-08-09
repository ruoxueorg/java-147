package org.ruoxue.java_147.conversion.datetime;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;

public class LocalDateTimeToDate {

	@Test
	public void toDate() {
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		Instant instant = zonedDateTime.toInstant();

		Date date = Date.from(instant);
		System.out.println(date);
		assertEquals("Mon Apr 03 21:00:00 CST 2023", date.toString());
	}

	@Test
	public void toDateWithToInstant() {
		ZoneId zone = ZoneId.of("Etc/GMT-11");
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		Instant instant = zonedDateTime.toInstant();

		Date date = Date.from(instant);
		System.out.println(date);
		assertEquals("Mon Apr 03 21:00:00 CST 2023", date.toString());
	}

	@Test
	public void toDateWithValueOf() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
		String value = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Instant instant = Timestamp.valueOf(value).toInstant();

		Date date = Date.from(instant);
		System.out.println(date);
		assertEquals("Tue Apr 04 00:00:00 CST 2023", date.toString());
	}
}
