package com.test.localsdigitalservices.utils;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommonUtil {

	public ResponseEntity<String> resultToJSON(final List<String> result) {
		
		final JSONObject response = new JSONObject();
		response.put("count", result.size());
		response.put("top", result);
		
		return new ResponseEntity<>(response.toString(), HttpStatus.OK);
	}
}
