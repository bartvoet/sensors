package org.b.v.sensors.api;

import java.io.IOException;


public interface Sensor {
	
	public SensorType type();
	
	public Double meassure(String type) throws IOException, InterruptedException;
	
	public void softreset() throws IOException, InterruptedException;

}
