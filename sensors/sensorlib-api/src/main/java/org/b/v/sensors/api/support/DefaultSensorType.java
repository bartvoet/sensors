package org.b.v.sensors.api.support;

import java.util.ArrayList;
import java.util.List;

import org.b.v.sensors.api.SensorType;
import org.b.v.sensors.api.SensorValueDefinition;
import org.b.v.sensors.api.SensorValueType;

public class DefaultSensorType implements SensorType {

	private List<SensorValueDefinition> configurationParameters=new ArrayList<SensorValueDefinition>();
	private List<SensorValueDefinition> typeNames=new ArrayList<SensorValueDefinition>();
	
	public List<SensorValueDefinition> getConfigurationParameters() {
		return configurationParameters;
	}

	public List<SensorValueDefinition> getMeasurementTypes() {
		return typeNames;
	}
	
	public DefaultSensorType addConfigurationParameter(String parameter,SensorValueType type) {
		configurationParameters.add(new SensorValueDefinition(parameter, type));
		return this;
	}
	
	public DefaultSensorType addConfigurationParameterWithOptions(String parameter,SensorValueType type,Object ...options) {
		configurationParameters.add(new SensorValueDefinition(parameter, type,options));
		return this;
	}
	
	public DefaultSensorType addMeassurementType(String typeName,SensorValueType type) {
		typeNames.add(new SensorValueDefinition(typeName, type));
		return this;
	}
 
}
