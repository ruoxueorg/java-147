package org.ruoxue.java_147.conversion.string;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;

public class StringToInputStreamWithExamplesTest {

	@Test
	public void IOUtils_toInputStream() {
		String value = "java147,springboot168,junit151,bash460,it484";
		InputStream is = null;
		try {
			is = IOUtils.toInputStream(value, StandardCharsets.UTF_8);
			System.out.println(is);

			String result = null;
			try {
				result = IOUtils.toString(is, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println(result);
			assertEquals(value, result);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	@Test(expected = NullPointerException.class)
	public void IOUtils_toInputStream_ThrowException() {
		String value = null;
		InputStream is = null;
		try {
			is = IOUtils.toInputStream(value, StandardCharsets.UTF_8);
			System.out.println(is);

			String result = null;
			try {
				result = IOUtils.toString(is, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println(result);
			assertEquals(value, result);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	@Test
	public void CharSource_openStream() {
		String value = "java147,springboot168,junit151,bash460,it484";
		InputStream is = null;
		try {
			is = CharSource.wrap(value).asByteSource(StandardCharsets.UTF_8).openStream();
			System.out.println(is);

			String result = CharStreams.toString(new InputStreamReader(is, StandardCharsets.UTF_8));
			System.out.println(result);
			assertEquals(value, result);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	@Test(expected = NullPointerException.class)
	public void CharSource_openStream_ThrowException() {
		String value = null;
		InputStream is = null;
		try {
			is = CharSource.wrap(value).asByteSource(StandardCharsets.UTF_8).openStream();
			System.out.println(is);

			String result = CharStreams.toString(new InputStreamReader(is, StandardCharsets.UTF_8));
			System.out.println(result);
			assertEquals(value, result);
		} catch (IOException ex) {
			ex.printStackTrace();
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
	public void CharSource_openStream_TryWithResources() {
		String value = "java147,springboot168,junit151,bash460,it484";
		try (InputStream is = CharSource.wrap(value).asByteSource(StandardCharsets.UTF_8).openStream()) {
			System.out.println(is);

			String result = CharStreams.toString(new InputStreamReader(is, StandardCharsets.UTF_8));
			System.out.println(result);
			assertEquals(value, result);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
