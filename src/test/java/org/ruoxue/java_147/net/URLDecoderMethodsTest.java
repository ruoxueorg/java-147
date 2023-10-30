package org.ruoxue.java_147.net;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

public class URLDecoderMethodsTest {

	@Test
	public void decode() {
		try {
			String value = "https%3A%2F%2Fwww.ruoxue.org";
			System.out.println(value);
			String result = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
			System.out.println(result);
			assertThat(result).isEqualTo("https://www.ruoxue.org");
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void decodeQuery() {
		try {
			String value = "https://www.ruoxue.org/?s=name+%25";
			System.out.println(value);
			URI uri = new URI(value);
			String query = uri.getRawQuery();
			System.out.println(query);
			String[] array = query.split("=");
			String result = URLDecoder.decode(array[1], StandardCharsets.UTF_8.toString());
			System.out.println(result);
			assertThat(result).isEqualTo("name %");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void decodeThrowException() {
		assertThatCode(() -> {
			String value = "email%40%21%24";
			System.out.println(value);
			String result = URLDecoder.decode(value, "X-8");
			System.out.println(result);
		}).isInstanceOf(UnsupportedEncodingException.class);
	}

	@Test
	public void decodeURL() {
		try {
			String value = "https://www.ruoxue.org?amount=101&email=email%40%21%24&name=name+%25&timestamp=1470926696715&user=user";
			URI uri = new URI(value);
			String scheme = uri.getScheme();
			String host = uri.getHost();
			String query = uri.getRawQuery();
			System.out.println(query);
			String result = Arrays.stream(query.split("&")).map(e -> {
				try {
					String[] array = e.split("=");
					String param = URLDecoder.decode(array[0], StandardCharsets.UTF_8.toString()) + "="
							+ URLDecoder.decode(array[1], StandardCharsets.UTF_8.toString());
					return param;
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}).collect(Collectors.joining("&", scheme + "://" + host + "?", ""));
			System.out.println(result);
			assertThat(result).isEqualTo(
					"https://www.ruoxue.org?amount=101&email=email@!$&name=name %&timestamp=1470926696715&user=user");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
