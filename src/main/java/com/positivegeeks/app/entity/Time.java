package com.positivegeeks.app.entity;

public class Time {
	
	private String time;
	private double value;
	
	public Time() {
		super();
	}
	public Time(String time, double value) {
		super();
		this.time = time;
		this.value = value;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	

}
