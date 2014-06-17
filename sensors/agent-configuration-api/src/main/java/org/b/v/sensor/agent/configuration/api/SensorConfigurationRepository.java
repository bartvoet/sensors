package org.b.v.sensor.agent.configuration.api;

import java.util.Set;

import org.b.v.values.SensorValue;

public interface SensorConfigurationRepository {
	public boolean containsConfigurationForSensor(String sensorId);
	public void newConfigurationForSensor(String sensorId,String externalId,Set<SensorValue> value);
	public SensorConfiguration currentConfiguration(String sensorId) throws NoConfigurationAvailable;
}
