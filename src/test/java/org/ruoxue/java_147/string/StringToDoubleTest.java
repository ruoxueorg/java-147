package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class StringToDoubleTest {

	@Test
	public void parseDouble() {
		String value = "460.88";
		double result = Double.parseDouble(value);
		System.out.println(result);
		assertEquals(460.88, result, 0);

		value = "-50.88";
		result = Double.parseDouble(value);
		System.out.println(result);
		assertEquals(-50.88, result, 0);
	}

	@Test(expected = NumberFormatException.class)
	public void parseDoubleThrowException() {
		String value = "460.88";
		double result = Double.parseDouble(value);
		System.out.println(result);
		assertEquals(460.88, result, 0);

		value = "bash-460";
		result = Double.parseDouble(value);
		System.out.println(result);
	}

	@Test
	public void valueOf() {
		String value = "460.88";
		Double result = Double.valueOf(value);
		System.out.println(result);
		assertEquals(460.88, result.doubleValue(), 0);

		value = "-50.88";
		result = Double.valueOf(value);
		System.out.println(result);
		assertEquals(-50.88, result.doubleValue(), 0);
	}

	@Test(expected = NumberFormatException.class)
	public void valueOfThrowException() {
		String value = "460.88";
		Double result = Double.valueOf(value);
		System.out.println(result);
		assertEquals(460.88, result.doubleValue(), 0);

		value = "bash-460";
		result = Double.valueOf(value);
		System.out.println(result);
	}

	@Test
	public void decimalFormat() {
		try {
			String value = "460.88";
			Number number = DecimalFormat.getNumberInstance().parse(value);
			double result = number.doubleValue();
			System.out.println(result);
			assertEquals(460.88, result, 0);

			value = "-50.88";
			result = DecimalFormat.getNumberInstance().parse(value).doubleValue();
			System.out.println(result);
			assertEquals(-50.88, result, 0);
			
			value = "-$1,250.88";
			DecimalFormat format = new DecimalFormat("$#,##0.00");
			format.setParseBigDecimal(true);
			
			result = format.parse(value).doubleValue();
			System.out.println(result);
			assertEquals(-1250.88, result, 0);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void constructor() {
		String value = "460.88";
		double result = new Double(value);
		System.out.println(result);
		assertEquals(460.88, result, 0);

		value = "-50.88";
		result = new Double(value);
		System.out.println(result);
		assertEquals(-50.88, result, 0);
	}

	@Test
	public void commons_lang3_NumberUtils() {
		String value = "460.88";
		double result = NumberUtils.toDouble(value);
		System.out.println(result);
		assertEquals(460.88, result, 0);

		value = "-50.88";
		result = NumberUtils.toDouble(value);
		System.out.println(result);
		assertEquals(-50.88, result, 0);

		value = "50.88";
		result = NumberUtils.toDouble(value, 0);
		System.out.println(result);
		assertEquals(50.88, result, 0);

		value = "bash-460";
		result = NumberUtils.toDouble(value, 0);
		System.out.println(result);
		assertEquals(0, result, 0);
	}
}
