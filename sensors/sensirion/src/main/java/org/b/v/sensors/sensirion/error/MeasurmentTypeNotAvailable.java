package org.b.v.sensors.sensirion.error;

public class MeasurmentTypeNotAvailable extends RuntimeException {

	public MeasurmentTypeNotAvailable(String type) {
		super(type==null?"null":type + " is not available");
	}

	private static final long serialVersionUID = 2049125453461687043L;

}
