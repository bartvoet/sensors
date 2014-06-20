package org.sensor.foundation.values;

public class SensorValue implements Comparable<SensorValue>{

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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SensorValue) {
			return ((SensorValue)obj).definition.equals(definition);
		}
		return false;
	}

	@Override
	public int compareTo(SensorValue o) {
		return this.definition.compareTo(o.definition);
	}
	
}
