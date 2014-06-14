package org.b.v.sensors.api;

import java.util.List;

import org.b.v.values.SensorValueDefinition;

public interface SensorType {
	List<SensorValueDefinition> getConfigurationParameters();
	List<SensorValueDefinition> getMeasurementTypes();
}
