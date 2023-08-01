package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.junit.Test;

public class CollectorsExamplesTest {

	@Test
	public void counting() {
		Collectors.counting();
	}

	@Test
	public void collectingAndThen() {
		Collectors.collectingAndThen(null, null);
	}

	@Test
	public void joining() {
		Collectors.joining();
	}

	@Test
	public void mapping() {
		Collectors.mapping(null, null);
	}

	@Test
	public void reducing() {
		Collectors.reducing(null, null);
	}

}
