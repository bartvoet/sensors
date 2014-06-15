package org.b.v.sensor.agent.configuration.api;

import java.util.Collection;

import org.b.v.values.SensorValue;

public interface SensorConfigurationRepository {
	public void newConfigurationForSensor(String sensorId,SensorValue value);
	public Collection<SensorValue> values(String sensorId);
}
