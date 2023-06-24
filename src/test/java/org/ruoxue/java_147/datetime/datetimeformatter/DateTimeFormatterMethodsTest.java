package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateTimeFormatterMethodsTest {

	@Test
	public void ofPattern() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-03-14T09:08:07.123456789");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("2023-03-14T09:08:07", result);

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("2023-03-14T09:08:07.123456789", result);

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("2023-03-14T09:08:07.123", result);
	}

	@Test
	public void inbuilt() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-03-14T09:08:07.123456789");
		OffsetDateTime offsetDateTime = OffsetDateTime.parse("2023-03-14T09:08:07.123456789+08:00");
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-03-14T09:08:07.123456789+08:00[Asia/Taipei]");

		String result = DateTimeFormatter.BASIC_ISO_DATE.format(localDateTime);
		System.out.println("BASIC_ISO_DATE: " + result);
		assertEquals("20230314", result);

		result = DateTimeFormatter.ISO_LOCAL_DATE.format(localDateTime);
		System.out.println("ISO_LOCAL_DATE: " + result);
		assertEquals("2023-03-14", result);

		result = DateTimeFormatter.ISO_OFFSET_DATE.format(offsetDateTime);
		System.out.println("ISO_OFFSET_DATE: " + result);
		assertEquals("2023-03-14+08:00", result);

		result = DateTimeFormatter.ISO_DATE.format(localDateTime);
		System.out.println("ISO_DATE: " + result);
		assertEquals("2023-03-14", result);

		result = DateTimeFormatter.ISO_LOCAL_TIME.format(localDateTime);
		System.out.println("ISO_LOCAL_DATE: " + result);
		assertEquals("09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_OFFSET_TIME.format(offsetDateTime);
		System.out.println("ISO_OFFSET_DATE: " + result);
		assertEquals("09:08:07.123456789+08:00", result);

		result = DateTimeFormatter.ISO_TIME.format(localDateTime);
		System.out.println("ISO_DATE: " + result);
		assertEquals("09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
		System.out.println("ISO_LOCAL_DATE: " + result);
		assertEquals("2023-03-14T09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime);
		System.out.println("ISO_OFFSET_DATE: " + result);
		assertEquals("2023-03-14T09:08:07.123456789+08:00", result);

		result = DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
		System.out.println("ISO_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
		System.out.println("ISO_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789+08:00[Asia/Taipei]", result);
		
		result = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime);
		System.out.println("ISO_ZONED_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789+08:00[Asia/Taipei]", result);
		
		result = DateTimeFormatter.ISO_ORDINAL_DATE.format(zonedDateTime);
		System.out.println("ISO_ORDINAL_DATE: " + result);
		assertEquals("2023-073+08:00", result);
		
		result = DateTimeFormatter.ISO_WEEK_DATE.format(zonedDateTime);
		System.out.println("ISO_WEEK_DATE: " + result);
		assertEquals("2023-W11-2+08:00", result);
		
		result = DateTimeFormatter.ISO_INSTANT.format(zonedDateTime);
		System.out.println("ISO_INSTANT: " + result);
		assertEquals("2023-03-14T01:08:07.123456789Z", result);
		
		result = DateTimeFormatter.RFC_1123_DATE_TIME.format(zonedDateTime);
		System.out.println("RFC_1123_DATE_TIME: " + result);
		assertEquals("Tue, 14 Mar 2023 09:08:07 +0800", result);
	}
}
