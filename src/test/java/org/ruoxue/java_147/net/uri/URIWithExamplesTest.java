package org.ruoxue.java_147.net.uri;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.Test;

public class URIWithExamplesTest {

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
		String value = "https://www.ruoxue.org";
		URI uri = URI.create(value);
		System.out.println(uri);
		assertThat(uri.toString()).isEqualTo("https://www.ruoxue.org");

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
}
