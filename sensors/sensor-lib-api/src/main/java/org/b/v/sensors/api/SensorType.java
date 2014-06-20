package org.b.v.sensors.api;

import java.util.List;
import java.util.Set;

import org.sensor.foundation.values.SensorValue;
import org.sensor.foundation.values.SensorValueDefinition;

public interface SensorType {
	List<SensorValueDefinition> getConfigurationParameters();
	Set<SensorValue> getDefaultConfiguration();
	List<SensorValueDefinition> getMeasurementTypes();
}
