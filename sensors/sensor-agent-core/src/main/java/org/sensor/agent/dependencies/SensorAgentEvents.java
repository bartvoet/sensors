package org.sensor.agent.dependencies;

import org.sensor.foundation.model.SensorConfiguration;
import org.sensor.foundation.model.SensorMeasurement;

public interface SensorAgentEvents {
	public void pushMeasurement(SensorMeasurement measurement);

	public SensorConfiguration getNewConfiguration(String sensorId);
}
