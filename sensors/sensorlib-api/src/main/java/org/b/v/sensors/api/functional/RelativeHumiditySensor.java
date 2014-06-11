package org.b.v.sensors.api.functional;

import java.io.IOException;

import org.b.v.sensors.api.Sensor;


public interface RelativeHumiditySensor extends Sensor{
	
	public double readHumidity() throws IOException, InterruptedException;

}
