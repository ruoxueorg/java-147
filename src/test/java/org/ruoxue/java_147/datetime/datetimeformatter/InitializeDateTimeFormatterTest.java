package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.Test;

public class InitializeDateTimeFormatterTest {

	@Test
	public void ofPattern() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String result = formatter.format(LocalDateTime.now());
		System.out.println(result);

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		result = formatter.format(LocalDate.now());
		System.out.println(result);

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		result = formatter.format(LocalTime.now());
		System.out.println(result);
	}

	@Test
	public void ofLocalizedDate() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-03-14T09:08:07.123456789");
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		String result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("2023年3月14日 星期二", result);

		formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG);
		result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("上午09時08分07秒", result);

		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("2023年3月14日 上午09時08分07秒", result);

		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
		result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("2023/3/14 上午 9:08", result);
	}

	@Test
	public void predefined() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-03-14T09:08:07.123456789");
		OffsetDateTime offsetDateTime = OffsetDateTime.parse("2023-03-14T09:08:07.123456789+08:00");
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-03-14T09:08:07.123456789+08:00[Asia/Singapore]");

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
		System.out.println("ISO_LOCAL_TIME: " + result);
		assertEquals("09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_OFFSET_TIME.format(offsetDateTime);
		System.out.println("ISO_OFFSET_TIME: " + result);
		assertEquals("09:08:07.123456789+08:00", result);

		result = DateTimeFormatter.ISO_TIME.format(localDateTime);
		System.out.println("ISO_TIME: " + result);
		assertEquals("09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
		System.out.println("ISO_LOCAL_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime);
		System.out.println("ISO_OFFSET_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789+08:00", result);

		result = DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
		System.out.println("ISO_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789", result);

		result = DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
		System.out.println("ISO_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789+08:00[Asia/Singapore]", result);

		result = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime);
		System.out.println("ISO_ZONED_DATE_TIME: " + result);
		assertEquals("2023-03-14T09:08:07.123456789+08:00[Asia/Singapore]", result);

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
