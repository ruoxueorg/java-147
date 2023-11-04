package org.ruoxue.java_147.net.uri;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

public class URIMethodsTest {

	@Test
	public void constructor() {
		try {
			String value = "https://username:password@www.ruoxue.org:443/java-learn/java-net?amount=101#top";
			URI uri = new URI(value);
			System.out.println(uri);
			System.out.println("getScheme: " + uri.getScheme());
			System.out.println("getAuthority: " + uri.getAuthority());
			System.out.println("getUserInfo: " + uri.getUserInfo());
			System.out.println("getHost: " + uri.getHost());
			System.out.println("getPort: " + uri.getPort());
			System.out.println("getPath: " + uri.getPath());
			System.out.println("getQuery: " + uri.getQuery());
			System.out.println("getFragment: " + uri.getFragment());

			URI uri2 = new URI("https", "username:password", "www.ruoxue.org", 443, "/java-learn/java-net",
					"amount=101", "top");
			assertThat(uri.getScheme()).isEqualTo(uri2.getScheme());
			assertThat(uri.getAuthority()).isEqualTo(uri2.getAuthority());
			assertThat(uri.getUserInfo()).isEqualTo(uri2.getUserInfo());
			assertThat(uri.getHost()).isEqualTo(uri2.getHost());
			assertThat(uri.getPort()).isEqualTo(uri2.getPort());
			assertThat(uri.getPath()).isEqualTo(uri2.getPath());
			assertThat(uri.getQuery()).isEqualTo(uri2.getQuery());
			assertThat(uri.getFragment()).isEqualTo(uri2.getFragment());
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@Test
	public void constructorThrowException() {
		assertThatCode(() -> {
			String value = "https://www.ruoxue.org^";
			URI uri = new URI(value);
			System.out.println(uri);
		}).isInstanceOf(URISyntaxException.class);
	}

	@Test
	public void create() {
		String value = "https://username:password@www.ruoxue.org:443/java-learn/java-net?amount=101#top";
		URI uri = URI.create(value);
		System.out.println(uri);
		System.out.println("getScheme: " + uri.getScheme());
		System.out.println("getAuthority: " + uri.getAuthority());
		System.out.println("getUserInfo: " + uri.getUserInfo());
		System.out.println("getHost: " + uri.getHost());
		System.out.println("getPort: " + uri.getPort());
		System.out.println("getPath: " + uri.getPath());
		System.out.println("getQuery: " + uri.getQuery());
		System.out.println("getFragment: " + uri.getFragment());

		assertThat(uri.getScheme()).isEqualTo("https");
		assertThat(uri.getAuthority()).isEqualTo("username:password@www.ruoxue.org:443");
		assertThat(uri.getUserInfo()).isEqualTo("username:password");
		assertThat(uri.getHost()).isEqualTo("www.ruoxue.org");
		assertThat(uri.getPort()).isEqualTo(443);
		assertThat(uri.getPath()).isEqualTo("/java-learn/java-net");
		assertThat(uri.getQuery()).isEqualTo("amount=101");
		assertThat(uri.getFragment()).isEqualTo("top");
	}

	@Test(expected = IllegalArgumentException.class)
	public void createThrowException() {
		String value = "https://www.ruoxue.org^";
		URI uri = URI.create(value);
		System.out.println(uri);
	}

	@Test
	public void resolve() {
		URI absoluteUri = URI.create("https://www.ruoxue.org/unit-testing");
		URI relativeUri = URI.create("/java-learn/java-net");
		URI uri = absoluteUri.resolve(relativeUri);
		System.out.println(uri);
		System.out.println("getScheme: " + uri.getScheme());
		System.out.println("getAuthority: " + uri.getAuthority());
		System.out.println("getUserInfo: " + uri.getUserInfo());
		System.out.println("getHost: " + uri.getHost());
		System.out.println("getPort: " + uri.getPort());
		System.out.println("getPath: " + uri.getPath());
		System.out.println("getQuery: " + uri.getQuery());
		System.out.println("getFragment: " + uri.getFragment());

		assertThat(uri.toString()).isEqualTo("https://www.ruoxue.org/java-learn/java-net");
	}

	@Test
	public void relativize() {
		URI absoluteUri = URI.create("https://www.ruoxue.org/java-learn/java-net");
		URI relativeUri = URI.create("https://www.ruoxue.org");
		URI uri = relativeUri.relativize(absoluteUri);
		System.out.println(uri);
		System.out.println("getScheme: " + uri.getScheme());
		System.out.println("getAuthority: " + uri.getAuthority());
		System.out.println("getUserInfo: " + uri.getUserInfo());
		System.out.println("getHost: " + uri.getHost());
		System.out.println("getPort: " + uri.getPort());
		System.out.println("getPath: " + uri.getPath());
		System.out.println("getQuery: " + uri.getQuery());
		System.out.println("getFragment: " + uri.getFragment());

		assertThat(uri.toString()).isEqualTo("java-learn/java-net");
	}

	@Test
	public void equalz() {
		URI uri = URI.create("https://www.ruoxue.org");
		System.out.println(uri);
		URI uri2 = URI.create("https://WWW.RUOXUE.ORG");
		System.out.println(uri2);
		boolean result = uri.equals(uri2);
		System.out.println(result);
		assertThat(result).isTrue();

		uri = URI.create("https://www.ruoxue.org?s=a");
		System.out.println(uri);
		uri2 = URI.create("https://www.ruoxue.org?s=%61");
		System.out.println(uri2);
		result = uri.equals(uri2);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void toStringz() {
		String value = "urn:isbn:1234567890+%25";
		URI uri = URI.create(value);
		System.out.println(uri);
		assertThat(uri.toString()).isEqualTo("urn:isbn:1234567890+%25");

		value = "mailto:ruoxueorg@gmail.com";
		uri = URI.create(value);
		System.out.println(uri);
		assertThat(uri.toString()).isEqualTo("mailto:ruoxueorg@gmail.com");
	}

	@Test
	public void toASCIIString() {
		String value = "urn:isbn:1234567890+%25";
		URI uri = URI.create(value);
		System.out.println(uri.toASCIIString());
		assertThat(uri.toASCIIString()).isEqualTo("urn:isbn:1234567890+%25");

		value = "mailto:ruoxueorg@gmail.com";
		uri = URI.create(value);
		System.out.println(uri);
		assertThat(uri.toASCIIString()).isEqualTo("mailto:ruoxueorg@gmail.com");
	}

	@Test
	public void uriToURL() {
		try {
			URI uri = URI.create("https://www.ruoxue.org");
			System.out.println(uri);
			URL url = uri.toURL();
			System.out.println(url);
			assertThat(url.toString()).isEqualTo("https://www.ruoxue.org");

			uri = URI.create("mailto:ruoxueorg@gmail.com");
			System.out.println(uri);
			url = uri.toURL();
			System.out.println(url);
			assertThat(url.toString()).isEqualTo("mailto:ruoxueorg@gmail.com");
		} catch (MalformedURLException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@Test
	public void uriToURLThrowException() {
		assertThatCode(() -> {
			URI uri = URI.create("urn:isbn:1234567890+%25");
			System.out.println(uri);

			URL url = uri.toURL();
			System.out.println(url);
		}).isInstanceOf(MalformedURLException.class);
	}
}
