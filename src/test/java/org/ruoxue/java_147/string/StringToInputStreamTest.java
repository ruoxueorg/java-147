package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.common.io.CharSource;

public class StringToInputStreamTest {

	@Test
	public void byteArrayInputStream() {
		String value = "java147,springboot168,junit151,bash460,it484";
		InputStream is = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
		System.out.println(is);

		StringBuilder builder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				builder.append(str);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		String result = builder.toString();
		System.out.println(result);
		assertEquals(value, result);
	}

	@Test
	public void commons_io_IOUtils() {
		String value = "java147,springboot168,junit151,bash460,it484";
		InputStream is = IOUtils.toInputStream(value, StandardCharsets.UTF_8);
		System.out.println(is);

		String result = null;
		try {
			result = IOUtils.toString(is, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(result);
		assertEquals(value, result);
	}

	@Test
	public void guava_CharSource() {
		String value = "java147,springboot168,junit151,bash460,it484";
		InputStream is = null;
		try {
			is = CharSource.wrap(value).asByteSource(StandardCharsets.UTF_8).openStream();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(is);

		StringBuilder builder = new StringBuilder();
		try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.toString())) {
			while (scanner.hasNext()) {
				builder.append(scanner.nextLine());
			}
		}
		String result = builder.toString();
		System.out.println(result);
		assertEquals(value, result);
	}
}
