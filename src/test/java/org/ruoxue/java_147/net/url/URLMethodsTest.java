package org.ruoxue.java_147.net.url;

import java.net.URI;
import java.net.URL;

import org.junit.Test;

public class URLMethodsTest {

	@Test
	public void encode() throws Exception {
		String value = "https://www.ruoxue.org";
		URL url = new URL(value);
		String scheme = url.getScheme();
		String host = url.getHost();
		String query = url.getRawQuery();
	}
}
