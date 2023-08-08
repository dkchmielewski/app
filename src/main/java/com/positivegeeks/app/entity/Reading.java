package com.positivegeeks.app.entity;

import java.util.List;

public class Reading {

	private List<String> date;
	private Time time;
	
	public Reading() {
		super();
	}

	public Reading(List<String> date, Time time) {
		super();
		this.date = date;
		this.time = time;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
}
