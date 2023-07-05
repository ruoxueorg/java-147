package org.ruoxue.java_147.datetime.zoneddatetime;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;

public class ZonedDateTimeWithTest {

	@Test
	public void withZoneSameInstant() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0);
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
		System.out.println(zonedDateTime);

		zone = ZoneId.of("Asia/Shanghai");
		zonedDateTime = zonedDateTime.withZoneSameInstant(zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T12:08:09+08:00[Asia/Shanghai]", zonedDateTime.toString());

		zone = ZoneId.of("Z");
		zonedDateTime = zonedDateTime.withZoneSameInstant(zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T04:08:09Z", zonedDateTime.toString());
	}

	@Test
	public void withZoneSameLocal() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0);
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
		System.out.println(zonedDateTime);

		zone = ZoneId.of("Asia/Shanghai");
		zonedDateTime = zonedDateTime.withZoneSameLocal(zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+08:00[Asia/Shanghai]", zonedDateTime.toString());

		zone = ZoneId.of("Z");
		zonedDateTime = zonedDateTime.withZoneSameLocal(zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09Z", zonedDateTime.toString());
	}

	@Test
	public void withEarlierOffsetAtOverlap() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 5, 1, 30, 0);
		ZoneId zone = ZoneId.of("US/Central");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofLocal(localDateTime, zone, ZoneOffset.ofHours(-6));
		System.out.println(zonedDateTime);
		assertEquals("2023-11-05T01:30-06:00[US/Central]", zonedDateTime.toString());

		zonedDateTime = zonedDateTime.withEarlierOffsetAtOverlap();
		System.out.println(zonedDateTime);
		assertEquals("2023-11-05T01:30-05:00[US/Central]", zonedDateTime.toString());
	}

	@Test
	public void withLaterOffsetAtOverlap() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 5, 1, 30, 0);
		ZoneId zone = ZoneId.of("US/Central");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofLocal(localDateTime, zone, ZoneOffset.ofHours(-6));
		System.out.println(zonedDateTime);
		assertEquals("2023-11-05T01:30-06:00[US/Central]", zonedDateTime.toString());

		zonedDateTime = zonedDateTime.withLaterOffsetAtOverlap();
		System.out.println(zonedDateTime);
		assertEquals("2023-11-05T01:30-06:00[US/Central]", zonedDateTime.toString());
	}

	@Test
	public void withFixedOffsetZone() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 5, 1, 30, 0);
		ZoneId zone = ZoneId.of("US/Central");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofLocal(localDateTime, zone, ZoneOffset.ofHours(-6));
		System.out.println(zonedDateTime);
		System.out.println(zonedDateTime.getZone());
		System.out.println(zonedDateTime.getOffset());
		assertEquals("US/Central", zonedDateTime.getZone().toString());
		
		zonedDateTime = zonedDateTime.withFixedOffsetZone();
		System.out.println(zonedDateTime);
		System.out.println(zonedDateTime.getZone());
		System.out.println(zonedDateTime.getOffset());
		assertEquals("-06:00", zonedDateTime.getZone().toString());
	}
}
