package org.b.v.sensors.api;

import java.util.List;

public interface SensorType {
	List<String> getConfigurationParameters();
	List<String> getMeasurementTypes();
}
