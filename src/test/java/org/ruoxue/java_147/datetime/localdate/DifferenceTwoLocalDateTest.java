package org.ruoxue.java_147.datetime.localdate;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class DifferenceTwoLocalDateTest {

	@Test
	public void period() {
		LocalDate localDate = LocalDate.of(2023, 8, 3);
		LocalDate localDate2 = LocalDate.of(2024, 8, 3);
		Period period = Period.between(localDate, localDate2);
		System.out.println(period);

		long years = period.getYears();
		System.out.println(years);
		assertThat(years).isEqualTo(1);

		long months = period.getMonths();
		System.out.println(months);
		assertThat(months).isEqualTo(0);

		long days = period.getDays();
		System.out.println(days);
		assertThat(days).isEqualTo(0);
	}

	@Test
	public void duration() {
		LocalDate localDate = LocalDate.of(2023, 8, 3);
		LocalDate localDate2 = LocalDate.of(2024, 8, 3);
		Duration duration = Duration.between(localDate.atStartOfDay(), localDate2.atStartOfDay());
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
		LocalDate localDate = LocalDate.of(2023, 8, 3);
		LocalDate localDate2 = LocalDate.of(2024, 8, 3);

		long years = ChronoUnit.YEARS.between(localDate, localDate2);
		System.out.println(years);
		assertThat(years).isEqualTo(1);

		long months = ChronoUnit.MONTHS.between(localDate, localDate2);
		System.out.println(months);
		assertThat(months).isEqualTo(12);

		long weeks = ChronoUnit.WEEKS.between(localDate, localDate2);
		System.out.println(weeks);
		assertThat(weeks).isEqualTo(52);

		long days = ChronoUnit.DAYS.between(localDate, localDate2);
		System.out.println(days);
		assertThat(days).isEqualTo(366);
	}

	@Test
	public void until() {
		LocalDate localDate = LocalDate.of(2023, 8, 3);
		LocalDate localDate2 = LocalDate.of(2024, 8, 3);

		long years = localDate.until(localDate2, ChronoUnit.YEARS);
		System.out.println(years);
		assertThat(years).isEqualTo(1);

		long months = localDate.until(localDate2, ChronoUnit.MONTHS);
		System.out.println(months);
		assertThat(months).isEqualTo(12);

		long weeks = localDate.until(localDate2, ChronoUnit.WEEKS);
		System.out.println(weeks);
		assertThat(weeks).isEqualTo(52);

		long days = localDate.until(localDate2, ChronoUnit.DAYS);
		System.out.println(days);
		assertThat(days).isEqualTo(366);
	}
}
