package org.b.v.sensors.api;

import java.util.List;
import java.util.Set;

import org.b.v.values.SensorValue;
import org.b.v.values.SensorValueDefinition;

public interface SensorType {
	List<SensorValueDefinition> getConfigurationParameters();
	Set<SensorValue> getDefaultConfiguration();
	List<SensorValueDefinition> getMeasurementTypes();
}
