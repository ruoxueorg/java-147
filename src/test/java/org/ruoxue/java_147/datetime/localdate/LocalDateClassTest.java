package org.ruoxue.java_147.datetime.localdate;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

import org.junit.Test;

public class LocalDateClassTest {

	@Test
	public void now() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);

		ZoneId zone = ZoneId.of("America/Chicago");
		localDate = LocalDate.now(zone);
		System.out.println(localDate);

		zone = ZoneId.of("-4");
		Clock clock = Clock.system(zone);
		localDate = LocalDate.now(clock);
		System.out.println(localDate);
	}
}
