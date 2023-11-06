package org.ruoxue.java_147.net.uri;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.junit.Test;

public class URIClassTest {

	@Test
	public void toURL() {
		try {
			URI uri = URI.create("https://www.ruoxue.org");
			System.out.println(uri);
			URL url = uri.toURL();
			System.out.println(url);
			assertThat(url.toString()).isEqualTo(uri.toString());
			
			uri = URI.create("mailto:ruoxueorg@gmail.com");
			System.out.println(uri);
			url = uri.toURL();
			System.out.println(url);
			assertThat(url.toString()).isEqualTo(uri.toString());
		} catch (MalformedURLException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@Test
	public void toURLThrowException() {
		assertThatCode(() -> {
			URI uri = URI.create("urn:isbn:1234567890+%25");
			System.out.println(uri);
			URL url = uri.toURL();
			System.out.println(url);
		}).isInstanceOf(MalformedURLException.class);
	}

	@Test
	public void compareTo() {
		URI uri = URI.create("https://www.ruoxue.org");
		System.out.println(uri);
		URI uri2 = URI.create("https://WWW.RUOXUE.ORG");
		System.out.println(uri2);
		int result = uri.compareTo(uri2);
		System.out.println(result);
		assertThat(result).isZero();

		uri = URI.create("https://www.ruoxue.org?s=a");
		System.out.println(uri);
		uri2 = URI.create("https://www.ruoxue.org?s=%61");
		System.out.println(uri2);
		result = uri.compareTo(uri2);
		System.out.println(result);
		assertThat(result).isGreaterThan(0);
	}

	@Test
	public void isAbsolute() {
		URI uri = URI.create("https://www.ruoxue.org");
		System.out.println(uri);
		boolean result = uri.isAbsolute();
		System.out.println(result + ", getScheme: " + uri.getScheme());
		assertThat(result).isTrue();

		uri = URI.create("java-net");
		System.out.println(uri);
		result = uri.isAbsolute();
		System.out.println(result + ", getScheme: " + uri.getScheme());
		assertThat(result).isFalse();
	}

	@Test
	public void isOpaque() {
		URI uri = URI.create("file:localhost/README.md");
		System.out.println(uri);
		boolean result = uri.isOpaque();
		System.out.println(result + ", getPath: " + uri.getPath());
		assertThat(result).isTrue();

		uri = URI.create("file:///README.md"); // equals to: uri = URI.create("file://localhost/README.md");
		System.out.println(uri);
		result = uri.isOpaque();
		System.out.println(result + ", getPath: " + uri.getPath());
		assertThat(result).isFalse();
	}
}
