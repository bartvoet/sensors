package org.b.v.sensors.api;

import java.io.IOException;
import java.util.Set;

import org.b.v.sensors.api.error.SensorConfigurationException;
import org.b.v.values.SensorValue;


public interface Sensor {

	public String identification();
	
	public SensorType type();
	
	public SensorValue meassure(String name) throws IOException, InterruptedException;

	public void configure(Set<SensorValue> configurationValues) throws SensorConfigurationException;
	
	public void softreset() throws IOException, InterruptedException;

}
