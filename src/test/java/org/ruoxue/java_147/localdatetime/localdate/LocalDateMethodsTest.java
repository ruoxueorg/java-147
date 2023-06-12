package org.ruoxue.java_147.localdatetime.localdate;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.zone.ZoneRulesProvider;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LocalDateMethodsTest {

	@Test
	public void getAvailableZoneIds() {
		List<String> list = new ArrayList<>(ZoneRulesProvider.getAvailableZoneIds());
		System.out.println(list);
	}

	@Test
	public void now() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		ZoneId zone=ZoneId.of("America/Chicago");
		localDate = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		System.out.println(localDate);
		localDate = LocalDate.now(ZoneId.of("America/Chicago"));
		System.out.println(localDate);
	}
	
	@Test
	public void of() {
		
	}

	@Test
	public void get() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.getYear());
		System.out.println(localDate.getMonth());
		System.out.println(localDate.getMonthValue());
		System.out.println(localDate.getDayOfMonth());
		System.out.println(localDate.getDayOfWeek());
		System.out.println(localDate.getDayOfYear());
	}
}
