package org.sensor.foundation.values;


public class SensorValueDefinition implements Comparable<SensorValueDefinition>{

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
	
	public SensorValue withValue(Object object) {
		return new SensorValue(this,object);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SensorValueDefinition){
			return ((SensorValueDefinition)obj).name.equals(name);
		}
		return false;
		
	}

	@Override
	public int compareTo(SensorValueDefinition o) {
		return name.compareTo(o.name);
	}
	
}
