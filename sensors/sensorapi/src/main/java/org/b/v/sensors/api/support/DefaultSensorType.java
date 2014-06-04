package org.b.v.sensors.api.support;

import java.util.ArrayList;
import java.util.List;

import org.b.v.sensors.api.SensorType;

public class DefaultSensorType implements SensorType {

	private List<String> valueTypes=new ArrayList<String>();
	private List<String> configurationParameters=new ArrayList<String>();
	private List<String> typeNames=new ArrayList<String>();
	
	public List<String> getValueTypes() {
		return valueTypes;
	}

	public List<String> getConfigurationParameters() {
		return configurationParameters;
	}

	public List<String> getTypeNames() {
		return typeNames;
	}
	
	public DefaultSensorType addValueType(String value){
		valueTypes.add(value);
		return this;
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
