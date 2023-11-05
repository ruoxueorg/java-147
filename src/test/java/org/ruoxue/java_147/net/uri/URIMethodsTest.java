package org.ruoxue.java_147.net.uri;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class URIMethodsTest {

	@Test
	public void constructor() {
		try {
			String value = "https://username:password@www.ruoxue.org:443/java-learn/java-net?amount=101#top";
			URI uri = new URI(value);
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

			URI uri2 = new URI("https", "username:password", "www.ruoxue.org", 443, "/java-learn/java-net",
					"amount=101", "top");
			System.out.println(uri2);
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
	public void resolve() {
		URI absoluteUri = URI.create("https://www.ruoxue.org/unit-testing");
		URI relativeUri = URI.create("/java-learn/java-net");
		URI uri = absoluteUri.resolve(relativeUri);
		System.out.println(uri);
		assertThat(uri.toString()).isEqualTo("https://www.ruoxue.org/java-learn/java-net");

		absoluteUri = URI.create("https://www.ruoxue.org/");
		relativeUri = URI.create("/java-learn");
		uri = absoluteUri.resolve(relativeUri);
		System.out.println(uri);
		assertThat(uri.toString()).isEqualTo("https://www.ruoxue.org/java-learn");
	}

	@Test
	public void relativize() {
		URI absoluteUri = URI.create("https://www.ruoxue.org/java-learn/java-net");
		URI relativeUri = URI.create("https://www.ruoxue.org");
		URI uri = relativeUri.relativize(absoluteUri);
		System.out.println(uri);
		assertThat(uri.toString()).isEqualTo("java-learn/java-net");

		absoluteUri = URI.create("https://www.ruoxue.org/java-learn");
		relativeUri = URI.create("https://www.ruoxue.org/");
		uri = relativeUri.relativize(absoluteUri);
		System.out.println(uri);
		assertThat(uri.toString()).isEqualTo("java-learn");
	}

	@Test
	public void normalize() {
		URI uri = URI.create("https://www.ruoxue.org/a/b/../c/./d");
		URI result = uri.normalize();
		System.out.println(result);
		assertThat(result.toString()).isEqualTo("https://www.ruoxue.org/a/c/d");

		uri = URI.create("https://www.ruoxue.org/java-learn");
		result = uri.normalize();
		System.out.println(result);
		assertThat(result.toString()).isEqualTo("https://www.ruoxue.org/java-learn");
	}

	@Test
	public void isOpaque() {
		URI uri = URI.create("https:www.ruoxue.org");
		System.out.println(uri);
		boolean result = uri.isOpaque();
		System.out.println(result + ", getPath: " + uri.getPath());
		assertThat(result).isTrue();

		uri = URI.create("https://www.ruoxue.org");
		System.out.println(uri);
		result = uri.isOpaque();
		System.out.println(result + ", getPath: " + uri.getPath());
		assertThat(result).isFalse();
	}
}
