package org.b.v.system.rpi;

import java.io.IOException;

import org.b.v.system.I2CConnection;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;



public class RPII2CDevice implements I2CConnection {

	private final I2CBus bus; 
	private final I2CDevice sensor;
	
	public RPII2CDevice() throws IOException{
		bus = I2CFactory.getInstance(I2CBus.BUS_1);
		sensor = bus.getDevice(0x40);
	}
	
	public void write(byte b) throws IOException {
		sensor.write(b);
	}

	public int read(byte[] buffer, int offset, int size) throws IOException {
		return sensor.read(buffer, offset, size);
	}

}
