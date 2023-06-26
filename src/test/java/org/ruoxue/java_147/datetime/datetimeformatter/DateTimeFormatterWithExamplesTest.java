package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

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
		assertEquals("{},ISO resolved to 2023-03-14T09:08:07.123456789", result.toString());

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		result = formatter.parse("2023-03-14");
		System.out.println(result);
		assertEquals("{},ISO resolved to 2023-03-14", result.toString());

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		result = formatter.parse("09:08:07.123");
		System.out.println(result);
		assertEquals("{},ISO resolved to 09:08:07.123", result.toString());
	}

	@Test
	public void parsePosition() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		String value = "LocalDateTime: 2023-03-14T09:08:07.123456789";
		TemporalAccessor result = formatter.parse(value, new ParsePosition(15));
		System.out.println(result);
		assertEquals("{},ISO resolved to 2023-03-14T09:08:07.123456789", result.toString());

		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		value = "LocalDate: 2023-03-14";
		result = formatter.parse(value, new ParsePosition(11));
		System.out.println(result);
		assertEquals("{},ISO resolved to 2023-03-14", result.toString());

		formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		value = "LocalTime: 09:08:07.123";
		result = formatter.parse(value, new ParsePosition(11));
		System.out.println(result);
		assertEquals("{},ISO resolved to 09:08:07.123", result.toString());
	}

	@Test
	public void parseBest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss[Z]]");
		String value = "2023-03-14";
		@SuppressWarnings("rawtypes")
		TemporalQuery[] tmporalQuerys = new TemporalQuery[] { OffsetDateTime::from, LocalDateTime::from,
				LocalDate::from };
		TemporalAccessor result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14", result.toString());

		value = "2023-03-14T09:08:07";
		result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14T09:08:07", result.toString());

		value = "2023-03-14T09:08:07+0000";
		result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14T09:08:07Z", result.toString());

		value = "2023-03-14T09:08:07+0200";
		result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14T09:08:07+02:00", result.toString());
	}

	@Test
	public void parseBestNano() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss[.SSSSSSSSS][Z]]");
		String value = "2023-03-14";
		@SuppressWarnings("rawtypes")
		TemporalQuery[] tmporalQuerys = new TemporalQuery[] { ZonedDateTime::from, LocalDateTime::from, LocalDate::from,
				LocalTime::from };
		TemporalAccessor result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14", result.toString());

		value = "2023-03-14T09:08:07";
		result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14T09:08:07", result.toString());

		value = "2023-03-14T09:08:07.123456789+0000";
		result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14T09:08:07.123456789Z", result.toString());

		value = "2023-03-14T09:08:07.123456789+0200";
		result = formatter.parseBest(value, tmporalQuerys);
		System.out.println(result.getClass().getSimpleName() + ": " + result);
		assertEquals("2023-03-14T09:08:07.123456789+02:00", result.toString());
	}

	@Test
	public void parseUnresolved() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		String value = "LocalDateTime: 2023-00-99T88:77:66.123456789";
		TemporalAccessor result = formatter.parseUnresolved(value, new ParsePosition(0));
		System.out.println(result);
		assertNull(result);

		value = "LocalDateTime: 2023-00-99T88:77:66.123456789";
		result = formatter.parseUnresolved(value, new ParsePosition(15));
		System.out.println(result);
		assertEquals(
				"{HourOfDay=88, NanoOfSecond=123456789, MinuteOfHour=77, MonthOfYear=0, SecondOfMinute=66, YearOfEra=2023, DayOfMonth=99},null",
				result.toString());
	}

	@Test
	public void parsedExcessDays() {

	}

	@Test
	public void parsedLeapSecond() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	}
}
