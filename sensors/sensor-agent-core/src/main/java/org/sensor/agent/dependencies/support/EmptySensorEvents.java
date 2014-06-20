package org.sensor.agent.dependencies.support;

import org.b.v.sensor.agent.configuration.api.SensorConfiguration;
import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorMeasurement;

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
