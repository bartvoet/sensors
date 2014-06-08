package org.b.v.sensors.api;

import java.io.IOException;

import org.b.v.sensors.api.error.SensorConfigurationException;


public interface Sensor {

	public String identification();
	
	public SensorType type();
	
	public SensorValue meassure(String type) throws IOException, InterruptedException;

	public void configure(String parameter,SensorValue value) throws SensorConfigurationException;
	
	public void softreset() throws IOException, InterruptedException;


}
