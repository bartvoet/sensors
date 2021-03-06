package org.sensor.foundation.model;

import java.util.Date;
import java.util.Set;

import org.sensor.foundation.values.SensorValue;

public class SensorConfiguration {

	private Integer sequence;
	private String centralizedId;
	private Date creationTime;
	private Set<SensorValue> values;

	public SensorConfiguration(Integer sequence,Set<SensorValue> values) {
		this.sequence = sequence;
		this.creationTime=new Date();
		this.values=values;
	}
	
	public SensorConfiguration(Integer sequence,Set<SensorValue> values,String centralizedId) {
		this(sequence,values);
		this.centralizedId=centralizedId;
	}
	
	public Integer getSequence() {
		return sequence;
	}

	public String getCentralizedId() {
		return centralizedId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Set<SensorValue> getValues() {
		return values;
	}

	public SensorConfiguration withSequence(int incrementAndGet) {
		this.sequence=incrementAndGet;
		return this;
	}
	
}
