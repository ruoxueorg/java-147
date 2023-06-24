package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import org.junit.Test;

public class DateTimeFormatterWithExamplesTest {

	@Test
	public void formatTo() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		StringBuffer result = new StringBuffer("LocalDateTime: ");
		formatter.formatTo(LocalDateTime.now(), result);
		System.out.println(result);

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		result = new StringBuffer("LocalDate: ");
		formatter.formatTo(LocalDate.now(), result);
		System.out.println(result);

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		result = new StringBuffer("LocalTime: ");
		formatter.formatTo(LocalTime.now(), result);
		System.out.println(result);
	}

	@Test
	public void parse() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		TemporalAccessor result = formatter.parse("2023-03-14T09:08:07.123456789");
		System.out.println(result);
		assertEquals("2023-03-14T09:08:07.123456789", formatter.format(result));

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		result = formatter.parse("2023-03-14");
		System.out.println(result);
		assertEquals("2023-03-14", formatter.format(result));

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		result = formatter.parse("09:08:07.123");
		System.out.println(result);
		assertEquals("09:08:07.123", formatter.format(result));
	}

	@Test
	public void parsePosition() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		TemporalAccessor result = formatter.parse("LocalDateTime: 2023-03-14T09:08:07.123456789",
				new ParsePosition(15));
		System.out.println(result);
		assertEquals("2023-03-14T09:08:07.123456789", formatter.format(result));

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		result = formatter.parse("LocalDate: 2023-03-14", new ParsePosition(11));
		System.out.println(result);
		assertEquals("2023-03-14", formatter.format(result));

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		result = formatter.parse("LocalTime: 09:08:07.123", new ParsePosition(11));
		System.out.println(result);
		assertEquals("09:08:07.123", formatter.format(result));
	}

	@Test
	public void parseBest() {

	}

	@Test
	public void parseUnresolved() {

	}

	@Test
	public void parsedExcessDays() {

	}

	@Test
	public void parsedLeapSecond() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	}

	@Test
	public void with() {

	}

	@Test
	public void toFormat() {

	}

}
