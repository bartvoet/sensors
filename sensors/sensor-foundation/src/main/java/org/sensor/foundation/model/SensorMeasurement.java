package org.sensor.foundation.model;

import java.util.Date;

import org.sensor.foundation.values.SensorValue;

public class SensorMeasurement {

	private String id;
	private String type;
	private Date time;
	private SensorValue value;

	public SensorMeasurement(String id, String type,Date time, SensorValue value) {
		super();
		this.id = id;
		this.time=time;
		this.value = value;
		this.type = type;
	}

	public String getId() {
		return id;
	}
	
	public SensorValue getValue() {
		return value;
	}
	
	public String getType() {
		return type;
	}
	
	public Date getDate(){
		return time;
	}
}
