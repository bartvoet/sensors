package org.b.v.sensor.agent.configuration.memory;

import java.util.Collection;
import java.util.Map;

import org.b.v.sensor.agent.configuration.api.SensorConfigurationRepository;
import org.b.v.sensors.api.SensorValue;

public class MemorySensorConfigurationRepository implements SensorConfigurationRepository {

	private Map<String,SensorValue> values;
	
	@Override
	public void configure(String sensorId,SensorValue value) {
		this.values.put(sensorId, value);
	}

	@Override
	public Collection<SensorValue> values() {
		return values.values();
	}

	
	
}
