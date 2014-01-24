package org.b.v.system.rpi;

import java.io.IOException;

import org.b.v.system.I2CConnection;
import org.b.v.system.System;

public class RPI implements System {

	public I2CConnection createI2CConnection(int adress) throws IOException {
		return new RPII2CDevice();
	}

}
