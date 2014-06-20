package org.sensor.agent.dependencies;

import org.b.v.sensor.agent.configuration.api.SensorConfiguration;

public interface SensorEvents {
	public void pushMeasurement(SensorMeasurement measurement);

	public SensorConfiguration getNewConfiguration(String sensorId);
}
