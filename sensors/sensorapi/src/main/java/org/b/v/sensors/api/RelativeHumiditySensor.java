package org.b.v.sensors.api;

import java.io.IOException;


public interface RelativeHumiditySensor extends Sensor{
	
	public double readHumidity() throws IOException, InterruptedException;

}
