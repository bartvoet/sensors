package org.b.v.sensor.agent.configuration.api;

import org.b.v.sensors.api.SensorValue;

public interface SensorConfigurationRepository {
	public void configure(String id,SensorValue value);
}
