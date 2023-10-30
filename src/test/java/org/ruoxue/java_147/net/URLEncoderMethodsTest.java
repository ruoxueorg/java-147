package org.ruoxue.java_147.net;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class URLEncoderMethodsTest {

	@Test
	public void encode() {
		try {
			String value = "https://www.ruoxue.org";
			System.out.println(value);
			String result = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
			System.out.println(result);
			assertThat(result).isEqualTo("https%3A%2F%2Fwww.ruoxue.org");
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void encodeQuery() {
		try {
			String url = "https://www.ruoxue.org/?s=";
			String value = "name %";
			System.out.println(value);
			String result = url + URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
			System.out.println(result);
			assertThat(result).isEqualTo("https://www.ruoxue.org/?s=name+%25");
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void encodeThrowException() {
		assertThatCode(() -> {
			String value = "email@!$";
			System.out.println(value);
			String result = URLEncoder.encode(value, "X-8");
			System.out.println(result);
		}).isInstanceOf(UnsupportedEncodingException.class);
	}

	@Test
	public void encodeURL() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("user", "user");
		paramMap.put("name", "name %");
		paramMap.put("email", "email@!$");
		paramMap.put("amount", "101");
		paramMap.put("timestamp", "1470926696715");
		System.out.println(paramMap);

		String result = paramMap.keySet().stream().map(k -> {
			try {
				String key = URLEncoder.encode(k, StandardCharsets.UTF_8.toString());
				String value = URLEncoder.encode(paramMap.get(k), StandardCharsets.UTF_8.toString());
				StringBuilder buff = new StringBuilder();
				buff.append(key);
				buff.append("=");
				buff.append(value);
				return buff.toString();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}).collect(Collectors.joining("&", "https://www.ruoxue.org?", ""));
		System.out.println(result);
		assertThat(result).isEqualTo(
				"https://www.ruoxue.org?amount=101&name=name+%25&user=user&email=email%40%21%24&timestamp=1470926696715");
	}
}
