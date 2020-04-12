package com.test.localsdigitalservices.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NewsAPIUtilTests {
	
	private final NewsAPIUtil newsAPIUtil;
	
	@Autowired
	public NewsAPIUtilTests(NewsAPIUtil newsAPIUtil) {
		this.newsAPIUtil = newsAPIUtil;
	}

	@Test
	public void simpleRequestTest() {
		
		// parameters
		final String category = null,
				country = "us";
		final int k = 10;
		
		// fetch top sources from API
		List<String> topSources = newsAPIUtil.getTopSources(category, country, k);
		
		// test result
		assertTrue(topSources.size() <= k);
	}
	
	@Test
	public void simpleRequestWithLessResponseTest() {
		
		// parameters
		final String category = null,
				country = "in";
		final int k = 10;
		
		// fetch top sources from API
		List<String> topSources = newsAPIUtil.getTopSources(category, country, k);
		
		// test result
		assertTrue(topSources.size() <= k);
	}
	
	@Test
	public void simpleRequestWithAllParamsTest() {
		
		// parameters
		final String category = "technology",
				country = "in";
		final int k = 12;
		
		// fetch top sources from API
		List<String> topSources = newsAPIUtil.getTopSources(category, country, k);
		
		// test result
		assertTrue(topSources.size() <= k);
	}
	
	@Test
	public void simpleRequestWithInvalidParamsTest() {
		
		// parameters
		final String category = "technology",
				country = "weiuhfd";
		final int k = 12;
		
		// fetch top sources from API
		List<String> topSources = newsAPIUtil.getTopSources(category, country, k);
		
		// test result
		assertEquals(0, topSources.size());
	}
	
	@Test
	public void simpleRequestWithNoParamsTest() {
		
		// parameters
		final String category = null,
				country = null;
		final int k = 12;
		
		// fetch top sources from API
		List<String> topSources = newsAPIUtil.getTopSources(category, country, k);
		
		// test result
		assertTrue(topSources.size() <= k);
	}
}
