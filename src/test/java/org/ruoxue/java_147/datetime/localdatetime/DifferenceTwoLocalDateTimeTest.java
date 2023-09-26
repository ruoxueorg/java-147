package org.ruoxue.java_147.datetime.localdatetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class DifferenceTwoLocalDateTimeTest {

	@Test
	public void duration() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		LocalDateTime localDateTime2 = LocalDateTime.of(2024, 8, 3, 1, 2, 3, 123456789);
		Duration duration = Duration.between(localDateTime, localDateTime2);
		System.out.println(duration);

		long days = duration.toDays();
		System.out.println(days);
		assertThat(days).isEqualTo(366);

		long hours = duration.toHours();
		System.out.println(hours);
		assertThat(hours).isEqualTo(8784);

		long minutes = duration.toMinutes();
		System.out.println(minutes);
		assertThat(minutes).isEqualTo(527040);

		long seconds = duration.getSeconds();
		System.out.println(seconds);
		assertThat(seconds).isEqualTo(31622400);

		long nanos = duration.toNanos();
		System.out.println(nanos);
		assertThat(nanos).isEqualTo(31622400000000000L);
	}

	@Test
	public void chronoUnit() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		LocalDateTime localDateTime2 = LocalDateTime.of(2024, 8, 3, 1, 2, 3, 123456789);

		long years = ChronoUnit.YEARS.between(localDateTime, localDateTime2);
		System.out.println(years);
		assertThat(years).isEqualTo(1);

		long months = ChronoUnit.MONTHS.between(localDateTime, localDateTime2);
		System.out.println(months);
		assertThat(months).isEqualTo(12);

		long weeks = ChronoUnit.WEEKS.between(localDateTime, localDateTime2);
		System.out.println(weeks);
		assertThat(weeks).isEqualTo(52);

		long days = ChronoUnit.DAYS.between(localDateTime, localDateTime2);
		System.out.println(days);
		assertThat(days).isEqualTo(366);

		long hours = ChronoUnit.HOURS.between(localDateTime, localDateTime2);
		System.out.println(hours);
		assertThat(hours).isEqualTo(8784);

		long minutes = ChronoUnit.MINUTES.between(localDateTime, localDateTime2);
		System.out.println(minutes);
		assertThat(minutes).isEqualTo(527040);

		long seconds = ChronoUnit.SECONDS.between(localDateTime, localDateTime2);
		System.out.println(seconds);
		assertThat(seconds).isEqualTo(31622400);

		long milliseconds = ChronoUnit.MILLIS.between(localDateTime, localDateTime2);
		System.out.println(milliseconds);
		assertThat(milliseconds).isEqualTo(31622400000L);

		long nano = ChronoUnit.NANOS.between(localDateTime, localDateTime2);
		System.out.println(nano);
		assertThat(nano).isEqualTo(31622400000000000L);
	}

	@Test
	public void until() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		LocalDateTime localDateTime2 = LocalDateTime.of(2024, 8, 3, 1, 2, 3, 987654321);

		long years = localDateTime.until(localDateTime2, ChronoUnit.YEARS);
		System.out.println(years);
		assertThat(years).isEqualTo(1);

		long months = localDateTime.until(localDateTime2, ChronoUnit.MONTHS);
		System.out.println(months);
		assertThat(months).isEqualTo(12);

		long weeks = localDateTime.until(localDateTime2, ChronoUnit.WEEKS);
		System.out.println(weeks);
		assertThat(weeks).isEqualTo(52);

		long days = localDateTime.until(localDateTime2, ChronoUnit.DAYS);
		System.out.println(days);
		assertThat(days).isEqualTo(366);

		long hours = localDateTime.until(localDateTime2, ChronoUnit.HOURS);
		System.out.println(hours);
		assertThat(hours).isEqualTo(8784);

		long minutes = localDateTime.until(localDateTime2, ChronoUnit.MINUTES);
		System.out.println(minutes);
		assertThat(minutes).isEqualTo(527040);

		long seconds = localDateTime.until(localDateTime2, ChronoUnit.SECONDS);
		System.out.println(seconds);
		assertThat(seconds).isEqualTo(31622400);

		long milliseconds = localDateTime.until(localDateTime2, ChronoUnit.MILLIS);
		System.out.println(milliseconds);
		assertThat(milliseconds).isEqualTo(31622400864L);

		long nano = localDateTime.until(localDateTime2, ChronoUnit.NANOS);
		System.out.println(nano);
		assertThat(nano).isEqualTo(31622400864197532L);
	}
}
