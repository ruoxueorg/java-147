package org.ruoxue.java_147.datetime.localdate;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class CompareLocalDatesTest {

	@Test
	public void compareTo() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate localDate2 = LocalDate.of(2024, 6, 18);
		int result = localDate.compareTo(localDate2);
		System.out.println(result);
		assertThat(result).isLessThan(0);

		localDate = LocalDate.of(2023, 6, 18);
		localDate2 = LocalDate.of(2023, 6, 18);
		result = localDate.compareTo(localDate2);
		System.out.println(result);
		assertThat(result).isEqualTo(0);

		localDate = LocalDate.of(2024, 6, 18);
		localDate2 = LocalDate.of(2023, 6, 18);
		result = localDate.compareTo(localDate2);
		System.out.println(result);
		assertThat(result).isGreaterThan(0);
	}

	@Test
	public void isBefore() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate localDate2 = LocalDate.of(2024, 6, 18);
		boolean result = localDate.isBefore(localDate2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localDate2.isBefore(localDate);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void isAfter() {
		LocalDate localDate = LocalDate.of(2023, 6, 1);
		LocalDate localDate2 = LocalDate.of(2022, 6, 18);
		boolean result = localDate.isAfter(localDate2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localDate2.isAfter(localDate);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void equals() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate localDate2 = LocalDate.of(2023, 6, 18);
		boolean result = localDate.equals(localDate2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localDate2.equals(localDate);
		System.out.println(result);
		assertThat(result).isTrue();
	}
}
