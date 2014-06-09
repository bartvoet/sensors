package org.b.v.sensors.api;

public class SensorValue {

	private Object value;
	private SensorValueDefinition definition;
	
	public SensorValue(SensorValueDefinition definition,Object value) {
		this.definition=definition;
		this.value=value;
	}
	
	public SensorValueDefinition getDefinition() {
		return definition;
	}
	
	public Object getValue() {
		return value;
	}

	public static SensorValue decimal(String name,double readTemperature) {
		return new SensorValue(new SensorValueDefinition(name,SensorValueType.DECIMAL),readTemperature);
	}
	
}
