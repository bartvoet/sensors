package org.b.v.sensors.api;

public class SensorValueDefinition {

	private String name;
	private SensorValueType valueType;

	public SensorValueDefinition(String name, SensorValueType valueType) {
		this.name = name;
		this.valueType = valueType;
	}
	
	public String getName() {
		return name;
	}
	
	public SensorValueType getValueType() {
		return valueType;
	}
	
}
