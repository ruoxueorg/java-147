package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;

public class LocalDateToDate {

	@Test
	public void toDate() {
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 0, 0, 0);

		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		Date date = Date.from(zonedDateTime.toInstant());
		System.out.println(date);
		assertEquals("Mon Apr 03 21:00:00 CST 2023", date.toString());
	}
	
	@Test
	public void toDateWithToInstant() {
		ZoneId zone = ZoneId.of("Etc/GMT-11");
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 0, 0, 0);

		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		Date date = Date.from(zonedDateTime.toInstant());
		System.out.println(date);
		assertEquals("Mon Apr 03 21:00:00 CST 2023", date.toString());
	}
	
	@Test
	public void toDateWithZonedDateTime() {
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);

		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		Date date = Date.from(zonedDateTime.toInstant());
		System.out.println(date);
		assertEquals("Tue Apr 04 02:04:03 CST 2023", date.toString());
	}
}
