package com.test.localsdigitalservices.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class AppSecrets {

	@Getter @Value("${server.news.api.key}")
	private String newsAPIKey;
	
	@Getter @Value("${server.news.api.url}")
	private String newsAPIUrl;
}
