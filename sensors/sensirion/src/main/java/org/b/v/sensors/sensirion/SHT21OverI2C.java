package org.b.v.sensors.sensirion;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.b.v.system.I2CConnection;


public class SHT21OverI2C {
	public final static byte TEMPERATURE_HOLD_COMMAND = (byte) 0xE3;
	public final static byte RELATIVE_HUMIDITY_HOLD_COMMAND = (byte) 0xE5;
	public final static byte TEMPERATURE_NOHOLD_COMMAND = (byte) 0xF3;
	public final static byte RELATIVE_HUMIDITY_NOHOLD_COMMAND = (byte) 0xF5;
	public final static byte SOFT_RESET               = (byte)0xFE;
	
	private I2CConnection sensor;
	
	public SHT21OverI2C(I2CConnection device) throws IOException {
		if(device==null){
			throw new IllegalArgumentException("Cannot instantiate with a I2C-implementation");
		}
		sensor = device;
	}
	
	public double readTemperature() throws IOException, InterruptedException{
		sensor.write(TEMPERATURE_NOHOLD_COMMAND);
		TimeUnit.MILLISECONDS.sleep(100);//depends on the configuration - to be implemented later
		//TODO => make a system-class to avoid running waiting and to test the 
		byte[] d = new byte[3];
		int numberOfBytes = sensor.read(d, 0, 3);
		
		if(numberOfBytes != 3){
			throw new RuntimeException("Response should be 3 bytes long");
		}
		return convertToTemperature(extractValue(d)); 
	}
	
	public double readHumidity() throws IOException, InterruptedException{
		sensor.write(RELATIVE_HUMIDITY_NOHOLD_COMMAND);
		//sytem.
		
		//TimeUnit.MILLISECONDS.sleep(100);//depends on the configuration - to be implemented later
		//TODO => make a system-class to avoid running waiting and to test the 
		byte[] d = new byte[3];
		int numberOfBytes = sensor.read(d, 0, 3);
		if(numberOfBytes != 3){
			throw new RuntimeException("Response should be 3 bytes long");
		}
		return convertToRH(extractValue(d)); 
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
	
//	public static void main(String[] args) throws IOException, InterruptedException {
//		SHT21OverI2C sensor = new SHT21OverI2C(new RPII2CDevice());
//		System.out.println(sensor.readTemperature());
//		System.out.println(sensor.readHumidity());
//	}
	
}
