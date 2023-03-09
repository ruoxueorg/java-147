package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class StringToFloatWithExamplesTest {

	@Test
	public void decimalFormat() {
		try {
			String value = "151.88";
			Number number = DecimalFormat.getNumberInstance().parse(value);
			float result = number.floatValue();
			System.out.println(result);
			assertEquals(151.88f, result, 0);

			value = "-40.88";
			number = DecimalFormat.getNumberInstance().parse(value);
			result = number.floatValue();
			System.out.println(result);
			assertEquals(-40.88f, result, 0);

			value = "-$1,240.88";
			DecimalFormat format = new DecimalFormat("$#,##0.00");
			format.setParseBigDecimal(true);

			result = format.parse(value).floatValue();
			System.out.println(result);
			assertEquals(-1240.88f, result, 0);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void decimalFormatThrowException() {
		try {
			String value = "151.88";
			Number number = DecimalFormat.getNumberInstance().parse(value);
			float result = number.floatValue();
			System.out.println(result);
			assertEquals(151.88f, result, 0);

			value = "bash-151.88";
			number = DecimalFormat.getNumberInstance().parse(value);
			result = number.floatValue();
			System.out.println(result);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void constructor() {
		String value = "151.88";
		float result = new Float(value);
		System.out.println(result);
		assertEquals(151.88f, result, 0);

		value = "-40.88";
		result = new Float(value);
		System.out.println(result);
		assertEquals(-40.88f, result, 0);
	}

	@Test(expected = NumberFormatException.class)
	public void constructorThrowException() {
		String value = "151.88";
		float result = new Float(value);
		System.out.println(result);
		assertEquals(151.88f, result, 0);

		value = "junit-151.88";
		result = new Float(value);
		System.out.println(result);
	}

	@Test
	public void NumberUtils_toFloat() {
		String value = "151.88";
		float result = NumberUtils.toFloat(value);
		System.out.println(result);
		assertEquals(151.88f, result, 0);

		value = "-40.88";
		result = NumberUtils.toFloat(value);
		System.out.println(result);
		assertEquals(-40.88f, result, 0);

		value = "40.88";
		result = NumberUtils.toFloat(value, 0);
		System.out.println(result);
		assertEquals(40.88f, result, 0);

		value = "junit-151.88";
		result = NumberUtils.toFloat(value, 0);
		System.out.println(result);
		assertEquals(0f, result, 0);
	}
}
