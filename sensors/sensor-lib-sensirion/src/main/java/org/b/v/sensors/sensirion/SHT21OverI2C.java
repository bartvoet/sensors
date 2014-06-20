package org.b.v.sensors.sensirion;

import java.io.IOException;
import java.util.Set;

import org.b.v.sensors.api.Sensor;
import org.b.v.sensors.api.SensorType;
import org.b.v.sensors.api.error.SensorConfigurationException;
import org.b.v.sensors.api.functional.RelativeHumiditySensor;
import org.b.v.sensors.api.functional.TemperatureSensor;
import org.b.v.sensors.api.support.DefaultSensorType;
import org.b.v.sensors.sensirion.error.ConfigurationValueNotAvailable;
import org.b.v.sensors.sensirion.error.MeasurementTypeNotAvailable;
import org.b.v.sensors.sensirion.error.NotAnAllowedValueForConfiguration;
import org.b.v.system.I2CConnection;
import org.b.v.system.SensorHostSystem;
import org.sensor.foundation.values.SensorValue;
import org.sensor.foundation.values.SensorValueType;

import static org.b.v.sensors.api.support.DefaultSensorType.configuration;


public class SHT21OverI2C implements Sensor,TemperatureSensor,RelativeHumiditySensor {
	private static final String MEASUREMENT_FOR_HUMIDITY = "humidity";
	private static final String MEASUREMENT_FOR_TEMPERATURE = "temperature";
	public final static byte COMMAND_FOR_TEMPERATURE_WITH_HOLD = (byte) 0xE3;
	public final static byte COMMAND_FOR_RELATIVE_HUMIDITY_WITH_HOLD = (byte) 0xE5;
	public final static byte COMMAND_FOR_TEMPERATURE_WITH_NOHOLD = (byte) 0xF3;
	public final static byte COMMAND_FOR_RELATIVE_HUMIDITY_WITH_NOHOLD = (byte) 0xF5;
	
	public final static byte COMMAND_FOR_SOFT_RESET               = (byte)0xFE;//1111’1110
	public final static byte COMMAND_FOR_USER_REGISTRY_WRITE      = (byte)0xE6;//1110’0110
	public final static byte COMMAND_FOR_USER_REGISTRY_READ      = (byte)0xE7;//1110’0110
	
	public final static String OPTION_FOR_RESOLUTION_TEMP_14_RH_12 = "RESOLUTION_TEMP_14_RH_12";
	public final static String OPTION_FOR_RESOLUTION_TEMP_12_RH_8 = "RESOLUTION_TEMP_12_RH_8";
	public final static String OPTION_FOR_RESOLUTION_TEMP_13_RH_10 = "RESOLUTION_TEMP_13_RH_10";
	public final static String OPTION_FOR_RESOLUTION_TEMP_11_RH_11 = "RESOLUTION_TEMP_11_RH_11";
	
	private SensorHostSystem system;
	private I2CConnection i2c;
	private String id;
	
	public SHT21OverI2C(String id,SensorHostSystem system) throws IOException {
		this.id=id;
		if(system==null){
			throw new IllegalArgumentException("Cannot instantiate an I2C-implementation without a valid system");
		}
		i2c = system.createI2CConnection(40);
		this.system=system;
		
	}
	
	public double readTemperature() throws IOException, InterruptedException{
		i2c.write(COMMAND_FOR_TEMPERATURE_WITH_NOHOLD);
		system.waitMillis(100);
		byte[] rawValue = new byte[3];
		int numberOfBytes = i2c.read(rawValue, 0, 3);
		
		if(numberOfBytes != 3){
			throw new RuntimeException("Response should be 3 bytes long");
		}
		return convertToTemperature(extractValue(rawValue)); 
	}

	@Override
	public String identification() {
		return id;
	}
	
	public double readHumidity() throws IOException, InterruptedException{
		i2c.write(COMMAND_FOR_RELATIVE_HUMIDITY_WITH_NOHOLD);
		system.waitMillis(100);
		byte[] d = new byte[3];
		int numberOfBytes = i2c.read(d, 0, 3);
		if(numberOfBytes != 3){
			throw new RuntimeException("Response should be 3 bytes long");
		}
		return convertToRH(extractValue(d)); 
	}
	
	public void softreset() throws IOException, InterruptedException{
		  i2c.write(COMMAND_FOR_SOFT_RESET);
		  system.waitMillis(50);// < 15 
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

	
	public static SensorType sensorType = 
			new DefaultSensorType()
				.addMeassurementType(MEASUREMENT_FOR_TEMPERATURE,SensorValueType.DECIMAL)
				.addMeassurementType(MEASUREMENT_FOR_HUMIDITY,SensorValueType.DECIMAL)
				.addConfiguration(
						configuration("resolution")
						.withType(SensorValueType.STRING)
						.withDefaultValue(OPTION_FOR_RESOLUTION_TEMP_14_RH_12)
						.withOptions(OPTION_FOR_RESOLUTION_TEMP_14_RH_12,
									 OPTION_FOR_RESOLUTION_TEMP_12_RH_8,
									 OPTION_FOR_RESOLUTION_TEMP_11_RH_11,
									 OPTION_FOR_RESOLUTION_TEMP_13_RH_10)
						);
	
	

	public SensorType type() {
		return sensorType;
	}

	@Override
	public SensorValue meassure(String type) throws IOException, InterruptedException {
		switch(type) {
			case MEASUREMENT_FOR_TEMPERATURE : return SensorValue.decimal(MEASUREMENT_FOR_TEMPERATURE,readTemperature());
			case MEASUREMENT_FOR_HUMIDITY : return SensorValue.decimal(MEASUREMENT_FOR_HUMIDITY,readHumidity());
		}
		throw new MeasurementTypeNotAvailable(type);
	}
	
	protected void changeResolution(SensorValue value) throws IOException {
		String newResolution = (String) value.getValue();
		byte registerContent = readRegister();
		switch(newResolution) {
			case OPTION_FOR_RESOLUTION_TEMP_14_RH_12 : 
				changeRegister(transformRegisterByteForResolution(registerContent,true,true));
				break;
			case OPTION_FOR_RESOLUTION_TEMP_12_RH_8 : 
				changeRegister(transformRegisterByteForResolution(registerContent,true,true));
				break;
			case OPTION_FOR_RESOLUTION_TEMP_13_RH_10: 
				changeRegister(transformRegisterByteForResolution(registerContent,true,true));
				break;
			case OPTION_FOR_RESOLUTION_TEMP_11_RH_11: 
				changeRegister(transformRegisterByteForResolution(registerContent,true,true));
				break;
			default : throw new NotAnAllowedValueForConfiguration("resolution",newResolution);
		}
	}

	private byte transformRegisterByteForResolution(byte registerContent,boolean bit7,boolean bit0) {
		return bitAtPosition(bitAtPosition(registerContent,(byte)7,true),(byte)0,true);
	}
	
	private byte readRegister() throws IOException{
        i2c.write(COMMAND_FOR_USER_REGISTRY_READ);
        system.waitMillis(100);
        byte[] bytes = new byte[1];
        i2c.read(bytes, 0, 1);
        return bytes[0];
	}

	
	private void changeRegister(byte content) throws IOException{
		byte registryBuffer[] = new byte[0];
		i2c.read(registryBuffer, 0, 1);
		i2c.write(COMMAND_FOR_USER_REGISTRY_WRITE,content);
	}
	
//	private static boolean valueOfBitAtPosition(byte number,byte position){
//		return ((number & ( 1 << position )) >> position) == 1;
//	}
	
	private static byte bitAtPosition(byte number,byte position,boolean bit){
		if(bit) {
			return (byte)(number | (1 << position));
		} else {
			return (byte)(number & (~(1 << position)));
		}
	}

	@Override
	public void configure(Set<SensorValue> configurationValues)
			throws SensorConfigurationException {
		for(SensorValue value : configurationValues) {
			String parameter = value.getDefinition().getName();
			switch(value.getDefinition().getName()) {
				case "resolution" : try {
					changeResolution(value);
				} catch (IOException e) {
					throw new SensorConfigurationException(e);
				}
				break;
			}
			throw new ConfigurationValueNotAvailable(parameter);
		}
	}
	
}
