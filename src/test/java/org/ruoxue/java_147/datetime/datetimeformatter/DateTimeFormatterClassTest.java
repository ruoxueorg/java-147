package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.text.Format;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.ResolverStyle;
import java.util.Locale;

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
		formatter.withChronology(JapaneseChronology.INSTANCE);
		formatter.withDecimalStyle(DecimalStyle.STANDARD);
		formatter.withLocale(Locale.TRADITIONAL_CHINESE);
		formatter.withResolverStyle(ResolverStyle.LENIENT);
		formatter.withZone(ZoneId.of("UTC+8"));

		LocalDateTime localDateTime = LocalDateTime.now();
		String result = formatter.format(localDateTime);
		System.out.println(result);
	}
}
