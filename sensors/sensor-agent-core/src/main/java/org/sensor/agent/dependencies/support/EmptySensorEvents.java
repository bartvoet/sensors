package org.sensor.agent.dependencies.support;

import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.foundation.model.SensorConfiguration;
import org.sensor.foundation.model.SensorMeasurement;

public class EmptySensorEvents implements SensorEvents {

	@Override
	public void pushMeasurement(SensorMeasurement measurement) {
		
	}

	@Override
	public SensorConfiguration getNewConfiguration(String sensorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
