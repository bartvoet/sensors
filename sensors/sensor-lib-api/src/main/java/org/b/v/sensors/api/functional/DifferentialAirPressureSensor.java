package org.b.v.sensors.api.functional;

import java.io.IOException;

import org.b.v.sensors.api.Sensor;


public interface DifferentialAirPressureSensor extends Sensor{
	
	public double readPressure() throws IOException, InterruptedException;
	
}
