package org.b.v.sensors.api;

public class SensorValueDefinition {

	private String name;
	private SensorValueType valueType;
	private Object[] options;

	public SensorValueDefinition(String name, SensorValueType valueType) {
		this.name = name;
		this.valueType = valueType;
	}
	
	public SensorValueDefinition(String name, SensorValueType valueType,Object... options) {
		this.name = name;
		this.valueType = valueType;
		this.options=options;
	}
	
	public String getName() {
		return name;
	}
	
	public SensorValueType getValueType() {
		return valueType;
	}

	public Object[] getOptions() {
		return options;
	}
	
}
