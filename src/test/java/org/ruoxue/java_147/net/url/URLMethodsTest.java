package org.ruoxue.java_147.net.url;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class URLMethodsTest {

	@Test
	public void constructor() {
		try {
			String value = "https://username:password@www.ruoxue.org/java-learn/java-net?amount=101#top";
			URL url = new URL(value);
			System.out.println(url);
			System.out.println("Protocol: " + url.getProtocol());
			System.out.println("Authority: " + url.getAuthority());
			System.out.println("UserInfo: " + url.getUserInfo());
			System.out.println("Host: " + url.getHost());
			System.out.println("Port: " + url.getPort());
			System.out.println("DefaultPort: " + url.getDefaultPort());
			System.out.println("File: " + url.getFile());
			System.out.println("Path: " + url.getPath());
			System.out.println("Query: " + url.getQuery());
			System.out.println("Ref: " + url.getRef());

			URL url2 = new URL("https", "www.ruoxue.org", 443, "/java-learn/java-net?amount=101#top");
			System.out.println(url2);
			assertThat(url.getProtocol()).isEqualTo(url2.getProtocol());
			assertThat(url.getHost()).isEqualTo(url2.getHost());
			assertThat(url.getDefaultPort()).isEqualTo(url2.getDefaultPort());
			assertThat(url.getFile()).isEqualTo(url2.getFile());
			assertThat(url.getPath()).isEqualTo(url2.getPath());
			assertThat(url.getQuery()).isEqualTo(url2.getQuery());
			assertThat(url.getRef()).isEqualTo(url2.getRef());
		} catch (MalformedURLException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@Test
	public void constructorThrowException() {
		assertThatCode(() -> {
			String value = "urn:www.ruoxue.org";
			URL url = new URL(value);
			System.out.println(url);
		}).isInstanceOf(MalformedURLException.class);
	}
}
