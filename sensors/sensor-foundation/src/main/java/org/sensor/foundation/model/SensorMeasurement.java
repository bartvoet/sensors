package org.sensor.foundation.model;

import java.util.Date;

import org.sensor.foundation.values.SensorValue;

public class SensorMeasurement {

	private String sensorId;
	private Date measurementTime;
	private SensorValue measurementValue;

	public SensorMeasurement(String sensorId, Date time, SensorValue value) {
		super();
		this.sensorId = sensorId;
		this.measurementTime=time;
		this.measurementValue = value;
	}

	public String getSensorId() {
		return sensorId;
	}
	
	public SensorValue getMeasurementValue() {
		return measurementValue;
	}
	
	public Date getMeasurementDate(){
		return measurementTime;
	}
}
