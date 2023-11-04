package org.ruoxue.java_147.net.url;

import java.net.URL;

import org.junit.Test;

public class URLMethodsTest {

	@Test
	public void constructor() throws Exception {
		String value = "https://www.ruoxue.org";
		URL url = new URL(value);
		System.out.println(url);
	}
}
