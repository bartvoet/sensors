package org.b.v.sensors.api;

import java.util.List;

public interface SensorType {
	List<String> getValueTypes();
	List<String> getConfigurationParameters();
	List<String> getTypeNames();
}
