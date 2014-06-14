package org.b.v.sensor.agent.configuration.memory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.b.v.sensor.agent.configuration.api.SensorConfigurationRepository;
import org.b.v.values.SensorValue;

public class MemorySensorConfigurationRepository implements SensorConfigurationRepository {

	private Map<String,Set<SensorValue>> values;
	
	@Override
	public void configure(String sensorId,SensorValue value) {
		Set<SensorValue> valueSet = null; 
		if(this.values.containsKey(sensorId)){
			valueSet = this.values.get(sensorId);
		}else {
			valueSet = new HashSet<SensorValue>();
			this.values.put(sensorId, valueSet);
		}
		valueSet.add(value);
		
	}

	@Override
	public Collection<SensorValue> values(String sensorId) {
		if(values.containsKey(sensorId)){
			return Collections.emptySet();
		}
		return values.get(sensorId);
	}

	
	
}
