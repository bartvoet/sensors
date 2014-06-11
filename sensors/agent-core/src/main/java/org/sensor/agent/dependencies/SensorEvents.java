package org.sensor.agent.dependencies;

import java.util.Collection;

import org.b.v.sensors.api.SensorValue;

public interface SensorEvents {
	public void pushMeasurement(SensorMeasurement measurement);

	public Collection<SensorValue> getInstructions();
}
