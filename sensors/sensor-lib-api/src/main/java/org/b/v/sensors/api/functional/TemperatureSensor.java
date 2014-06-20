package org.b.v.sensors.api.functional;

import java.io.IOException;

import org.b.v.sensors.api.Sensor;


public interface TemperatureSensor extends Sensor{
	
	public double readTemperature() throws IOException, InterruptedException;
	
}
