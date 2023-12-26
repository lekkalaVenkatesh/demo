package com.paulCamper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorData {

	@JsonProperty("date")
	public String date;

	@JsonProperty("time")
	public String time;

	@JsonProperty("input")
	public String input;

	@JsonProperty("value")
	public double value;

	@Override
	public String toString() {
		return "SensorData [date=" + date + ", time=" + time + ", input=" + input + ", value=" + value + "]";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
