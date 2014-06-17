package org.b.v.sensor.agent.configuration.memory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.b.v.sensor.agent.configuration.api.NoConfigurationAvailable;
import org.b.v.sensor.agent.configuration.api.SensorConfiguration;
import org.b.v.sensor.agent.configuration.api.SensorConfigurationRepository;
import org.b.v.values.SensorValue;

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
	public void newConfigurationForSensor(String sensorId,String externalId,Set<SensorValue> values) {
		this.values.put(sensorId, 
						new SensorConfiguration(integer.incrementAndGet(),values,externalId)
		);
	}

	@Override
	public boolean containsConfigurationForSensor(String sensorId) {
		return values.containsKey(sensorId);
	}

	
	
}
