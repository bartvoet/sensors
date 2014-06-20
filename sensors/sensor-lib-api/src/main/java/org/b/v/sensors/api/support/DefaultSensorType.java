package org.b.v.sensors.api.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.b.v.sensors.api.SensorType;
import org.sensor.foundation.values.SensorValue;
import org.sensor.foundation.values.SensorValueDefinition;
import org.sensor.foundation.values.SensorValueType;

public class DefaultSensorType implements SensorType {

	private List<SensorValueDefinition> configurationParameters=new ArrayList<SensorValueDefinition>();
	private List<SensorValueDefinition> typeNames=new ArrayList<SensorValueDefinition>();
	private Set<SensorValue> defaultConfiguration = new HashSet<SensorValue>();
	
	public List<SensorValueDefinition> getConfigurationParameters() {
		return configurationParameters;
	}

	public List<SensorValueDefinition> getMeasurementTypes() {
		return typeNames;
	}
	
//	public DefaultSensorType addConfigurationParameter(String parameter,SensorValueType type) {
//		configurationParameters.add(new SensorValueDefinition(parameter, type));
//		return this;
//	}
//	
//	public DefaultSensorType addConfigurationParameterWithOptions(String parameter,SensorValueType type,Object ...options) {
//		configurationParameters.add(new SensorValueDefinition(parameter, type,options));
//		return this;
//	}
	
	public DefaultSensorType addConfiguration(ConfigurationBuilder configuration) {
		SensorValue value = configuration.build();
		configurationParameters.add(value.getDefinition());
		defaultConfiguration.add(value);
		return this;
	}
	
	public DefaultSensorType addMeassurementType(String typeName,SensorValueType type) {
		typeNames.add(new SensorValueDefinition(typeName, type));
		return this;
	}

	@Override
	public Set<SensorValue> getDefaultConfiguration() {
		return defaultConfiguration;
	}
	
	public static ConfigurationBuilder configuration(String name) {
		return new ConfigurationBuilder(name);
	}
	
	public static final class ConfigurationBuilder {
		private String parameter;
		private Object[] options;
		private SensorValueType type;
		private Object value;
		
		public ConfigurationBuilder(String name) {
			this.parameter=name;
		}
		
		public ConfigurationBuilder withType(SensorValueType type) {
			this.type=type;
			return this;
		}
		
		public ConfigurationBuilder withOptions(Object... options) {
			this.options=options;
			return this;
		}
		
		public ConfigurationBuilder withDefaultValue(Object value) {
			this.value=value;
			return this;
		}
		
		public SensorValue build(){
			return new SensorValue(new SensorValueDefinition(parameter, type,options),value);
		}
	}
 
}
