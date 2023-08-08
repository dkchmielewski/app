package com.positivegeeks.app.controller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.positivegeeks.app.entity.Meter;
import com.positivegeeks.app.service.MeterServiceImpl;

@RestController
@RequestMapping("/")
public class MeterController {

	@Autowired
	MeterServiceImpl service;
	
	@GetMapping("search/{meterId}/{startTime}/{endTime}")
	public List<Meter> getMeterData(@PathVariable Long meterId, @PathVariable String startTime, @PathVariable String endTime) throws ParseException, java.text.ParseException {
		service.getMeterData(meterId, startTime, endTime);
		return null;
	}
	
	@GetMapping("test/{id}/{num}")
	public String test(@PathVariable int id, @PathVariable String num) {
		return id + num;
	}
	
}
