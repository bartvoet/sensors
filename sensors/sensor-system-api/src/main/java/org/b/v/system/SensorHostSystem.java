package org.b.v.system;

import java.io.IOException;

public interface SensorHostSystem {
	public I2CConnection createI2CConnection(int adress) throws IOException;
	public void waitMillis(int milliseconds);
}
