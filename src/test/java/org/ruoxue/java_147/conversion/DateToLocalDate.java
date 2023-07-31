package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateToLocalDate {

	@Test
	public void toLocalDateWithToInstant() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, 3, 4);
		Date date = calendar.getTime();

		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDate localDate = date.toInstant().atZone(zone).toLocalDate();
		System.out.println(localDate);
		assertEquals("2023-04-04", localDate.toString());
	}

	@Test
	public void toLocalDateWithOfEpochMilli() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, 3, 4);
		Date date = calendar.getTime();

		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
		System.out.println(localDate);
		assertEquals("2023-04-04", localDate.toString());
	}

	@Test
	public void toZonedDateTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, 3, 4, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();

		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime zonedDateTime = date.toInstant().atZone(zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-04-04T03:00+11:00[Asia/Sakhalin]", zonedDateTime.toString());
	}

	@Test
	public void toLocalDateWithSqlDate() {
		java.sql.Date sqlDate = java.sql.Date.valueOf("2023-04-04");
		LocalDate localDate = sqlDate.toLocalDate();
		System.out.println(localDate);
		assertEquals("2023-04-04", localDate.toString());
	}
}
