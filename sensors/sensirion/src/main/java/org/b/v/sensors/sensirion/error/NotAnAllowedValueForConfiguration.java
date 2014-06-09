package org.b.v.sensors.sensirion.error;

public class NotAnAllowedValueForConfiguration extends RuntimeException {

	private static final long serialVersionUID = -8854245020209513387L;

	public NotAnAllowedValueForConfiguration(String string, String newResolution) {
		super("Value " + newResolution + " not allowed for parameter " + string);
	}

}
