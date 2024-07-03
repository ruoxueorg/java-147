package org.ruoxue.java_147.net;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class DifferenceUriUrlTest {

	@Test
	public void createURI() {
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

	@Test
	public void createURL() throws MalformedURLException {
		String value = "http://www.ruoxue.org/java-learn";
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

		URL url2 = new URL("http", "www.ruoxue.org", 80, "/java-learn");
		System.out.println(url2);
		assertThat(url.getProtocol()).isEqualTo(url2.getProtocol());
		assertThat(url.getHost()).isEqualTo(url2.getHost());
		assertThat(url.getDefaultPort()).isEqualTo(url2.getDefaultPort());
		assertThat(url.getFile()).isEqualTo(url2.getFile());
		assertThat(url.getPath()).isEqualTo(url2.getPath());
		assertThat(url.getQuery()).isEqualTo(url2.getQuery());
		assertThat(url.getRef()).isEqualTo(url2.getRef());
	}

	@Test
	public void conversion() throws URISyntaxException, MalformedURLException {
		String value = "https://host:443/path?query";
		URI uri = new URI(value);
		URL url = new URL(value);

		URL toURL = uri.toURL();
		URI toURI = url.toURI();

		assertThat(url).isNotNull();
		assertThat(uri).isNotNull();
		System.out.println("toURL: " + toURL.toString());
		System.out.println("toURI: " + toURI.toString());
		assertThat(toURL.toString()).isEqualTo(toURI.toString());
	}

	@Test
	public void openStream() throws IOException {
		URL url = new URL("https://www.ruoxue.org");
		String contents = IOUtils.toString(url.openStream(), StandardCharsets.UTF_8);
		assertThat(contents).isNotNull();
	}
}