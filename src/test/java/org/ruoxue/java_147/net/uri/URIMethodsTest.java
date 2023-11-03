package org.ruoxue.java_147.net.uri;

import java.net.URI;

import org.junit.Test;

public class URIMethodsTest {
	
	@Test
	public void encode() throws Exception {
		String value = "https://www.ruoxue.org";
		URI uri = new URI(value);
		String scheme = uri.getScheme();
		String host = uri.getHost();
		String query = uri.getRawQuery();
	}
}
