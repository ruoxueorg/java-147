package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Test;

public class DateTimeFormatterMethodsTest {

	@Test
	public void ofPattern() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-03-14T09:08:07.123456789");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String result = formatter.format(localDateTime);
		System.out.println(result);

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		result = formatter.format(localDateTime);
		System.out.println(result);
		

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.TRADITIONAL_CHINESE);
		result = formatter.format(localDateTime);
		System.out.println(result);
	}

	@Test
	public void withZone() {

	}
}
