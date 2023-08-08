package org.ruoxue.java_147.conversion.datetime;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

public class LocalDateTimeToDate {

	@Test
	public void toDate() {
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		LocalDate localDate = LocalDate.of(2023, 4, 4);

		Date date = Date.from(localDate.atStartOfDay(zone).toInstant());
		System.out.println(date);
		assertEquals("Mon Apr 03 21:00:00 CST 2023", date.toString());
	}

	@Test
	public void toDateWithToInstant() {
		ZoneId zone = ZoneId.of("Etc/GMT-11");
		LocalDate localDate = LocalDate.of(2023, 4, 4);

		Date date = Date.from(localDate.atStartOfDay(zone).toInstant());
		System.out.println(date);
		assertEquals("Mon Apr 03 21:00:00 CST 2023", date.toString());
	}
}
