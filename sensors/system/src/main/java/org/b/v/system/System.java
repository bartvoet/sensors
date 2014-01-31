package org.b.v.system;

import java.io.IOException;

public interface System {
	public I2CConnection createI2CConnection(int adress) throws IOException;
	public void wait(int milliseconds);
}
