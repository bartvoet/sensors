package org.b.v.sensors.api;

import java.util.List;

public interface SensorType {
	List<SensorValueDefinition> getConfigurationParameters();
	List<SensorValueDefinition> getMeasurementTypes();
}
