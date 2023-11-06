package org.ruoxue.java_147.net.url;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.junit.Test;

public class URLClassTest {
	
	@Test
	public void toURI() {
		try {
			String value = "https://www.ruoxue.org";
			URL url = new URL(value);
			System.out.println(url);
			URI uri = url.toURI();
			System.out.println(uri);
			assertThat(uri.toString()).isEqualTo(url.toString());

			value = "mailto:ruoxueorg@gmail.com";
			url = new URL(value);
			System.out.println(url);
			uri = url.toURI();
			System.out.println(uri);
			assertThat(uri.toString()).isEqualTo(url.toString());
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@Test
	public void sameFile() {
		try {
			URL url = new URL("https://www.ruoxue.org/#top");
			System.out.println(url);
			URL url2 = new URL("https://WWW.RUOXUE.ORG/#bottom");
			System.out.println(url2);
			boolean result = url.sameFile(url2);
			System.out.println(result);
			assertThat(result).isTrue();

			url = new URL("https://www.ruoxue.org?s=a#bottom");
			System.out.println(url);
			url2 = new URL("https://www.ruoxue.org?s=%61#top");
			System.out.println(url2);
			result = url.sameFile(url2);
			System.out.println(result);
			assertThat(result).isFalse();
		} catch (MalformedURLException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@Test
	public void getContent() {
		try {
			URL url = new URL("https://www.ruoxue.org");
			System.out.println(url.getContent());
			StringBuilder result = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) url.getContent()))) {
				String value;
				while ((value = in.readLine()) != null) {
					result.append(value);
				}
				System.out.println(result.length());
				assertThat(result).hasSizeGreaterThan(0);
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}
