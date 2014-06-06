package org.b.v.sensors.api.support;

import java.util.ArrayList;
import java.util.List;

import org.b.v.sensors.api.SensorType;

public class DefaultSensorType implements SensorType {

	private List<String> configurationParameters=new ArrayList<String>();
	private List<String> typeNames=new ArrayList<String>();
	
	public List<String> getConfigurationParameters() {
		return configurationParameters;
	}

	public List<String> getMeasurementTypes() {
		return typeNames;
	}
	
	public DefaultSensorType addConfigurationParameter(String parameter) {
		configurationParameters.add(parameter);
		return this;
	}
	
	public DefaultSensorType addTypeName(String typeName) {
		typeNames.add(typeName);
		return this;
	}
 
}
