package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.junit.Test;

public class DateTimeFormatterMethodsTest {

	@Test
	public void ofPattern() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
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
	public void format() {
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

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.TRADITIONAL_CHINESE);
		result = formatter.format(localDateTime);
		System.out.println(result);
		assertEquals("2023-03-14T09:08:07.123", result);
	}

	@Test
	public void formatLocalDate() {
		LocalDate localDate = LocalDate.parse("2023-03-14");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String result = formatter.format(localDate);
		System.out.println(result);
		assertEquals("2023-03-14", result);

		formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		result = formatter.format(localDate);
		System.out.println(result);
		assertEquals("20230314", result);

		formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.TRADITIONAL_CHINESE);
		result = formatter.format(localDate);
		System.out.println(result);
		assertEquals("2023/03/14", result);
	}

	@Test
	public void formatLocalTime() {
		LocalTime localTime = LocalTime.parse("09:08:07.123456789");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String result = formatter.format(localTime);
		System.out.println(result);
		assertEquals("09:08:07", result);

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSSSS");
		result = formatter.format(localTime);
		System.out.println(result);
		assertEquals("09:08:07.123456789", result);

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		result = formatter.format(localTime);
		System.out.println(result);
		assertEquals("09:08:07.123", result);

		formatter = DateTimeFormatter.ofPattern("HH-mm-ss.SSS", Locale.TRADITIONAL_CHINESE);
		result = formatter.format(localTime);
		System.out.println(result);
		assertEquals("09-08-07.123", result);
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
	public void toZonedDateTime() {
		// 2024-04-17T00:00:00.000Z
		LocalDateTime localDateTime = LocalDateTime.of(2024, 4, 17, 0, 0, 0, 0);
		ZoneId zone = ZoneId.of("UTC");
		ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
		System.out.println(zonedDateTime);
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT.withZone(zone);
		String result = zonedDateTime.format(formatter);
		// 2024-04-17T00:00:00Z
		System.out.println(result);
		localDateTime = localDateTime.minusNanos(1000000);
		zonedDateTime = localDateTime.atZone(zone);
		System.out.println(zonedDateTime);
		result = zonedDateTime.format(formatter);
		// 2024-04-16T23:59:59.999Z
		System.out.println(result);
	}
}
