package com.positivegeeks.app.controller;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.positivegeeks.app.service.MeterServiceImpl;

@RestController
@RequestMapping("/")
public class MeterController {

	@Autowired
	MeterServiceImpl service;
	
//	localhost:8080/search/874829/2023-07-05%2021%3A28/2023-07-08%2021%3A28
	@GetMapping("search/{meterId}/{begin}/{end}")
	public ResponseEntity<String> getMeterData(@PathVariable String meterId, 
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date begin, 
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date end) throws java.text.ParseException {
		JSONObject obj = service.getMeterData(meterId, begin, end);
		String prettyJson = obj.toString(4);
//		String prettyJson = null;
		if(prettyJson==null) {
			return new ResponseEntity<>("Data does not exist, please try again!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(prettyJson, HttpStatus.OK);
				
	}

}
