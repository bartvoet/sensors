package org.sensor.agent.dependencies.support;

import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorMeasurement;

public class EmptySensorEvents implements SensorEvents {

	@Override
	public void pushMeasurement(SensorMeasurement measurement) {
		
	}

}
