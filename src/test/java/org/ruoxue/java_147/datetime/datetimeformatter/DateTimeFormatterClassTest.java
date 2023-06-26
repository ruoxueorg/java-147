package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.text.Format;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateTimeFormatterClassTest {

	@Test
	public void toFormat() {
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-03-14T09:08:07.123456789+08:00[Asia/Singapore]");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
		Format format = formatter.toFormat();
		String result = format.format(zonedDateTime);
		System.out.println(result);
		assertEquals("2023-03-14T09:08:07.123456789+08:00[Asia/Singapore]", result);

		formatter = DateTimeFormatter.ISO_INSTANT;
		format = formatter.toFormat();
		result = format.format(zonedDateTime);
		System.out.println(result);
		assertEquals("2023-03-14T01:08:07.123456789Z", result);

		formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		format = formatter.toFormat();
		result = format.format(zonedDateTime);
		System.out.println(result);
		assertEquals("Tue, 14 Mar 2023 09:08:07 +0800", result);
	}

	@Test
	public void get() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		System.out.println(formatter.getChronology());
		System.out.println(formatter.getDecimalStyle());
		System.out.println(formatter.getLocale());
		System.out.println(formatter.getResolverFields());
		System.out.println(formatter.getResolverStyle());
		System.out.println(formatter.getZone());
	}

	@Test
	public void with() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	}

}
