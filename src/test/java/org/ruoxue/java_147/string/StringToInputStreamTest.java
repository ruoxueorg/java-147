package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class StringToInputStreamTest {

	@Test
	public void byteArrayInputStream() {
		String value = "java147,springboot168,junit151,bash460,it484";
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
			System.out.println(is);

			StringBuilder builder = new StringBuilder();
			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(is, StandardCharsets.UTF_8))) {
				String buff = null;
				while ((buff = bufferedReader.readLine()) != null) {
					builder.append(buff);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			String result = builder.toString();
			System.out.println(result);
			assertEquals(value, result);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	@Test
	public void byteArrayInputStreamTryWithResources() {
		String value = "java147,springboot168,junit151,bash460,it484";
		try (InputStream is = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8))) {
			System.out.println(is);

			StringBuilder builder = new StringBuilder();
			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(is, StandardCharsets.UTF_8))) {
				String buff = null;
				while ((buff = bufferedReader.readLine()) != null) {
					builder.append(buff);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			String result = builder.toString();
			System.out.println(result);
			assertEquals(value, result);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Test(expected = NullPointerException.class)
	public void byteArrayInputStreamThrowException() {
		String value = "java147,springboot168,junit151,bash460,it484";
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(value.getBytes((Charset) null));
			System.out.println(is);

			StringBuilder builder = new StringBuilder();
			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(is, StandardCharsets.UTF_8))) {
				String buff = bufferedReader.readLine();
				while (buff != null) {
					builder.append(buff);
					buff = bufferedReader.readLine();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			String result = builder.toString();
			System.out.println(result);
			assertEquals(value, result);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}
	}
}
