package org.b.v.sensors.sensirion;

import static org.junit.Assert.*;

import java.io.IOException;

import org.b.v.sensors.sensirion.SHT21OverI2C;
import org.b.v.system.I2CConnection;
import org.junit.Test;


public class SHT21OverI2CTest {

	private static final int NOT_3_BYTES = 0;

	private static class StubI2CDevice implements I2CConnection{
		public void write(byte b) throws IOException {
			// TODO Auto-generated method stub
		}

		public int read(byte[] buffer, int offset, int size) throws IOException {
			byte[] bytes = new byte[3];
			bytes[0]=99;bytes[1]=3;
			bytes[2]=3;
			return 3;
		}
		
	}
	
//	@Test
//	public void readHumidity_calculation_formula() throws IOException, InterruptedException {
//		StubI2CDevice device = new StubI2CDevice();
//		SHT21OverI2C rhMeter = new SHT21OverI2C(device);
//		assertEquals(-6.0, rhMeter.readHumidity(),0);
//	}
//
//	@Test
//	public void readHumidity_will_throw() throws IOException, InterruptedException {
//		SHT21OverI2C rhMeter = new SHT21OverI2C(new I2CConnection(){
//			public void write(byte b) throws IOException {}
//
//			public int read(byte[] buffer, int offset, int size)
//					throws IOException {return NOT_3_BYTES;	}
//			
//		});
//		
//		try {
//			rhMeter.readHumidity();
//			fail("exception is expected");
//		} catch (RuntimeException e) {
//			assertEquals("Response should be 3 bytes long",e.getMessage());
//		}
//	}

}
