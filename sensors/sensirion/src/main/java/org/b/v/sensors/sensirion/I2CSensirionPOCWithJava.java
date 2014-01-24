package org.b.v.sensors.sensirion;
/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  I2CWiiMotionPlusExample.java  
 * 
 * This file is part of the Pi4J project. More information about 
 * this project can be found here:  http://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2013 Pi4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.concurrent.TimeUnit;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class I2CSensirionPOCWithJava {

	public  static byte eTempHoldCmd = (byte) 0xE3;
	public static byte eRHumidityHoldCmd = (byte) 0xE5;
	public static byte eTempNoHoldCmd = (byte) 0xF3;
	public static byte eRHumidityNoHoldCmd = (byte) 0xF5;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		final I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
		final I2CDevice sensor = bus.getDevice(0x40);
		
		//sensor.
		
		byte someCommand = eTempNoHoldCmd;
		sensor.write(someCommand);
		
		// in this case depends on resolution
		// best to retry als need to check the nack-side
		// today we get an exception
		TimeUnit.MILLISECONDS.sleep(100);
		//investigate the differences => parse messages?
		// how to make the distinction (disconnect???)
		
//		Caused by: java.io.IOException: Error reading from /dev/i2c-1 at address 0x40 to address 0xf3. Got -1.
//		at com.pi4j.io.i2c.impl.I2CDeviceImpl.read(I2CDeviceImpl.java:208)
//		at I2CSensirionPOCWithJava.main(I2CSensirionPOCWithJava.java:64)
//		... 5 more

		// functionele errors onderzoeken => hoe dit testbaar maken? (zit in principe in de laatste byte)
		
		// systeem
		// sensors detecteren? => dit is mogelijk !!!!
		
		// autodetection
		// remote sensors (smarten up)
		
		byte[] buffer = new byte[3];
		int test = sensor.read(buffer, 0, 3);
		System.out.println(test);
		System.out.println(buffer[0]);
		System.out.println(buffer[1]);
		System.out.println(buffer[2]);

		
		
		//i2cget -y 1 0x40 0xe7 b
		//System.out.println(sensor.read(0xe7));
		
		//i2cset -y 0 0x40 0xf3
//		byte buffer[] = new byte[3];
//		sensor.read(0xf3, buffer, 0, 3);
	}
	
//	//==============================================================================
//	float SHT2x_CalcRH(u16t u16sRH)
//	//==============================================================================
//	{
//	  ft humidityRH;              // variable for result
//
//	  u16sRH &= ~0x0003;          // clear bits [1..0] (status bits)
//	  //-- calculate relative humidity [%RH] --
//
//	  humidityRH = -6.0 + 125.0/65536 * (ft)u16sRH; // RH= -6 + 125 * SRH/2^16
//	  return humidityRH;
//	}
//
//	//==============================================================================
//	float SHT2x_CalcTemperatureC(u16t u16sT)
//	//==============================================================================
//	{
//	  ft temperatureC;            // variable for result
//
//	  u16sT &= ~0x0003;           // clear bits [1..0] (status bits)
//
//	  //-- calculate temperature [C] --
//	  temperatureC= -46.85 + 175.72/65536 *(ft)u16sT; //T= -46.85 + 175.72 * ST/2^16
//	  return temperatureC;
//	}

}
