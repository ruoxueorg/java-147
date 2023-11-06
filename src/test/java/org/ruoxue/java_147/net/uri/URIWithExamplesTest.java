package org.ruoxue.java_147.net.uri;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class URIWithExamplesTest {

	@Test
	public void create() {
		String value = "https://username:password@www.ruoxue.org:443/java-learn/java-net?amount=101#top";
		URI uri = URI.create(value);
		System.out.println(uri);
		System.out.println("Scheme: " + uri.getScheme());
		System.out.println("SchemeSpecificPart: " + uri.getSchemeSpecificPart());
		System.out.println("Authority: " + uri.getAuthority());
		System.out.println("UserInfo: " + uri.getUserInfo());
		System.out.println("Host: " + uri.getHost());
		System.out.println("Port: " + uri.getPort());
		System.out.println("Path: " + uri.getPath());
		System.out.println("Query: " + uri.getQuery());
		System.out.println("Fragment: " + uri.getFragment());

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
		try {
			URI uri = new URI("https", "www.ruoxue.org", "/java-learn", "name=name %", null);
			System.out.println(uri.toString());
			assertThat(uri.toString()).isEqualTo("https://www.ruoxue.org/java-learn?name=name%20%25");

			uri = new URI("mailto", "ruo xue org@gmail.com", null);
			System.out.println(uri.toString());
			assertThat(uri.toString()).isEqualTo("mailto:ruo%20xue%20org@gmail.com");
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@Test
	public void toASCIIString() {
		try {
			URI uri = new URI("https", "www.ruoxue.org", "/java-learn", "name=name %", null);
			System.out.println(uri.toASCIIString());
			assertThat(uri.toASCIIString()).isEqualTo("https://www.ruoxue.org/java-learn?name=name%20%25");

			uri = new URI("mailto", "ruo xue org@gmail.com", null);
			System.out.println(uri.toASCIIString());
			assertThat(uri.toASCIIString()).isEqualTo("mailto:ruo%20xue%20org@gmail.com");
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}
