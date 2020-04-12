package com.test.localsdigitalservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.localsdigitalservices.utils.CommonUtil;
import com.test.localsdigitalservices.utils.NewsAPIUtil;

@RestController
@RequestMapping(path = "/news/sources/top", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class MainController {

	private final NewsAPIUtil newsAPIUtil;
	private final CommonUtil commonUtil;
	
	@Autowired
	public MainController(NewsAPIUtil newsAPIUtil, CommonUtil commonUtil) {
		this.newsAPIUtil = newsAPIUtil;
		this.commonUtil = commonUtil;
	}
	
	@GetMapping
	public ResponseEntity<String> topNewsSourcesWithAllParams(
			@RequestParam(required = false) String category, @RequestParam(required = false) String country, 
			@RequestParam Integer k) {
		
		final List<String> top = newsAPIUtil.getTopSources(category, country, k);
		
		return commonUtil.resultToJSON(top);
	}
}
