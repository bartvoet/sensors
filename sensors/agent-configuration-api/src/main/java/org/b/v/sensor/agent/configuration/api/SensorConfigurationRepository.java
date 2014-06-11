package org.b.v.sensor.agent.configuration.api;

import java.util.Collection;

import org.b.v.sensors.api.SensorValue;

public interface SensorConfigurationRepository {
	public void configure(String id,SensorValue value);
	public Collection<SensorValue> values();
}
