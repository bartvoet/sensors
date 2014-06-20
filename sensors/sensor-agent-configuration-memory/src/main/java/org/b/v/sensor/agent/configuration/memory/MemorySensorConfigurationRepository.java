package org.b.v.sensor.agent.configuration.memory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.b.v.sensor.agent.configuration.api.NoConfigurationAvailable;
import org.b.v.sensor.agent.configuration.api.SensorConfigurationRepository;
import org.sensor.foundation.model.SensorConfiguration;

public class MemorySensorConfigurationRepository implements SensorConfigurationRepository {

	private Map<String,SensorConfiguration> values;
	private AtomicInteger integer=new AtomicInteger();
	
	
	@Override
	public SensorConfiguration currentConfiguration(String sensorId) {
		if(!values.containsKey(sensorId)){
			throw new NoConfigurationAvailable();
		}
		return values.get(sensorId);
	}

	@Override
	public void newConfigurationForSensor(String sensorId,SensorConfiguration sensorConfiguration) {
		this.values.put(sensorId,sensorConfiguration.withSequence(integer.incrementAndGet()));
	}

	@Override
	public boolean containsConfigurationForSensor(String sensorId) {
		return values.containsKey(sensorId);
	}

	
	
}
