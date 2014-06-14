package org.sensor.agent.dependencies.support;

import java.util.Collection;

import org.b.v.values.SensorValue;
import org.sensor.agent.dependencies.SensorEvents;
import org.sensor.agent.dependencies.SensorMeasurement;

public class EmptySensorEvents implements SensorEvents {

	@Override
	public void pushMeasurement(SensorMeasurement measurement) {
		
	}

	@Override
	public Collection<SensorValue> getInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

}
