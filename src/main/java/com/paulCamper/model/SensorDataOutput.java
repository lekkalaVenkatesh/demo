package com.paulCamper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorDataOutput {

	@JsonProperty("date")
	public String date;

	@JsonProperty("input")
	public String input;

	@JsonProperty("median_value")
	public double value;

	public SensorDataOutput(String date, String input, double value) {
		super();
		this.date = date;
		this.input = input;
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "SensorDataOutput [date=" + date + ", input=" + input + ", value=" + value + "]";
	}

	
}
