package org.sensor.foundation.values;

public enum SensorValueType {
	DECIMAL,STRING,LONG,DATE;
	
	public SensorValueDefinition definition(String name) {
		return new SensorValueDefinition(name,this);
	}
	
	public SensorValueDefinition definitionWithOptions(String name,Object ... options) {
		return new SensorValueDefinition(name,this,options);
	}
}
