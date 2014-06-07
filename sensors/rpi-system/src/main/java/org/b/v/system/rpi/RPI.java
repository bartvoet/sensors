package org.b.v.system.rpi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.b.v.system.I2CConnection;
import org.springframework.stereotype.Component;

@Component("system")
public class RPI implements org.b.v.system.System {

	public I2CConnection createI2CConnection(int adress) throws IOException {
		return new RPII2CDevice();
	}

	public void wait(int milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}