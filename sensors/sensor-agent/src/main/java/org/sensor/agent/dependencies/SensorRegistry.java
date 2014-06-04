package org.sensor.agent.dependencies;

import java.util.Collection;
import java.util.List;

import org.b.v.sensors.api.Sensor;

public interface SensorRegistry {
	Sensor getSensor(String id);
	Collection<Sensor> activeSensors();
}
