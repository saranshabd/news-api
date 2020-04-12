package com.test.localsdigitalservices.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.test.localsdigitalservices.models.AppSecrets;

@Service
public class NewsAPIUtil {

	private final AppSecrets appSecrets;
	
	@Autowired
	public NewsAPIUtil(AppSecrets appSecrets) {
		this.appSecrets = appSecrets;
	}
	
	public List<String> getTopSources(final String category, final String country, final int k) {
		
		final JSONObject response;
		try {
			response = makeHTTPRequest(category, country, k);
		} catch (WebClientResponseException ignored) {
			// something went wrong, returning empty list
			return new LinkedList<>();
		}
		
		final JSONArray sources = getSourcesFromResponse(response);
		
		return getTopNewsSources(sources, k);
	}
	
	// ==================================================================================================================
	// Private Utility Methods ==========================================================================================
	// ==================================================================================================================
	
	private JSONObject makeHTTPRequest(final String category, final String country, final int k) {
		
		final String uri = buildUri(category, country);
		
		final WebClient client = WebClient.create(appSecrets.getNewsAPIUrl());
		return new JSONObject(client.get().uri(uri).retrieve().bodyToMono(String.class).block());
	}
	
	private JSONArray getSourcesFromResponse(final JSONObject response) {
		
		return response.getJSONArray("sources");
	}
	
	private String buildUri(final String category, final String country) {
		
		final StringBuilder builder = new StringBuilder("/v2/sources?");
		
		// add parameters
		if (null != category) builder.append(String.format("category=%s&", category));
		if (null != country)  builder.append(String.format("country=%s&", country));
		
		// add API key
		builder.append(String.format("apiKey=%s", appSecrets.getNewsAPIKey()));
		
		return builder.toString();
	}
	
	private List<String> getTopNewsSources(final JSONArray articles, final int k) {
		
		final Map<String, Integer> newsSources = extractNewsSourcesAndOccurrences(articles);
		
		final List<Entry<String, Integer>> sortedNewsSources = sortNewsSourcesBasedOnOccurrences(newsSources);
		
		return finalTopSourceList(sortedNewsSources, k);
	}
	
	private Map<String, Integer> extractNewsSourcesAndOccurrences(final JSONArray sources) {

		final Map<String, Integer> sourcesCount = new HashMap<>();
		
		for (int i = 0; i < sources.length(); ++i) {
			final JSONObject source = (JSONObject) sources.get(i);
			final String sourceName = source.getString("name");
			
			if (sourcesCount.containsKey(sourceName))
				sourcesCount.put(sourceName, 1 + sourcesCount.get(sourceName));
			else sourcesCount.put(sourceName, 1);
		}
		
		return sourcesCount;
	}
	
	private List<Entry<String, Integer>> sortNewsSourcesBasedOnOccurrences(final Map<String, Integer> newsSources) {
		
		final List<Entry<String, Integer>> sortedList = new LinkedList<Entry<String, Integer>>(newsSources.entrySet());
		Collections.sort(sortedList, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
            	return o1.getValue().compareTo(o2.getValue());
            }
        });
		
		return sortedList;
	}
	
	private List<String> finalTopSourceList(final List<Entry<String, Integer>> sortedNewsSources, final int k) {
		
		final List<String> topSources = new LinkedList<>();
		int curr = 0;
		for (Entry<String, Integer> entry : sortedNewsSources) {
			if (curr == k) break;
			topSources.add(entry.getKey());
			++curr;
        }
		
		return topSources;
	}
}
