package org.b.v.sensors.api;

import java.io.IOException;


public interface Sensor {
	
	public double readTemperature() throws IOException, InterruptedException;
	
	public double readHumidity() throws IOException, InterruptedException;
	
	public void softreset() throws IOException, InterruptedException;

}
