package org.ruoxue.java_147.datetime.localtime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeLocalTimeTest {

	@Test
	public void now() {
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);

		ZoneId zone = ZoneId.of("Europe/London");
		localTime = LocalTime.now(zone);
		System.out.println(localTime);

		zone = ZoneId.of("UTC+1");
		Clock clock = Clock.system(zone);
		localTime = LocalTime.now(clock);
		System.out.println(localTime);
	}

	@Test
	public void of() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

		int secondOfDay = localTime.toSecondOfDay();
		System.out.println(secondOfDay);
		assertEquals(33125L, secondOfDay);

		localTime = LocalTime.of(9, 12, 5, 999);
		System.out.println(localTime);
		assertEquals("09:12:05.000000999", localTime.toString());

		localTime = LocalTime.ofSecondOfDay(33127L);
		System.out.println(localTime);
		assertEquals("09:12:07", localTime.toString());
	}

	@Test
	public void parse() {
		LocalTime localTime = LocalTime.parse("09:12:05");
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());

		localTime = LocalTime.parse("091205", DateTimeFormatter.ofPattern("HHmmss"));
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());
	}

	@Test
	public void from() {
		LocalTime localTime = LocalTime.parse("09:12:05");
		LocalTime result = LocalTime.from(localTime);
		System.out.println(result);
		assertEquals("09:12:05", result.toString());
	}

	@Test
	public void ofSecondOfDay() {
		LocalTime localTime = LocalTime.ofSecondOfDay(33125L);
		System.out.println(localTime);
		assertEquals("09:12:05", localTime.toString());
	}
}
