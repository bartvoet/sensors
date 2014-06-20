package org.b.v.sensors.sensirion;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.b.v.sensors.api.Sensor;
import org.b.v.sensors.api.SensorType;
import org.b.v.sensors.api.error.SensorConfigurationException;
import org.b.v.system.I2CConnection;
import org.sensor.foundation.values.SensorValue;


public class SDP600OverI2C implements Sensor{
	public final static byte PRESSURE_NOHOLD_COMMAND = (byte) 0xF1;
	public final static byte SOFT_RESET               = (byte)0xFE;
	
	private I2CConnection sensor;
	
	public SDP600OverI2C(I2CConnection device) throws IOException {
		if(device==null){
			throw new IllegalArgumentException("Cannot instantiate with a I2C-implementation");
		}
		sensor = device;
	}
	
	public double readPressure() throws IOException, InterruptedException{
		sensor.write(PRESSURE_NOHOLD_COMMAND);
		TimeUnit.MILLISECONDS.sleep(100);//depends on the configuration - to be implemented later
		//TODO => make a system-class to avoid running waiting and to test the 
		byte[] d = new byte[3];
		int numberOfBytes = sensor.read(d, 0, 3);
		
		if(numberOfBytes != 3){
			throw new RuntimeException("Response should be 3 bytes long");
		}
		return convertToTemperature(extractValue(d)); 
	}
	
	public void softreset() throws IOException, InterruptedException{
		  sensor.write(SOFT_RESET);
		  TimeUnit.MILLISECONDS.sleep(50); // < 15 
	}

	private static int extractValue(byte[] d) {
		int val = d[0];
		
		val <<= 8;//shift 8 positions to the left
		val += d[1];
		val &= 0xFFFC;
		return val;
	}
	
	public static double convertToTemperature(int val){
		return -46.85 + (175.72/65536 * val); 
	}
	
	public static double convertToRH(int val){
		return - 6.0 + 125/65536.0 * val;//val = ((val * 256) / 134215) - 6;
	}

	public SensorType type() {
		// TODO Auto-generated method stub
		return null;
	}

	public SensorValue meassure(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String identification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configure(Set<SensorValue> configurationValues)
			throws SensorConfigurationException {
		// TODO Auto-generated method stub
		
	}
	
}
