package org.sensor.agent.dependencies.support;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.b.v.sensors.api.Sensor;
import org.sensor.agent.dependencies.SensorRegistry;
import org.sensor.agent.error.SensorNotExisting;

public class DefaultSensorRegistry implements SensorRegistry {

	private Map<String,Sensor> sensors=new HashMap<String,Sensor>();
	
	@Override
	public Sensor getSensor(String id) throws SensorNotExisting{
		if(id==null || !sensors.containsKey(id)) {
			throw new SensorNotExisting();
		}
		return sensors.get(id);
	}

	@Override
	public Collection<Sensor> activeSensors() {
		return sensors.values();
	}
	
	public DefaultSensorRegistry addSensor(String id,Sensor sensor){
		sensors.put(id, sensor);
		return this;
	}

}
