package org.b.v.sensors.api;

import java.io.IOException;


public interface TemperatureSensor extends Sensor{
	
	public double readTemperature() throws IOException, InterruptedException;
	
}
