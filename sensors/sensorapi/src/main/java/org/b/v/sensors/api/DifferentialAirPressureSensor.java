package org.b.v.sensors.api;

import java.io.IOException;


public interface DifferentialAirPressureSensor extends Sensor{
	
	public double readPressure() throws IOException, InterruptedException;
	
}
