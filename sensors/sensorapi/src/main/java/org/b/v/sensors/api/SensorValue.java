package org.b.v.sensors.api;

public class SensorValue {

	private SensorValueType type;
	private Object value;
	
	public SensorValue(SensorValueType type,Object value) {
		this.type=type;
		this.value=value;
	}
	
	public SensorValueType getType() {
		return type;
	}
	
	public Object getValue() {
		return value;
	}

	public static SensorValue decimal(double readTemperature) {
		return new SensorValue(SensorValueType.DECIMAL,readTemperature);
	}
	
}
