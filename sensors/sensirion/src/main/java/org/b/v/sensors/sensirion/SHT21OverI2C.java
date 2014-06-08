package org.b.v.sensors.sensirion;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.b.v.sensors.api.Sensor;
import org.b.v.sensors.api.SensorType;
import org.b.v.sensors.api.SensorValue;
import org.b.v.sensors.api.SensorValueType;
import org.b.v.sensors.api.error.SensorConfigurationException;
import org.b.v.sensors.api.functional.RelativeHumiditySensor;
import org.b.v.sensors.api.functional.TemperatureSensor;
import org.b.v.sensors.api.support.DefaultSensorType;
import org.b.v.sensors.sensirion.error.MeasurmentTypeNotAvailable;
import org.b.v.system.I2CConnection;


public class SHT21OverI2C implements Sensor,TemperatureSensor,RelativeHumiditySensor {
	public final static byte TEMPERATURE_HOLD_COMMAND = (byte) 0xE3;
	public final static byte RELATIVE_HUMIDITY_HOLD_COMMAND = (byte) 0xE5;
	public final static byte TEMPERATURE_NOHOLD_COMMAND = (byte) 0xF3;
	public final static byte RELATIVE_HUMIDITY_NOHOLD_COMMAND = (byte) 0xF5;
	public final static byte SOFT_RESET               = (byte)0xFE;
	
	private I2CConnection sensor;
	private String id;
	
	public SHT21OverI2C(String id,org.b.v.system.System system) throws IOException {
		this.id=id;
		if(system==null){
			throw new IllegalArgumentException("Cannot instantiate an I2C-implementation without a valid system");
		}
		sensor = system.createI2CConnection(40);
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

	@Override
	public String identification() {
		return id;
	}
	
	public double readHumidity() throws IOException, InterruptedException{
		sensor.write(RELATIVE_HUMIDITY_NOHOLD_COMMAND);
		
		TimeUnit.MILLISECONDS.sleep(100);//depends on the configuration - to be implemented later
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

	public SensorType type() {
		return sensorType;
	}
	
	public static SensorType sensorType = 
			new DefaultSensorType()
				.addMeassurementType("temperature",SensorValueType.DECIMAL)
				.addMeassurementType("humidity",SensorValueType.DECIMAL)
				.addConfigurationParameter("resulution",SensorValueType.LONG);

	public SensorValue meassure(String type) throws IOException, InterruptedException {
		switch(type) {
			case "temperature" : return SensorValue.decimal("temperature",readTemperature());
			case "humidity" : return SensorValue.decimal("humidity",readHumidity());
		}
		throw new MeasurmentTypeNotAvailable(type);
	}

	@Override
	public void configure(String parameter, SensorValue value) throws SensorConfigurationException {
		
		
	}

			
}
