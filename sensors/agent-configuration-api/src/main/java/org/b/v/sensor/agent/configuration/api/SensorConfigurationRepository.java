package org.b.v.sensor.agent.configuration.api;

import java.util.Collection;
import java.util.Set;

import org.b.v.values.SensorValue;

public interface SensorConfigurationRepository {
	public void newConfigurationForSensor(String sensorId,Set<SensorValue> value);
	public Collection<SensorValue> values(String sensorId);
}
