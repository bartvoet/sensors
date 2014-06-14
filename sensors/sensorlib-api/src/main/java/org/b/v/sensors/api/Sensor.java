package org.b.v.sensors.api;

import java.io.IOException;

import org.b.v.sensors.api.error.SensorConfigurationException;
import org.b.v.values.SensorValue;


public interface Sensor {

	public String identification();
	
	public SensorType type();
	
	public SensorValue meassure(String name) throws IOException, InterruptedException;

	public void configure(String parameter,SensorValue value) throws SensorConfigurationException;
	
	public void softreset() throws IOException, InterruptedException;

}
