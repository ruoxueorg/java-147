package org.ruoxue.java_147.conversion.datetime;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateToLocalDateTime {

	@Test
	public void toLocalDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, 3, 4, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();

		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDateTime localDateTime = date.toInstant().atZone(zone).toLocalDateTime();
		System.out.println(localDateTime);
		assertEquals("2023-04-04T03:00", localDateTime.toString());
	}

	@Test
	public void toLocalDateTimeWithOfEpochMilli() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, 3, 4, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();

		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
		System.out.println(localDateTime);
		assertEquals("2023-04-04T03:00", localDateTime.toString());
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
}
